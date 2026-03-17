package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {
    private DcMotor pickupMotorFront, pickupMotorBack;
    private static final DcMotorSimple.Direction FRONT_PICKUP_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction BACK_PICKUP_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static double pickupPower = 0.8;
    private boolean active = false;
//    private double duration = 1.0;
    private final ElapsedTime timer = new ElapsedTime();


    public Pickup(DcMotor front, DcMotor back){
        this.pickupMotorFront = front;
        this.pickupMotorFront.setDirection(FRONT_PICKUP_DIRECTION);

        this.pickupMotorBack = back;
        this.pickupMotorBack.setDirection(BACK_PICKUP_DIRECTION);
    }

    public void activate(){
        this.active = true;
        pickupMotorFront.setPower(pickupPower);
        pickupMotorBack.setPower(pickupPower);
    }

    public void stop(){
        this.active = false;
        pickupMotorFront.setPower(0.0);
        pickupMotorBack.setPower(0.0);
    }
    public void setPickupPower(double power){
        pickupPower = power;
    }

    public void addTelemetry(Telemetry tm){
        tm.addData("Active?", this.active);
    }

//    public void timedPickup(){
//        if(!this.active){
//            this.active = true;
//            this.timer.reset();
//            this.motor.setPower(power);
//        }
//    }
//    public void update() {
//        if(active && timer.seconds() > duration){
//            motor.setPower(0.0);
//            active = false;
//        }
//    }
//    public void continuousPickup(){
//        this.motor.setPower(power);
//    }
//    public void stop(){
//        this.motor.setPower(0.0);
//    }
//
//    public void flip(){
//        this.stop();
//        if(this.direction == DcMotorSimple.Direction.REVERSE){
//            this.direction = DcMotorSimple.Direction.FORWARD;
//        }
//        else if(this.direction == DcMotorSimple.Direction.FORWARD){
//            this.direction = DcMotorSimple.Direction.REVERSE;
//
//        }
//    }
//    public void setPower(double power){
//        this.power = power;
//    }
//
//    public void addTelemetry(Telemetry tl){
//        tl.addData("Pickup Power", power);
//        tl.addData("Pickup Direction", direction);
//        tl.addData("Pickup Active", active);
//    }

}
