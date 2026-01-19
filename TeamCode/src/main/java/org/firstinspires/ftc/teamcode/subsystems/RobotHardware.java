package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class RobotHardware {

    // For now motors are 20:1 gear ratios except backRightDrive which is 27:1
    public DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    public Servo shootServo;
    public WebcamName webcam;


    public RobotHardware(HardwareMap hw){
        frontLeftDrive = hw.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hw.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hw.get(DcMotor.class, "back_left_drive");
        backRightDrive = hw.get(DcMotor.class, "back_right_drive");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        shootServo = hw.get(Servo.class, "shoot_servo");

        webcam = hw.get(WebcamName.class, "webcam");
    }
}
