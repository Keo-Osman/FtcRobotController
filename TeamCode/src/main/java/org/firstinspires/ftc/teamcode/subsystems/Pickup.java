package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pickup {
    private CRServo crServo;
    private final ElapsedTime timer = new ElapsedTime();


    public Pickup(CRServo servo){
        crServo = servo;
    }

    public void update(boolean active, Telemetry tl) {
        if (!active) {
            crServo.setPower(1.0);
            tl.addData("Pickup Active: ", true);
        }
        else{
            crServo.setPower(0.0);
            tl.addData("Pickup Active: ", false);
        }

    }

//    public void startShot() {
//        if (active) return;
//        active = true;
//        timer.reset();
//        servo.setPosition((MAX_POS + MIN_POS)/2);
//    }
//    public void setPosition(double position){
//        crServo.setPosition(position);
//    }
//    public void reverseDirection(){
//        if(direction == Servo.Direction.FORWARD){
//            direction = Servo.Direction.REVERSE;
//        }
//        if(direction == Servo.Direction.REVERSE){
//            direction = Servo.Direction.FORWARD;
//        }
//    }


}
