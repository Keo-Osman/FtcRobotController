package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="CRServo Test", group="Linear OpMode")
public class CRServoTest extends LinearOpMode {
    private CRServo crServo;

    // CONFIGURE AS CONTINUOUS SERVO FOR THIS?

    // CHECK CAREFULLY IF MOVING COULD BE MECHANICAL ISSUE - DISCONNECT GEAR IF NOT
    // ENSURE SERVO WIRED CORRECTLY AND PORT MATCHES CONFIG
    // MAKE SURE NAME IS shoot_servo
    @Override
    public void runOpMode() {
        crServo = hardwareMap.get(CRServo.class, "shoot_servo");
        telemetry.addData("CRServo Position", crServo.getPower());
        telemetry.addData("CRServo Direction", crServo.getDirection().toString());
        waitForStart();

        while (opModeIsActive()) {
            crServo.setPower(1.0);
            sleep(3000);
            crServo.setPower(-1.0);
            sleep(3000);
        }
    }
}