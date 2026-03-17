package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Motor Test", group="Linear OpMode")
@Disabled
public class MotorTest extends LinearOpMode {
    public DcMotor motor;
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "flywheel_motor_1");
        waitForStart();
        motor.setPower(1.0);
        while(opModeIsActive()) {
            sleep(10);
        }
        motor.setPower(0.0);
    }
}
