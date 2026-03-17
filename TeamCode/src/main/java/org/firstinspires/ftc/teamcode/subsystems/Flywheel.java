package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Flywheel {
    public enum MotorType{
        BACK,
        FRONT
    }

    private DcMotor backMotor, frontMotor;

    private static final double FRONT_POWER = 1.0;
    private static final double BACK_POWER = 1.0;
    private static final DcMotorSimple.Direction FRONT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    private static final DcMotorSimple.Direction BACK_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private ElapsedTime rampTimer = new ElapsedTime();
    public boolean rampingUp = false;
    public boolean rampingDown = false;
    private double rampDuration = 2.0;
    private double currentPowerFront = 0.0;
    private double currentPowerBack = 0.0;


    public Flywheel(DcMotor motorBack, DcMotor motorFront){
        this.backMotor = motorBack;
        this.backMotor.setDirection(BACK_MOTOR_DIRECTION);

        this.frontMotor = motorFront;
        this.frontMotor.setDirection(FRONT_MOTOR_DIRECTION);
    }


    public void startRampUp(){
        if(!this.rampingUp && !this.rampingDown) {
            this.rampingUp = true;
            this.rampTimer.reset();
        }
    }

    public void startRampDown(){
        if(!this.rampingUp && !this.rampingDown) {
            this.rampingDown = true;
            this.rampTimer.reset();
        }
    }
    public void update(){
        if(rampingUp) {
            double t = Math.min(rampTimer.seconds() / rampDuration, 1.0);
            double smooth = t * t * (3 - 2 * t);
            this.currentPowerFront = smooth * FRONT_POWER;
            this.currentPowerBack = smooth * BACK_POWER;
            this.frontMotor.setPower(currentPowerFront);
            this.backMotor.setPower(currentPowerBack);
            if (t >= 1) {
                rampingUp = false;
            }
        }
        if(rampingDown){
            double t = Math.min(rampTimer.seconds() / rampDuration, 1.0);
            double smooth = 1 - t * t * (3 - 2 * t);
            this.currentPowerFront = smooth * FRONT_POWER;
            this.currentPowerBack = smooth * BACK_POWER;
            this.frontMotor.setPower(currentPowerFront);
            this.backMotor.setPower(currentPowerBack);
            if (t >= 1) {
                rampingDown = false;
            }
        }
    }

    public void stop(){
        rampingDown = false;
        rampingUp = false;
        setPower(MotorType.FRONT, 0.0);
        setPower(MotorType.BACK, 0.0);
    }

    public void setPower(MotorType motorType, double power){
        if(motorType == MotorType.BACK){
            this.currentPowerBack = power;
            this.backMotor.setPower(power);
        }
        else if(motorType == MotorType.FRONT){
            this.currentPowerFront = power;
            this.frontMotor.setPower(power);

        }
    }

    public void addTelemetry(Telemetry telemetry){
        telemetry.addData("Ramping Up", this.rampingUp);
        telemetry.addData("Front Motor Power: ", this.frontMotor.getPower());
        telemetry.addData("Back Motor Power: ", this.backMotor.getPower());
    }
}











//public void setPowerDirection(MotorType motorType, double power, DcMotorSimple.Direction direction){
//    if(motorType == MotorType.BACK){
//        this.backMotor.setDirection(direction);
//        this.backMotor.setPower(power);
//
//    }
//    else if(motorType == MotorType.FRONT){
//        this.frontMotor.setDirection(direction);
//        this.frontMotor.setPower(power);
//
//    }
//}


//    public void continuousSpin(){
//        setPowerDirection(MotorType.BACK, backMotorShootPower, backMotorDirection);
//        setPowerDirection(MotorType.FRONT, frontMotorShootPower, frontMotorDirection);
//        active = true;
//    }
//
//    public void changeMotorConfig(MotorType motorType, double power, DcMotorSimple.Direction direction){
//        if(motorType == MotorType.BACK){
//            this.backMotorShootPower = power;
//            this.backMotorDirection = direction;
//        }
//        else{
//            this.frontMotorShootPower = power;
//            this.frontMotorDirection = direction;
//        }
//    }

//public void setShootDuration(double seconds) {
//    this.shootDuration = seconds;
//}

//    public void stop() {
//        setPowerDirection(MotorType.BACK, 0.0, backMotorDirection);
//        setPowerDirection(MotorType.FRONT, 0.0, frontMotorDirection);
//        active = false;
//    }
//
//    public void flip(){
//        if(frontMotorDirection == DcMotorSimple.Direction.REVERSE){
//            frontMotorDirection = DcMotorSimple.Direction.FORWARD;
//        }
//        else{
//            frontMotorDirection = DcMotorSimple.Direction.REVERSE;
//        }
//
//        if(backMotorDirection == DcMotorSimple.Direction.REVERSE){
//            backMotorDirection = DcMotorSimple.Direction.FORWARD;
//        }
//        else{
//            backMotorDirection = DcMotorSimple.Direction.REVERSE;
//        }
//    }
