package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.RobotUtilities.HardwareWolviCube;

@TeleOp(name="Test Outtake", group="Test")
public class TEST_outtake extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareWolviCube robot         = new HardwareWolviCube();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {
        double frontLeftPower;
        double frontRightPower;
        double backLeftPower;
        double backRightPower;
        double max;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Becario");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.frontLeft.setPower(0.9);
        robot.frontRight.setPower(0.9);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.a) {
                telemetry.addData("Shooter", "Shotting");
                telemetry.update();
                robot.outtakeShooter.setPosition(0);
            } else {
                telemetry.addData("Shooter", "Waiting to shoot");
                telemetry.update();
                robot.outtakeShooter.setPosition(1);
            }
        }
        robot.frontLeft.setPower(0);
    }
}
