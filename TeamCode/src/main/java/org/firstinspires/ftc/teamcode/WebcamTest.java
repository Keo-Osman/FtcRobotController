package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.FocusControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.WhiteBalanceControl;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Webcam Test", group = "Test")
public class WebcamTest extends LinearOpMode{
//    private WebcamName webcam = hardwareMap.get(WebcamName.class, "Webcam 1");

//    private ExposureControl exposureControl = vuforia



    @Override
    public void runOpMode() {
        waitForStart();
        VisionPortal portal = new VisionPortal.Builder()
                //.addProcessor(colorLocator)
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setAutoStartStreamOnBuild(true)
                .build();
        // Wait until streaming
        while (portal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            sleep(10);
        }

        ExposureControl exposure = portal.getCameraControl(ExposureControl.class);
        long exposureMin, exposureMax;

        GainControl gain = portal.getCameraControl(GainControl.class);
        int gainMin, gainMax;
        WhiteBalanceControl whiteBalance = portal.getCameraControl(WhiteBalanceControl.class);
        FocusControl focus = portal.getCameraControl(FocusControl.class);
        double focusMin, focusMax;

        // Preview stream is only available during opModeInInit not opModeIsActive
        while(opModeIsActive() || opModeInInit()) {
            // Exposure
            exposureMin = exposure.getMinExposure(TimeUnit.MILLISECONDS);
            exposureMax = exposure.getMaxExposure(TimeUnit.MILLISECONDS);
            telemetry.addData("Exposure Supported? ", exposure.isExposureSupported());
            telemetry.addData("Exposure Range", String.valueOf(exposureMin), String.valueOf(exposureMax));
            telemetry.addData("Auto supported?", exposure.isModeSupported(ExposureControl.Mode.Auto));
            telemetry.addData("Manual supported?", exposure.isModeSupported(ExposureControl.Mode.Manual));

            boolean wasExposureSet = exposure.setExposure(25, TimeUnit.MILLISECONDS);
            boolean wasExposureModeSet = exposure.setMode(ExposureControl.Mode.Manual);
            telemetry.addData("Exposure Set Success?", wasExposureSet);
            telemetry.addData("Exposure Mode Set Success?", wasExposureModeSet);


            // Gain
            gainMin = gain.getMinGain();
            gainMax = gain.getMaxGain();
            telemetry.addData("Gain Range", String.valueOf(gainMin), String.valueOf(gainMax));

            boolean wasGainSet = gain.setGain(25);
            telemetry.addData("Gain Set Success?", wasGainSet);

            // White Balance
            boolean wasTemperatureSet = whiteBalance.setWhiteBalanceTemperature(3000);
            boolean wasWhiteBalanceModeSet = whiteBalance.setMode(WhiteBalanceControl.Mode.MANUAL);
            whiteBalance.getMaxWhiteBalanceTemperature();
            telemetry.addData("Temperature Set?", wasTemperatureSet);
            telemetry.addData("White Balance Mode?", wasWhiteBalanceModeSet);

            focusMin = focus.getMinFocusLength();
            focusMax = focus.getMaxFocusLength();
            boolean wasFocusSet = focus.setFocusLength(25);
            boolean wasFocusModeSet = focus.setMode(FocusControl.Mode.Fixed);
            telemetry.addData("Focus Length Set?", wasFocusSet);
            telemetry.addData("Focus Mode Set?", wasFocusModeSet);

            telemetry.update();
        }
    }

}
