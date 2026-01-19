package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GamepadTelemetry {
    public void Sticks(Telemetry telemetry, Gamepad gamepad) {
        telemetry.addData("Stick - Lx", gamepad.left_stick_x);
        telemetry.addData("Stick - Ly", gamepad.left_stick_y);
        telemetry.addData("Stick - Ls", gamepad.left_stick_button);
        telemetry.addData("Stick - Rx", gamepad.right_stick_x);
        telemetry.addData("Stick - Ry", gamepad.right_stick_y);
    }
    public void Triggers(Telemetry telemetry, Gamepad gamepad) {
        telemetry.addData("Lb", gamepad.left_bumper);
        telemetry.addData("Lt", gamepad.left_trigger);
        telemetry.addData("Rb", gamepad.right_bumper);
        telemetry.addData("Rt", gamepad.right_trigger);
    }

    public void ABXY(Telemetry telemetry, Gamepad gamepad) {
        telemetry.addData("a", gamepad.a);
        telemetry.addData("b", gamepad.b);
        telemetry.addData("x", gamepad.x);
        telemetry.addData("y", gamepad.y);
    }

    public void DPad(Telemetry telemetry, Gamepad gamepad) {
        telemetry.addData("D up", gamepad.dpad_up);
        telemetry.addData("D down", gamepad.dpad_down);
        telemetry.addData("D left", gamepad.dpad_left);
        telemetry.addData("D right", gamepad.dpad_right);

    }

    public void CCST(Telemetry telemetry, Gamepad gamepad){
        telemetry.addData("circle", gamepad.circle);
        telemetry.addData("cross", gamepad.cross);
        telemetry.addData("square", gamepad.square);
        telemetry.addData("triangle", gamepad.triangle);

    }
}
