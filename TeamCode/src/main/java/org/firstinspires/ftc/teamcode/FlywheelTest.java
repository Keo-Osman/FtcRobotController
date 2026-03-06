package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.subsystems.GamepadTelemetry;

@TeleOp(name="Flywheel Test", group="Linear OpMode")
public class FlywheelTest extends LinearOpMode {
    private Flywheel flywheel;
    private GamepadTelemetry gpTelemetry;
    private boolean getGpTelemetry = false;

    @Override
    public void runOpMode() {
        gpTelemetry = new GamepadTelemetry();
        DcMotor frontMotor = hardwareMap.get(DcMotor.class, "flywheel_motor_front");
        DcMotor backMotor = hardwareMap.get(DcMotor.class, "flywheel_motor_back");
        telemetry.addData("Flywheel back", frontMotor);
        telemetry.addData("Flywheel front", backMotor);

        flywheel = new Flywheel(frontMotor, backMotor);

        waitForStart();


        telemetry.addLine("Front - REVERSE, Back FORWARD");
        flywheel.continuousSpin();
        while (!gamepad1.dpad_up){
            telemetry.addLine("Press dPad_Up to stop");
        }
        flywheel.stop();
        sleep(1000);

        telemetry.addLine("Front - FORWARD, Back REVERSE");
        flywheel.flip();
        flywheel.continuousSpin();
        while (!gamepad1.dpad_up){
            telemetry.addLine("Press dPad_Up to stop");
        }
        flywheel.stop();

        while (true){
            telemetry.addLine("Press dPad_Up to flip back, dPad_Down to continue");
            if(gamepad1.dpad_up){
                flywheel.flip();
                break;
            }
            else if (gamepad1.dpad_down) {
                break;
            }
        }


        while(opModeIsActive()) {
            if(gamepad1.dpadDownWasPressed()){
                flywheel.continuousSpin();
            }
            if(gamepad1.dpadUpWasPressed()){
                flywheel.timedShoot();
            }
            if(gamepad2.dpadRightWasPressed()){
                flywheel.stop();
            }

            flywheel.update();
            flywheel.addTelemetry(telemetry);

            if(gamepad1.dpadLeftWasPressed()){
                getGpTelemetry = !getGpTelemetry;
            }
            if(getGpTelemetry){
                gpTelemetry.DPad(telemetry, gamepad1);
            }

            telemetry.addLine("Press Dpad_Up to shoot, Dpad_Down to spin, Dpad_Right to stop, Dpad_Left to toggle telem.");
            telemetry.update();
        }
    }
}
