package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {

    public DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    public DcMotor pickupMotor;
    public DcMotor flywheelFront, flywheelBack;
//    public WebcamName webcam;


    public RobotHardware(HardwareMap hw){
        frontLeftDrive = hw.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hw.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hw.get(DcMotor.class, "back_left_drive");
        backRightDrive = hw.get(DcMotor.class, "back_right_drive");
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        pickupMotor = hw.get(DcMotor.class, "pickup_motor");

        flywheelFront = hw.get(DcMotor.class, "flywheel_motor_front");
        flywheelBack = hw.get(DcMotor.class, "flywheel_motor_back");

//        webcam = hw.get(WebcamName.class, "webcam");
    }
}
