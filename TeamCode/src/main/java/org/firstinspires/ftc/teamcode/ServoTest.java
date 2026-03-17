package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Servo Test", group="Linear OpMode")
@Disabled
public class ServoTest extends LinearOpMode {
    // REV 41-3334
    // TRY TO FIND SRS PROGRAMMER
    private Servo servo;

    // CHECK CAREFULLY IF MOVING COULD BE MECHANICAL ISSUE - DISCONNECT GEAR IF NOT
    // ENSURE SERVO WIRED CORRECTLY AND PORT MATCHES CONFIG
    // MAKE SURE NAME IS shoot_servo
    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "shoot_servo");
        telemetry.addData("Servo Position", servo.getPosition());
        telemetry.addData("Servo Direction", servo.getDirection().toString());
        waitForStart();

        while (opModeIsActive()) {
            servo.setPosition(0.0);
            sleep(3000);
            servo.setPosition(1.0);
            sleep(3000);
        }
    }
}

