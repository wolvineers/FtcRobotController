package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//import org.firstinspires.ftc.teamcode.HardwareWolviCube;

@TeleOp(name="Test Encoder", group="Test")
public class TEST_encoder extends LinearOpMode {

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
        robot.resetEncoder(5);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.addData("Motor Encoder", robot.frontLeft.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.frontLeft.setPower(0.1);
        // run until the end of the match (driver presses STOP)
        //robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (opModeIsActive() && robot.frontLeft.getCurrentPosition()< 538) {
            telemetry.addData("Moving motor", robot.frontLeft.getCurrentPosition());
            telemetry.update();
        }
        robot.frontLeft.setPower(0);
    }
}
