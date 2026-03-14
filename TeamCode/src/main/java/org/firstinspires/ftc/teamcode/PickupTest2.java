package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Pickup Test (2)", group = "Test")
public class PickupTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor pickupMotorFront = hardwareMap.get(DcMotor.class, "pickup_motor_front");
        DcMotor pickupMotorBack = hardwareMap.get(DcMotor.class, "pickup_motor_back");

        waitForStart();

        pickupMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
        pickupMotorFront.setPower(0.8);
        pickupMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
        pickupMotorBack.setPower(0.8);

        while (opModeIsActive()){
            sleep(100);
        }
//        sleep(2000);
//
//        pickupMotorFront.setPower(0.0);
//
//        sleep(2000);
//
//        pickupMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        pickupMotorFront.setPower(0.8);
//        pickupMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
//        pickupMotorBack.setPower(0.8);
//        sleep(2000);
    }
}
