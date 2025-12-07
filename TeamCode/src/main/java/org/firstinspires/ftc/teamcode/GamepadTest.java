package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


//Simple test that displays gamepad input values on telemetry data
@TeleOp(name="Gamepad Test", group="Linear OpMode")
public class GamepadTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()){
            //Sticks
            telemetry.addData("Stick - Lx", gamepad1.left_stick_x);
            telemetry.addData("Stick - Ly", gamepad1.left_stick_y);
            telemetry.addData("Stick - Ls", gamepad1.left_stick_button);
            telemetry.addData("Stick - Rx", gamepad1.right_stick_x);
            telemetry.addData("Stick - Ry", gamepad1.right_stick_y);

            //Triggers and Bumpers
            telemetry.addData("Lb", gamepad1.left_bumper);
            telemetry.addData("Lt", gamepad1.left_trigger);
            telemetry.addData("Rb", gamepad1.right_bumper);
            telemetry.addData("Rt", gamepad1.right_trigger);

            //Buttons
            telemetry.addData("a", gamepad1.a);
            telemetry.addData("b", gamepad1.b);
            telemetry.addData("x", gamepad1.x);
            telemetry.addData("y", gamepad1.y);
            telemetry.addData("circle", gamepad1.circle);
            telemetry.addData("cross", gamepad1.cross);
            telemetry.addData("square", gamepad1.square);
            telemetry.addData("triangle", gamepad1.triangle);

            //Dpad
            telemetry.addData("D up", gamepad1.dpad_up);
            telemetry.addData("D down", gamepad1.dpad_down);
            telemetry.addData("D left", gamepad1.dpad_left);
            telemetry.addData("D right", gamepad1.dpad_right);

            telemetry.update();
        }
    }
}
