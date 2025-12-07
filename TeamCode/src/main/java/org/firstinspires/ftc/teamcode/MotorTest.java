package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Motor test", group="Linear OpMode")
public class MotorTest extends LinearOpMode {
    private DcMotor motor;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor_test");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // Turns motor on for 2 seconds at progressively higher powers
        telemetry.addData("Power", "25%");
        telemetry.update();
        motor.setPower(0.25);
        sleep(2_000);

        telemetry.addData("Power", "50%");
        telemetry.update();
        motor.setPower(0.5);
        sleep(2_000);

        telemetry.addData("Power", "75%");
        telemetry.update();
        motor.setPower(0.75);
        sleep(2_000);

        telemetry.addData("Power", "100%");
        telemetry.update();
        motor.setPower(1.0);
        sleep(2_000);

        motor.setPower(0);
        sleep(5_000);

        // Turns motor on for 2 seconds at progressively higher powers in reverse direction
        telemetry.addData("Power", "-25%");
        telemetry.update();
        motor.setPower(-0.25);
        sleep(2_000);

        telemetry.addData("Power", "-50%");
        telemetry.update();
        motor.setPower(-0.5);
        sleep(2_000);

        telemetry.addData("Power", "-75%");
        telemetry.update();
        motor.setPower(-0.75);
        sleep(2_000);

        telemetry.addData("Power", "-100%");
        telemetry.update();
        motor.setPower(-1.0);
        sleep(2_000);

        motor.setPower(0);
        telemetry.addData("Status", "Test finished");
        telemetry.update();
    }
}
