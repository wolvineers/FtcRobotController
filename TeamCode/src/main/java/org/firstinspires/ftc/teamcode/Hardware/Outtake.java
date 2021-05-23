package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake {
    public DcMotor left = null;
    public DcMotor right = null;

    public Servo shooter = null;

    public Outtake(DcMotor l, DcMotor r, Servo s) {
        left    = l;
        right   = r;
        shooter = s;

        // Set motor config
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setDirection(DcMotor.Direction.FORWARD);

        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setDirection(DcMotor.Direction.FORWARD);
    }

    public void activated() {
        left.setPower(-1);
        right.setPower(1);
    }

    public void deactivated() {
        left.setPower(0);
        right.setPower(0);
    }

    public void shoot(){
        shooter.setPosition(0);
        shooter.setPosition(1);
    }

    public void init(){
        left.setPower(0);
        right.setPower(0);
        shooter.setPosition(1);
    }
}
