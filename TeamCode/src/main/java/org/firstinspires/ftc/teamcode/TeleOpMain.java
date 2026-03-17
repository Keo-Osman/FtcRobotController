package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.subsystems.Pickup;
import org.firstinspires.ftc.teamcode.subsystems.RobotHardware;

@TeleOp(name="Main TeleOp", group="Competition")
public class TeleOpMain extends LinearOpMode {
    private RobotHardware hardware;
    private Drive drive;
    private Pickup pickup;
    private ElapsedTime runtime;
    private Flywheel flywheel;

    @Override
    public void runOpMode(){
        hardware = new RobotHardware(hardwareMap);
        drive = new Drive(hardware.frontLeftDrive, hardware.frontRightDrive, hardware.backLeftDrive, hardware.backRightDrive);
        flywheel = new Flywheel(hardware.flywheelBack, hardware.flywheelFront);
        pickup = new Pickup(hardware.pickupMotorFront, hardware.pickupMotorBack);
        runtime = new ElapsedTime();

        waitForStart();
        telemetry.clearAll();
        runtime.reset();


        while (opModeIsActive()){
            // Gamepad (1) controls drive
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            drive.driveRobotCentric(axial, lateral, yaw);


            // Gamepad (2) controls pickup and flywheel
            //Pickup controls
            if(gamepad2.dpad_up){
                pickup.activate();
            }
            else{
                pickup.stop();
            }

            // Main flywheel controls
            if(gamepad2.crossWasPressed()){
                flywheel.startRampUp();
            }
            if(gamepad2.squareWasPressed()){
                flywheel.startRampDown();
            }
            if(gamepad2.dpadDownWasPressed()){
                flywheel.stop();
            }

            // Controller flywheel override
            if(!flywheel.rampingUp && !flywheel.rampingDown) {
                flywheel.setPower(Flywheel.MotorType.BACK, gamepad2.left_trigger);
                flywheel.setPower(Flywheel.MotorType.FRONT, gamepad2.right_trigger);
            }


            flywheel.update();

            drive.addTelemetry(telemetry);
            flywheel.addTelemetry(telemetry);
            pickup.addTelemetry(telemetry);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}


