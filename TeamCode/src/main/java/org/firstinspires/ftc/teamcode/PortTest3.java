package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Port Test3", group = "Test")
public class PortTest3 extends LinearOpMode {

    DcMotor[] allMotors;

    void setAllDirections(boolean flipped) {
        DcMotorSimple.Direction dir = flipped ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD;
        for (DcMotor motor : allMotors) {
            motor.setDirection(dir);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motorCH0 = hardwareMap.get(DcMotor.class, "ch0");
        DcMotor motorCH1 = hardwareMap.get(DcMotor.class, "ch1");
        DcMotor motorCH2 = hardwareMap.get(DcMotor.class, "ch2");
        DcMotor motorCH3 = hardwareMap.get(DcMotor.class, "ch3");

        DcMotor motorEH0 = hardwareMap.get(DcMotor.class, "eh0");
        DcMotor motorEH1 = hardwareMap.get(DcMotor.class, "eh1");
        DcMotor motorEH2 = hardwareMap.get(DcMotor.class, "eh2");
        DcMotor motorEH3 = hardwareMap.get(DcMotor.class, "eh3");

        allMotors = new DcMotor[]{motorCH0, motorCH1, motorCH2, motorCH3, motorEH0, motorEH1, motorEH2, motorEH3};
        double power = 0.0;
        boolean flipped = false;
        boolean prevRightBumper = false;

        waitForStart();
        while (opModeIsActive()) {
            power = gamepad1.right_trigger;
            if (gamepad1.right_bumper && !prevRightBumper) {
                flipped = !flipped;
                setAllDirections(flipped);
            }
            prevRightBumper = gamepad1.right_bumper;

            if (gamepad1.triangle) {
                motorEH0.setPower(power);
            } else {
                motorEH0.setPower(0.0);
            }
            if (gamepad1.circle) {
                motorEH1.setPower(power);
            } else {
                motorEH1.setPower(0.0);
            }
            if (gamepad1.cross) {
                motorEH2.setPower(power);
            } else {
                motorEH2.setPower(0.0);
            }
            if (gamepad1.square) {
                motorEH3.setPower(power);
            } else {
                motorEH3.setPower(0.0);
            }

            if (gamepad1.dpad_up) {
                motorCH0.setPower(power);
            } else {
                motorCH0.setPower(0.0);
            }
            if (gamepad1.dpad_down) {
                motorCH1.setPower(power);
            } else {
                motorCH1.setPower(0.0);
            }
            if (gamepad1.dpad_left) {
                motorCH2.setPower(power);
            } else {
                motorCH2.setPower(0.0);
            }
            if (gamepad1.dpad_right) {
                motorCH3.setPower(power);
            } else {
                motorCH3.setPower(0.0);
            }
            telemetry.addData("Power", power);
            telemetry.update();
        }
    }
}