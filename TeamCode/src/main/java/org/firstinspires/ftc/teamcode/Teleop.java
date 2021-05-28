package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.WolviCube;

@TeleOp(name="Teleop", group="Definitius")
public class Teleop extends LinearOpMode {

    WolviCube robot = new WolviCube();

    int shoots = 0;
    boolean movementBlocked = false;
    boolean movementUnblocked = false;

    @Override
    public void runOpMode() throws InterruptedException {
        // Robot initialization
        HardwareMap hwMap = hardwareMap;
        robot.addModulesToRobot2021(hwMap);

        waitForStart();

        while (opModeIsActive()) {
            // INITIALIZATION
            movementBlocked = false;

            // GAMEPAD 1 - buttons distribution

            if (gamepad1.b) {
                telemetry.addData("Shooting... ", String.valueOf(shoots), " try");
                robot.outtake.shoot(); shoots++;
            }

            if (gamepad1.a) {
                movementBlocked = !movementBlocked;
                if (movementBlocked) telemetry.addData("Movement...", "on all directions");
                else telemetry.addData("Movement...", "on X axe");
                sleep(500);
            }

            if (gamepad1.left_bumper) {
                // Disminuir la potència
            }

            if (gamepad1.right_bumper) {
                // Augmentar potència
            }


            // GAMMEPAD 2 - buttons distribution

            if (gamepad2.dpad_up) {
                telemetry.addData("Lifting...", "up");
                robot.outtake.activated();
                robot.intake.deactivated();
                robot.lift.up();
            }

            if (gamepad2.dpad_down) {
                telemetry.addData("Lifting...", "down");
                robot.outtake.deactivated();
                robot.intake.activated();
                robot.lift.down();
            }

            if (gamepad2.y) {
                // Pujar baldufa
            }

            if (gamepad2.b) {
                // Tancar pinça
            }

            if (gamepad2.y) {
                // Baixar baldufa
            }

            if (gamepad2.x) {
                // Obrir pinça
            }

            if (movementBlocked) robot.dt.applyMovementOnX(-gamepad1.left_stick_x);
            else robot.dt.applyMovement(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x);

            telemetry.update();
        }

    }
}
