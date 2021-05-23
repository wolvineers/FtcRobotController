package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.WolviCube;

@TeleOp(name="Teleop", group="Definitius")
public class Teleop extends LinearOpMode {

    WolviCube robot = new WolviCube();

    int shoots = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Robot initialization
        HardwareMap hwMap = hardwareMap;
        robot.addModulesToRobot2021(hwMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                telemetry.addData("Shooting... ", String.valueOf(shoots), " try");
                robot.outtake.shoot(); shoots++;
            }
            if (gamepad2.dpad_up) {
                telemetry.addData("Lifting...", "up");
                robot.lift.up();
            }
            if (gamepad2.dpad_down){
                telemetry.addData("Lifting...", "down");
                robot.lift.down();
            }

            robot.dt.applyMovement(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x);

            telemetry.update();
        }

    }
}
