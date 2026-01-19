package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.FocusControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.WhiteBalanceControl;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.concurrent.TimeUnit;

public class RobotVision {
    public VisionPortal visionPortal;
    private final int resolutionWidth = 320;
    private final int resolutionHeight = 240;

    ExposureControl exposure;
    long exposureMin, exposureMax;

    GainControl gain;
    int gainMin, gainMax;

    WhiteBalanceControl whiteBalance;
    int whiteBalanceMin, whiteBalanceMax;

    FocusControl focus;
    double focusMin, focusMax;


    public RobotVision(RobotHardware hardware){
        visionPortal = new VisionPortal.Builder()
                //.addProcessor(colorLocator)
                .setCameraResolution(new Size(resolutionWidth, resolutionHeight))
                .setCamera(hardware.webcam)
                .setAutoStartStreamOnBuild(true)
                .build();

        exposure = visionPortal.getCameraControl(ExposureControl.class);
        gain = visionPortal.getCameraControl(GainControl.class);
        whiteBalance = visionPortal.getCameraControl(WhiteBalanceControl.class);
        focus = visionPortal.getCameraControl(FocusControl.class);

        exposureMin = exposure.getMinExposure(TimeUnit.MILLISECONDS);
        exposureMax = exposure.getMaxExposure(TimeUnit.MILLISECONDS);

        gainMin = gain.getMinGain();
        gainMax = gain.getMaxGain();

        whiteBalanceMin = whiteBalance.getMinWhiteBalanceTemperature();
        whiteBalanceMax = whiteBalance.getMaxWhiteBalanceTemperature();

        focusMin = focus.getMinFocusLength();
        focusMax = focus.getMaxFocusLength();
        // TODO init settings to optimal settings
    }

    public void VisionTelemetry(Telemetry telemetry, int level){
        telemetry.addData("Gain: ", gain.getGain());
        telemetry.addData("Exposure: ", exposure.getExposure(TimeUnit.MILLISECONDS));
        telemetry.addData("White Balance: ", whiteBalance.getWhiteBalanceTemperature());
        telemetry.addData("Focus Length: ", focus.getFocusLength());

        if(level >= 1){
            telemetry.addData("Gain Range", String.valueOf(gainMin), String.valueOf(gainMax));
            telemetry.addData("Exposure Range", String.valueOf(exposureMin), String.valueOf(exposureMax));
            telemetry.addData("White Balance Range", String.valueOf(whiteBalanceMin), String.valueOf(whiteBalanceMax));
            telemetry.addData("Focus Range", String.valueOf(focusMin), String.valueOf(focusMax));
        }
        // TODO? If (level >= 2) modes supported
    }
}




