package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {

    public DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    public DcMotor pickupMotorFront, pickupMotorBack;
    public DcMotor flywheelFront, flywheelBack;

    public RobotHardware(HardwareMap hw){

        // Motor direction set in constructors of Drive, Pickup, Flywheel

        frontLeftDrive = hw.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hw.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hw.get(DcMotor.class, "back_left_drive");
        backRightDrive = hw.get(DcMotor.class, "back_right_drive");

        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        pickupMotorFront = hw.get(DcMotor.class, "pickup_motor_front");
        pickupMotorBack = hw.get(DcMotor.class, "pickup_motor_back");

        flywheelFront = hw.get(DcMotor.class, "flywheel_motor_front");
        flywheelBack = hw.get(DcMotor.class, "flywheel_motor_back");
    }
}
