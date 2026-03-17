package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Monolith TeleOp", group="Test")
public class TeleOpHacky extends LinearOpMode {

    // -------------------------------------------------------------------------
    // HARDWARE
    // -------------------------------------------------------------------------
    private DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    private DcMotor flywheelFront, flywheelBack;
    private DcMotor pickupMotorFront, pickupMotorBack;

    // -------------------------------------------------------------------------
    // DRIVE STATE
    // -------------------------------------------------------------------------
    private double frontLeftPower, frontRightPower, backLeftPower, backRightPower;

    // -------------------------------------------------------------------------
    // FLYWHEEL STATE
    // -------------------------------------------------------------------------
    private static final double FLYWHEEL_FRONT_POWER  = 1.0;
    private static final double FLYWHEEL_BACK_POWER   = 1.0;
    private static final double RAMP_DURATION         = 2.0; // seconds

    private boolean rampingUp   = false;
    private boolean rampingDown = false;
    private double  currentPowerFront = 0.0;
    private double  currentPowerBack  = 0.0;
    private ElapsedTime rampTimer = new ElapsedTime();

    // -------------------------------------------------------------------------
    // PICKUP STATE
    // -------------------------------------------------------------------------
    private static final double PICKUP_POWER = 0.8;
    private boolean pickupActive = false;

    // -------------------------------------------------------------------------
    // MISC
    // -------------------------------------------------------------------------
    private ElapsedTime runtime = new ElapsedTime();

    // -------------------------------------------------------------------------

    @Override
    public void runOpMode() {

        // --- Hardware map ---
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive   = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive  = hardwareMap.get(DcMotor.class, "back_right_drive");

        flywheelFront   = hardwareMap.get(DcMotor.class, "flywheel_motor_front");
        flywheelBack    = hardwareMap.get(DcMotor.class, "flywheel_motor_back");

        pickupMotorFront = hardwareMap.get(DcMotor.class, "pickup_motor_front");
        pickupMotorBack  = hardwareMap.get(DcMotor.class, "pickup_motor_back");

        // --- Motor directions ---
        // Drive: left side reversed so both sides drive forward together
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        // Flywheel: motors face each other, so they spin opposite directions
        flywheelFront.setDirection(DcMotorSimple.Direction.REVERSE);
        flywheelBack.setDirection(DcMotorSimple.Direction.FORWARD);

        // Pickup
        pickupMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
        pickupMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);

        // --- Brake behaviour ---
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialised — waiting for start");
        telemetry.update();

        waitForStart();
        telemetry.clearAll();
        runtime.reset();

