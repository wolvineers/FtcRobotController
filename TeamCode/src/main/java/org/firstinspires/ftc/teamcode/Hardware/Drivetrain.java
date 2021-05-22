package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain {

    /*
     *   Drivetrain motors
     *      - Specs: 320rpm,  537.6PPR
     *      - Documentation: https://www.gobilda.com/content/spec_sheets/5202-0002-0019_spec_sheet.pdf
     */
    public DcMotor frontLeft   = null;
    public DcMotor  frontRight  = null;
    public DcMotor  backLeft    = null;
    public DcMotor  backRight   = null;

    public Drivetrain () {

        // Define and Initialize Motors
        frontLeft  = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backLeft   = hwMap.get(DcMotor.class, "backLeft");
        backRight  = hwMap.get(DcMotor.class, "backRight");

        // Set all motors to run without encoders.
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)

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

    public void applyMovement() {

    }
}
