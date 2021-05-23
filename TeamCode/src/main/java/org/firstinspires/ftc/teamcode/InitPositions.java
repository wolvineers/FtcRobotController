package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.WolviCube;

@TeleOp(name="Initialization", group="Definitius")
public class InitPositions extends LinearOpMode {

    WolviCube robot = new WolviCube();

    @Override
    public void runOpMode() throws InterruptedException {
        // Robot initialization
        HardwareMap hwMap = hardwareMap;
        robot.addModulesToRobot2021(hwMap);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Initialization of:    ", "WolviCube");
            telemetry.addData("Control lift:    ", "a");
            telemetry.update();

            if (gamepad1.a) {
                telemetry.addData("Initializing lift... ", "Use pad of Gamepad1");
                telemetry.addData("Initializing lift... ", "Press 'y' to finish");
                telemetry.update();
                robot.lift.init(gamepad1);
            }
        }

    }
}
