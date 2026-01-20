package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Flywheel {
    private DcMotor[] motors;
    public Flywheel(DcMotor motor1, DcMotor motor2){
        this.motors[0] = motor1;
        this.motors[1] = motor2;
    }

    public void SetPowerDirectionFlywheel(double power1, DcMotor.Direction direction1, double power2, DcMotor.Direction direction2){
        SetPowerDirectionMotor(power1, direction1, 1);
        SetPowerDirectionMotor(power2, direction2, 2);
    }

    // Takes int 1 or 2 to set motors[0] or motors[1]
    public void SetPowerDirectionMotor(double power, DcMotorSimple.Direction direction, int motorNumber){
        motors[motorNumber - 1].setDirection(direction);
        motors[motorNumber - 1].setPower(power);
    }

    public void Telemetry(Telemetry telemetry){
        telemetry.addData("Motor 1 Power: ", motors[0].getPower());
        telemetry.addData("Motor 2 Power: ", motors[1].getPower());
        telemetry.addData("Motor 1 Direction: ", motors[0].getDirection());
        telemetry.addData("Motor 2 Direction: ", motors[1].getDirection());
    }

}
