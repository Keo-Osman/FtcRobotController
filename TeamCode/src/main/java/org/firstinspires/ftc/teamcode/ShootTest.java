package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.GamepadTelemetry;


@TeleOp(name = "Shoot & Pickup Test", group = "Test")
public class ShootTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor pickupMotorFront = hardwareMap.get(DcMotor.class, "pickup_motor_front");
        DcMotor pickupMotorBack = hardwareMap.get(DcMotor.class, "pickup_motor_back");

        DcMotor flywheelBack = hardwareMap.get(DcMotor.class, "flywheel_back");
        DcMotor flywheelFront = hardwareMap.get(DcMotor.class, "flywheel_front");

        pickupMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
        pickupMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);

        flywheelBack.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelFront.setDirection(DcMotorSimple.Direction.FORWARD);
        GamepadTelemetry gp = new GamepadTelemetry();
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.crossWasPressed()){
                if(pickupMotorBack.getDirection() == DcMotorSimple.Direction.FORWARD){
                    pickupMotorBack.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                else if (pickupMotorBack.getDirection() == DcMotorSimple.Direction.REVERSE) {
                    pickupMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
                }
            }
            if(gamepad1.circleWasPressed()){
                if(pickupMotorFront.getDirection() == DcMotorSimple.Direction.FORWARD){
                    pickupMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                else if (pickupMotorFront.getDirection() == DcMotorSimple.Direction.REVERSE) {
                    pickupMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
                }
            }
            if(gamepad1.triangleWasPressed()){
                if(flywheelBack.getDirection() == DcMotorSimple.Direction.FORWARD){
                    flywheelBack.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                else if (flywheelBack.getDirection() == DcMotorSimple.Direction.REVERSE) {
                    flywheelBack.setDirection(DcMotorSimple.Direction.FORWARD);
                }
            }
            if(gamepad1.squareWasPressed()){
                if(flywheelFront.getDirection() == DcMotorSimple.Direction.FORWARD){
                    flywheelFront.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                else if (flywheelFront.getDirection() == DcMotorSimple.Direction.REVERSE) {
                    flywheelFront.setDirection(DcMotorSimple.Direction.FORWARD);
                }
            }
            flywheelBack.setPower(gamepad1.left_trigger);
            flywheelFront.setPower(gamepad1.right_trigger);
            if(gamepad1.dpad_down) {
                pickupMotorBack.setPower(-gamepad1.left_stick_x);
                pickupMotorFront.setPower(-gamepad1.left_stick_x);
            }
            else {
                pickupMotorBack.setPower(0.0);
                pickupMotorFront.setPower(0.0);
            }
            gp.Triggers(telemetry, gamepad1);
            gp.DPad(telemetry, gamepad1);
            gp.Sticks(telemetry, gamepad1);
            gp.Triggers(telemetry, gamepad1);
            telemetry.update();
        }
    }
}
