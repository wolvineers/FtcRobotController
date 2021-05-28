package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Utilities.Gyro;

public class Drivetrain {

    /*
     *   Drivetrain motors
     *      - Specs: 320rpm,  537.6PPR
     *      - Documentation: https://www.gobilda.com/content/spec_sheets/5202-0002-0019_spec_sheet.pdf
     */
    public DcMotor  frontLeft   = null;
    public DcMotor  frontRight  = null;
    public DcMotor  backLeft    = null;
    public DcMotor  backRight   = null;

    public Gyro gyro = null;

    // Correction factor values
    public double strafingCorrection    = 1.1;
    public double turnCorrection        = 1;
    public double powerMotor            = .50; // 100%

    public Drivetrain (DcMotor fL, DcMotor fR, DcMotor bL, DcMotor bR) {
        frontLeft = fL;
        frontRight = fR;
        backLeft = bL;
        backRight = bR;

        // Set all motors to run without encoders.
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    // Increment [-1, 1]
    public double incrementPower(double increment) {
        powerMotor += increment;

        // Normalize power between -1 and 1
        if (powerMotor > 1) powerMotor = 1;
        else if (powerMotor < -1) powerMotor = -1;

        return powerMotor;
    }

    public void applyMovement(double left_x, double left_y, double right_x) {
        // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
        // In this mode the Left stick moves the robot, the Right stick turns left and right.
        double y = -left_y;
        double x = -left_x * strafingCorrection;
        double rx = right_x * turnCorrection;

        // Combine drive and turn for blended motion.
        double frontLeftPower = y + x + rx;
        double frontRightPower = y - x - rx;
        double backLeftPower = y - x + rx;
        double backRightPower = y + x - rx;

        // Extracted from: https://gm0.org/en/latest/docs/software/mecanum-drive.html
        // Put powers in the range of -1 to 1 only if they aren't already
        // Not checking would cause us to always drive at full speed
        if (Math.abs(frontLeftPower) > 1 || Math.abs(backLeftPower) > 1 || Math.abs(frontRightPower) > 1 || Math.abs(backRightPower) > 1 ) {
            // Find the largest power
            double max = 0;
            max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(Math.abs(frontRightPower), max);
            max = Math.max(Math.abs(backRightPower), max);

            // Divide everything by max (it's positive so we don't need to worry
            // about signs)
            frontLeftPower /= max;
            backLeftPower /= max;
            frontRightPower /= max;
            backRightPower /= max;
        }

        // Output the safe vales to the motor drives. [-1, 1]
        this.frontLeft.setPower(frontLeftPower);
        this.frontRight.setPower(frontRightPower);
        this.backLeft.setPower(backLeftPower);
        this.backRight.setPower(backRightPower);
    }

    public void applyMovementOnX(double left_x) {
        // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
        // In this mode the Left stick moves the robot, the Right stick turns left and right.
        double y = 0;
        double x = -left_x * strafingCorrection;
        double rx = 0;

        // Combine drive and turn for blended motion.
        double frontLeftPower = y + x + rx;
        double frontRightPower = y - x - rx;
        double backLeftPower = y - x + rx;
        double backRightPower = y + x - rx;

        // Output the safe vales to the motor drives. [-1, 1]
        this.frontLeft.setPower(frontLeftPower);
        this.frontRight.setPower(frontRightPower);
        this.backLeft.setPower(backLeftPower);
        this.backRight.setPower(backRightPower);
    }

    /*public void rotate(int degrees) {
        double frontLeftPower,  frontRightPower, backLeftPower, backRightPower;

        // Restart imu to rotate
        gyro.resetAngle();

        if (degrees < 0)
        {   // turn right.
            frontLeftPower = -powerMotor;
            frontRightPower = powerMotor;
            backLeftPower = -powerMotor;
            backRightPower = powerMotor;
        }
        else if (degrees > 0)
        {   // turn left.
            frontLeftPower = powerMotor;
            frontRightPower = -powerMotor;
            backLeftPower = powerMotor;
            backRightPower = -powerMotor;
        }
        else return;

        // Set power to rotate
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        // Rotate until turn is completed
        if (degrees < 0) {
            // On right turn we have to get off zero first.
            while (gyro.getAngle() == 0) {}
            while (gyro.getAngle() > degrees) {}
        }
        else {    // left turn.
            while (gyro.getAngle() < degrees) {}
        }

        // turn the motors off.
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        // wait for rotation to stop.
        //sleep(500); buscar quina classe és la bona

        // reset angle on new heading.
        gyro.resetAngle();
    }*/
}
