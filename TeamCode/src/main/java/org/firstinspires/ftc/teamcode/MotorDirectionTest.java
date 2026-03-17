package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.RobotHardware;

@TeleOp(name="Motor Direction Test", group="Test")
public class MotorDirectionTest extends LinearOpMode {
    private RobotHardware hw;
    @Override
    public void runOpMode() throws InterruptedException {
        hw = new RobotHardware(hardwareMap);

        waitForStart();

        double power = 0.5;
        while (opModeIsActive()){
            if(gamepad1.cross){
                hw.frontLeftDrive.setPower(power);
            }
            if(gamepad1.circle){
                hw.frontRightDrive.setPower(power);
            }
            if(gamepad1.square){
                hw.backLeftDrive.setPower(power);
            }
            if(gamepad1.triangle){
                hw.backRightDrive.setPower(power);
            }
        }
    }
}
