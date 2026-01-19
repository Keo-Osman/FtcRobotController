package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Shooter {
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position
    public Servo.Direction direction = Servo.Direction.FORWARD;

    private final Servo servo;
    private final ElapsedTime timer = new ElapsedTime();

    private boolean active = false;

    public Shooter(Servo servo) {
        this.servo = servo;
    }

    public void startShot() {
        if (active) return;
        active = true;
        timer.reset();
        servo.setPosition((MAX_POS + MIN_POS)/2);
    }
    public void setPosition(double position){
        servo.setPosition(position);
    }
    public void reverseDirection(){
        if(direction == Servo.Direction.FORWARD){
            direction = Servo.Direction.REVERSE;
        }
        if(direction == Servo.Direction.REVERSE){
            direction = Servo.Direction.FORWARD;
        }
    }

    public void update() {
        if (!active) return;

        if (timer.seconds() > 0.35) {
//            motor.setPower(0.0);
            active = false;
        }
    }
}

