package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.subsystems.GamepadTelemetry;
import org.firstinspires.ftc.teamcode.subsystems.Pickup;
import org.firstinspires.ftc.teamcode.subsystems.RobotHardware;

@TeleOp(name="Main TeleOp", group="Linear OpMode")
public class TeleOpMain extends LinearOpMode {
    private RobotHardware hardware;
    private Drive drive;
    private Pickup pickup;
//    private RobotVision vision;
    private GamepadTelemetry gamepadTelemetry;
    private ElapsedTime runtime;
    private Flywheel flywheel;

    @Override
    public void runOpMode(){
        hardware = new RobotHardware(hardwareMap);
        drive = new Drive(hardware);
//        vision = new RobotVision(hardware);
        pickup = new Pickup(hardware.pickupMotor);
        flywheel = new Flywheel(hardware.flywheelBack, hardware.flywheelFront);
        gamepadTelemetry = new GamepadTelemetry();
        runtime = new ElapsedTime();

        // Webcam stream preview only available in opModeInInit
//        while(opModeInInit()){
//            // Wait until streaming
//            while (vision.visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
//                sleep(10);
//                telemetry.addLine("Waiting for Camera to initialise");
//            }
//            telemetry.addLine("Camera Initialised");
//            vision.VisionTelemetry(telemetry, 1);
//            telemetry.update();
//        }

        waitForStart();
        telemetry.clearAll();
        runtime.reset();


        while (opModeIsActive()){

            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            drive.driveRobotCentric(axial, lateral, yaw);


            pickup.update(gamepad1.a);

            if(gamepad1.right_trigger > 0.2){
                flywheel.timedShoot();
            }
            if(gamepad1.left_trigger > 0.2){
                flywheel.continuousSpin();
            }
            flywheel.update();
            telemetry.addData("Status", "Run Time: " + runtime.toString());

//            vision.VisionTelemetry(telemetry, 1);
            drive.addTelemetry(telemetry);
            flywheel.addTelemetry(telemetry);
            pickup.addTelemetry(telemetry);

            gamepadTelemetry.Sticks(telemetry, gamepad1);
            gamepadTelemetry.DPad(telemetry, gamepad1);

            telemetry.update();
        }
    }


}


