package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    public DcMotor motor = null;

    public Intake(DcMotor m) {
        motor = m;

        // Set motor config
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void activated() {
        motor.setPower(-1);
    }

    public void deactivated() {
        motor.setPower(0);
    }

    public void init() {
        motor.setPower(0);
    }
}
