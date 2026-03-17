package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

@TeleOp(name = "Drive Test", group = "Test")
public class DriveTest extends LinearOpMode {
    Drive drive;
    DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    @Override
    public void runOpMode() throws InterruptedException {
        // TODO reverse motors?
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        drive = new Drive(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive);
        waitForStart();
        while (opModeIsActive()) {
            double axial = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;

            drive.driveRobotCentric(axial, lateral, yaw);
        }
    }
}

