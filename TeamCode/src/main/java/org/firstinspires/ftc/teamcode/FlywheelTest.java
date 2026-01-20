package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Flywheel;

public class FlywheelTest extends LinearOpMode {
    private Flywheel flywheel;
    private final DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    private final DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;
    @Override
    public void runOpMode() {
        DcMotor motor1 = hardwareMap.get(DcMotor.class, "flywheel_motor_1");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "flywheel_motor_2");
        flywheel = new Flywheel(motor1, motor2);

        waitForStart();
        flywheel.SetPowerDirectionFlywheel(1.0, FORWARD, 1.0, REVERSE);
        sleep(3000);
        flywheel.SetPowerDirectionFlywheel(1.0, REVERSE, 1.0, FORWARD);
    }
}
