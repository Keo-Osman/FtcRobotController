package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class RobotHardware {

    // For now motors are 20:1 gear ratios except backRightDrive which is 27:1
    public DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    public CRServo pickupServo;
    public DcMotor flywheel1, flywheel2;
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

        pickupServo = hw.get(CRServo.class, "pickup_servo");

        flywheel1 = hw.get(DcMotor.class, "flywheel_motor_1");
        flywheel2 = hw.get(DcMotor.class, "flywheel_motor_2");

        webcam = hw.get(WebcamName.class, "webcam");
    }
}