        // =====================================================================
        // MAIN LOOP
        // =====================================================================
        while (opModeIsActive()) {

            // -----------------------------------------------------------------
            // DRIVE  (gamepad 1, robot-centric mecanum)
            // -----------------------------------------------------------------
            double axial   = -gamepad1.left_stick_y;   // forward/back (stick forward = negative, hence flip)
            double lateral =  gamepad1.left_stick_x;   // strafe
            double yaw     =  gamepad1.right_stick_x;  // rotation

            frontLeftPower  = axial + lateral + yaw;
            frontRightPower = axial - lateral - yaw;
            backLeftPower   = axial - lateral + yaw;
            backRightPower  = axial + lateral - yaw;

            // Normalise so no wheel exceeds ±1.0
            double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));
            if (max > 1.0) {
                frontLeftPower  /= max;
                frontRightPower /= max;
                backLeftPower   /= max;
                backRightPower  /= max;
            }

            frontLeftDrive.setPower(frontLeftPower);
            frontRightDrive.setPower(frontRightPower);
            backLeftDrive.setPower(backLeftPower);
            backRightDrive.setPower(backRightPower);

            // -----------------------------------------------------------------
            // PICKUP  (gamepad 2, dpad up held = run)
            // -----------------------------------------------------------------
            if (gamepad2.dpad_up) {
                pickupMotorFront.setPower(PICKUP_POWER);
                pickupMotorBack.setPower(PICKUP_POWER);
                pickupActive = true;
            } else {
                pickupMotorFront.setPower(0.0);
                pickupMotorBack.setPower(0.0);
                pickupActive = false;
            }

            // -----------------------------------------------------------------
            // FLYWHEEL  (gamepad 2)
            //   cross       → ramp up
            //   square      → ramp down
            //   dpad down   → hard stop
            //   triggers    → direct override (only when not ramping)
            // -----------------------------------------------------------------

            // Rising-edge button detection
            if (gamepad2.cross && !rampingUp && !rampingDown) {
                rampingUp = true;
                rampTimer.reset();
            }
            if (gamepad2.square && !rampingUp && !rampingDown) {
                rampingDown = true;
                rampTimer.reset();
            }
            if (gamepad2.dpad_down) {
                // Hard stop — kill ramps and zero motors immediately
                rampingUp   = false;
                rampingDown = false;
                currentPowerFront = 0.0;
                currentPowerBack  = 0.0;
                flywheelFront.setPower(0.0);
                flywheelBack.setPower(0.0);
            }

            // Ramp update
            if (rampingUp) {
                double t      = Math.min(rampTimer.seconds() / RAMP_DURATION, 1.0);
                double smooth = t * t * (3 - 2 * t);           // smoothstep
                currentPowerFront = smooth * FLYWHEEL_FRONT_POWER;
                currentPowerBack  = smooth * FLYWHEEL_BACK_POWER;
                flywheelFront.setPower(currentPowerFront);
                flywheelBack.setPower(currentPowerBack);
                if (t >= 1.0) rampingUp = false;
            }

            if (rampingDown) {
                double t      = Math.min(rampTimer.seconds() / RAMP_DURATION, 1.0);
                double smooth = 1.0 - t * t * (3 - 2 * t);    // inverse smoothstep
                currentPowerFront = smooth * FLYWHEEL_FRONT_POWER;
                currentPowerBack  = smooth * FLYWHEEL_BACK_POWER;
                flywheelFront.setPower(currentPowerFront);
                flywheelBack.setPower(currentPowerBack);
                if (t >= 1.0) rampingDown = false;
            }

            // Trigger override — only active when not mid-ramp
            if (!rampingUp && !rampingDown) {
                if (gamepad2.left_trigger > 0.0 || gamepad2.right_trigger > 0.0) {
                    currentPowerBack  = gamepad2.left_trigger;
                    currentPowerFront = gamepad2.right_trigger;
                    flywheelBack.setPower(currentPowerBack);
                    flywheelFront.setPower(currentPowerFront);
                }
            }

            // -----------------------------------------------------------------
            // TELEMETRY
            // -----------------------------------------------------------------

            // Runtime
            telemetry.addData("Run time (s)", "%.1f", runtime.seconds());
            telemetry.addLine();

            // Drive
            telemetry.addLine("--- DRIVE ---");
            telemetry.addData("FL / FR power", "%+.2f  /  %+.2f", frontLeftPower, frontRightPower);
            telemetry.addData("BL / BR power", "%+.2f  /  %+.2f", backLeftPower,  backRightPower);
            telemetry.addData("Axial / Lateral / Yaw", "%+.2f  /  %+.2f  /  %+.2f", axial, lateral, yaw);
            telemetry.addLine();

            // Flywheel
            telemetry.addLine("--- FLYWHEEL ---");
            String flywheelState;
            if      (rampingUp)   flywheelState = "RAMPING UP   [" + String.format("%.0f%%", (rampTimer.seconds() / RAMP_DURATION) * 100) + "]";
            else if (rampingDown) flywheelState = "RAMPING DOWN [" + String.format("%.0f%%", (rampTimer.seconds() / RAMP_DURATION) * 100) + "]";
            else if (currentPowerFront > 0 || currentPowerBack > 0) flywheelState = "RUNNING";
            else                  flywheelState = "IDLE";
            telemetry.addData("State",        flywheelState);
            telemetry.addData("Front power",  "%+.3f  (actual: %+.3f)", currentPowerFront, flywheelFront.getPower());
            telemetry.addData("Back  power",  "%+.3f  (actual: %+.3f)", currentPowerBack,  flywheelBack.getPower());
            telemetry.addLine();

            // Pickup
            telemetry.addLine("--- PICKUP ---");
            telemetry.addData("State",        pickupActive ? "RUNNING" : "STOPPED");
            telemetry.addData("Power",        pickupActive ? PICKUP_POWER : 0.0);

            telemetry.update();
        }
    }
}