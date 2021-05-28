package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.RingPipelineCV;
import org.firstinspires.ftc.teamcode.Utilities.WolviCube;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Auto2", group="Tests")
public class Auto extends LinearOpMode {

    WolviCube robot = new WolviCube();

    // Camera vars
    OpenCvInternalCamera phoneCam;
    RingPipelineCV pipeline;


    @Override
    public void runOpMode() throws InterruptedException {
        // Robot initialization
        HardwareMap hwMap = hardwareMap;
        robot.addModulesToRobot2021(hwMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new RingPipelineCV();
        phoneCam.setPipeline(pipeline);

        waitForStart();

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                phoneCam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }
        });

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addData("Position", pipeline.getPosition());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }
    }
}

