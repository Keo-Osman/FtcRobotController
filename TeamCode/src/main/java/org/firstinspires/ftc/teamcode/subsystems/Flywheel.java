package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Flywheel {
    private DcMotor backMotor, frontMotor;

    private double frontMotorShootPower = 1.0;
    private double backMotorShootPower = 1.0;
    private DcMotorSimple.Direction frontMotorDirection = DcMotorSimple.Direction.REVERSE;
    private DcMotorSimple.Direction backMotorDirection = DcMotorSimple.Direction.FORWARD;

    private double shootDuration = 0.75;
    private ElapsedTime timer = new ElapsedTime();
    private boolean active = false;

    public enum MotorType{
        BACK,
        FRONT
    }

    public void timedShoot(){
        this.active = true;
        this.timer.reset();
        setPowerDirection(MotorType.BACK, backMotorShootPower, backMotorDirection);
        setPowerDirection(MotorType.FRONT, frontMotorShootPower, frontMotorDirection);
    }
    public void update(){
        if(active && timer.seconds() > shootDuration){
            setPowerDirection(MotorType.BACK, 0.0, backMotorDirection);
            setPowerDirection(MotorType.FRONT, 0.0, frontMotorDirection);
            active = false;
        }
    }

    public Flywheel(DcMotor motorBack, DcMotor motorFront){
        this.backMotor = motorBack;
        this.frontMotor = motorFront;
    }

    public void setPowerDirection(MotorType motorType, double power, DcMotorSimple.Direction direction){
        if(motorType == MotorType.BACK){
            this.backMotor.setDirection(direction);
            this.backMotor.setPower(power);

        }
        else if(motorType == MotorType.FRONT){
            this.frontMotor.setDirection(direction);
            this.frontMotor.setPower(power);

        }
    }
    public void continuousSpin(){
        setPowerDirection(MotorType.BACK, backMotorShootPower, backMotorDirection);
        setPowerDirection(MotorType.FRONT, frontMotorShootPower, frontMotorDirection);
        active = true;
    }

    public void changeMotorConfig(MotorType motorType, double power, DcMotorSimple.Direction direction){
        if(motorType == MotorType.BACK){
            this.backMotorShootPower = power;
            this.backMotorDirection = direction;
        }
        else{
            this.frontMotorShootPower = power;
            this.frontMotorDirection = direction;
        }
    }

    public void setShootDuration(double seconds) {
        this.shootDuration = seconds;
    }

    public void stop() {
        setPowerDirection(MotorType.BACK, 0.0, backMotorDirection);
        setPowerDirection(MotorType.FRONT, 0.0, frontMotorDirection);
        active = false;
    }

    public void flip(){
        if(frontMotorDirection == DcMotorSimple.Direction.REVERSE){
            frontMotorDirection = DcMotorSimple.Direction.FORWARD;
        }
        else{
            frontMotorDirection = DcMotorSimple.Direction.REVERSE;
        }

        if(backMotorDirection == DcMotorSimple.Direction.REVERSE){
            backMotorDirection = DcMotorSimple.Direction.FORWARD;
        }
        else{
            backMotorDirection = DcMotorSimple.Direction.REVERSE;
        }
    }



    public void addTelemetry(Telemetry telemetry){
        telemetry.addData("Shot Active?", this.active);

        telemetry.addData("Front Motor Power: ", this.frontMotor.getPower());
        telemetry.addData("Front Motor Direction: ", this.frontMotor.getDirection());

        telemetry.addData("Back Motor Power: ", this.backMotor.getPower());
        telemetry.addData("Back Motor Direction: ", this.backMotor.getDirection());
    }
}
