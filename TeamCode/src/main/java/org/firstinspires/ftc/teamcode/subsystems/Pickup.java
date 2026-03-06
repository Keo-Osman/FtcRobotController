package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {
    private DcMotor motor;
    private DcMotorSimple.Direction direction = DcMotorSimple.Direction.FORWARD;
    private double power = 1.0;
    private boolean active = false;
    private final ElapsedTime timer = new ElapsedTime();


    public Pickup(DcMotor motor){
        this.motor = motor;
        this.motor.setDirection(direction);
    }

    public void update(boolean button) {
        if (button) {
            motor.setPower(power);
            active = true;
        }
        else{
            motor.setPower(0.0);
            active = false;
        }
    }

    public void flip(){
        if(this.direction == DcMotorSimple.Direction.REVERSE){
            this.direction = DcMotorSimple.Direction.FORWARD;
        }
        else if(this.direction == DcMotorSimple.Direction.FORWARD){
            this.direction = DcMotorSimple.Direction.REVERSE;

        }
    }

    public void setPower(double power){
        this.power = power;
    }
    public void addTelemetry(Telemetry tl){
        tl.addData("Pickup Power", power);
        tl.addData("Pickup Direction", direction);
        tl.addData("Pickup Active", active);
    }

}
