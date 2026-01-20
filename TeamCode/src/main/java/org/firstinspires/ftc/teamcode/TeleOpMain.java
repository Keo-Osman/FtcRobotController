package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.subsystems.GamepadTelemetry;
import org.firstinspires.ftc.teamcode.subsystems.Pickup;
import org.firstinspires.ftc.teamcode.subsystems.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.RobotVision;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name="Main TeleOp", group="Linear OpMode")
public class TeleOpMain extends LinearOpMode {
    private RobotHardware hardware;
    private Drive drive;
    private Pickup pickup;
    private RobotVision vision;
    private GamepadTelemetry gamepadTelemetry;
    private ElapsedTime runtime;
    private Flywheel flywheel;

    @Override
    public void runOpMode(){
        hardware = new RobotHardware(hardwareMap);
        drive = new Drive(hardware);
        vision = new RobotVision(hardware);
        pickup = new Pickup(hardware.pickupServo);
        flywheel = new Flywheel(hardware.flywheel1, hardware.flywheel2);
        gamepadTelemetry = new GamepadTelemetry();
        runtime = new ElapsedTime();

        // Webcam stream preview only available in opModeInInit (??)
        while(opModeInInit()){
            // Wait until streaming
            while (vision.visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
                sleep(10);
                telemetry.addLine("Waiting for Camera to initialise");
            }
            telemetry.addLine("Camera Initialised");
            vision.VisionTelemetry(telemetry, 1);
            telemetry.update();
        }

        waitForStart();
        telemetry.clearAll();
        runtime.reset();


        while (opModeIsActive()){
            pickup.update(gamepad1.a, telemetry);
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            drive.DriveRobotCentric(axial, lateral, yaw);


            telemetry.addData("Status", "Run Time: " + runtime.toString());

            vision.VisionTelemetry(telemetry, 1);
            drive.Telemetry(telemetry);
            flywheel.Telemetry(telemetry);

            gamepadTelemetry.Sticks(telemetry, gamepad1);
            gamepadTelemetry.DPad(telemetry, gamepad1);

            telemetry.update();
        }
    }


}


