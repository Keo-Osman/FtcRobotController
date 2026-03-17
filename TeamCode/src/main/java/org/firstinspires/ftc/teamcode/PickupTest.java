package org.firstinspires.ftc.teamcode;

//@TeleOp(name = "Pickup Test", group = "Test")
//@Disabled
////TODO fix
//public class PickupTest extends LinearOpMode {
//    boolean active = false;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        DcMotor pickupMotor = hardwareMap.get(DcMotor.class, "pickup_motor");
//        Pickup pickup = new Pickup(pickupMotor);
//        GamepadTelemetry gp = new GamepadTelemetry();
//        waitForStart();
//
//        while(opModeIsActive()){
//            if(gamepad1.left_trigger > 0.2) {
//                pickup.setPower(gamepad1.left_trigger);
//                this.active = true;
//            }else {
//                pickup.setPower(0.0);
//                this.active = false;
//            }
//            if(gamepad1.aWasPressed()){
//                pickup.flip();
//            }
//
//            pickup.update();
//            pickup.addTelemetry(telemetry);
//            gp.ABXY(telemetry, gamepad1);
//            gp.Triggers(telemetry, gamepad1);
//            telemetry.update();
//
//        }
//    }
//}
