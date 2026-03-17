package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Port Test", group = "Test")
@Disabled
public class PortTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motorCH0 = hardwareMap.get(DcMotor.class, "ch0");
        DcMotor motorCH1 = hardwareMap.get(DcMotor.class, "ch1");
        DcMotor motorCH2 = hardwareMap.get(DcMotor.class, "ch2");
        DcMotor motorCH3 = hardwareMap.get(DcMotor.class, "ch3");

        DcMotor motorEH0 = hardwareMap.get(DcMotor.class, "eh0");
        DcMotor motorEH1 = hardwareMap.get(DcMotor.class, "eh1");
        DcMotor motorEH2 = hardwareMap.get(DcMotor.class, "eh2");
        DcMotor motorEH3 = hardwareMap.get(DcMotor.class, "eh3");
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.triangle){
                motorCH0.setPower(1.0);
            }else{
                motorCH0.setPower(0.0);
            }
            if(gamepad1.circle){
                motorCH1.setPower(1.0);
            }else{
                motorCH1.setPower(0.0);
            }
            if(gamepad1.cross){
                motorCH2.setPower(1.0);
            }else{
                motorCH2.setPower(0.0);
            }
            if(gamepad1.square){
                motorCH3.setPower(1.0);
            }else{
                motorCH3.setPower(0.0);
            }

            if(gamepad1.dpad_up){
                motorEH0.setPower(1.0);
            }else{
                motorEH0.setPower(0.0);
            }
            if(gamepad1.dpad_down){
                motorEH1.setPower(1.0);
            }else{
                motorEH1.setPower(0.0);
            }
            if(gamepad1.dpad_left){
                motorEH2.setPower(1.0);
            }else{
                motorEH2.setPower(0.0);
            }
            if(gamepad1.dpad_right){
                motorEH3.setPower(1.0);
            }else{
                motorEH3.setPower(0.0);
            }
        }

    }
}
