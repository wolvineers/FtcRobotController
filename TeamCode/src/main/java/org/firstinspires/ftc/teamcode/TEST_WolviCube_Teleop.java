package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

//import org.firstinspires.ftc.teamcode.HardwareWolviCube;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 */

@TeleOp(name="Test Mecanum - Gamepad", group="Test")
public class TEST_WolviCube_Teleop extends LinearOpMode {

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
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
            // In this mode the Left stick moves the robot, the Right stick turns left and right.
            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x * 1.5;
            double rx = gamepad1.right_stick_x * 0.75;

            // Combine drive and turn for blended motion.
            frontLeftPower = y + x + rx;
            frontRightPower = y - x - rx;
            backLeftPower = y - x + rx;
            backRightPower = y + x - rx;

            // Normalize the values so neither exceed +/- 1.0. Code from sample
            /*max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }*/
            telemetry.addData("FLP", frontLeftPower);    //
            telemetry.addData("FRP", frontRightPower);    //
            telemetry.addData("BLP", backLeftPower);    //
            telemetry.addData("BRP", backRightPower);    //
            telemetry.update();

            // Output the safe vales to the motor drives. [-1, 1]
            robot.frontLeft.setPower(frontLeftPower);
            robot.frontRight.setPower(frontRightPower);
            robot.backLeft.setPower(backLeftPower);
            robot.backRight.setPower(backRightPower);
        }
    }
}
