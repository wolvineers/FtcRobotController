package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleGoalArm {

    public Servo arm = null;
    public Servo clamp = null;

    public WobbleGoalArm(Servo a, Servo c) {
        arm     = a;
        clamp   = c;
    }

    public void raiseArm() {
        arm.setPosition(0);
    }

    public void lowerArm () {
        arm.setPosition(1);
    }

    public void openClamp () {
        clamp.setPosition(0);
    }

    public void closeClamp () {
        clamp.setPosition(1);
    }

    public void init () {
        arm.setPosition(1);     // arm raised
        clamp.setPosition(0);   // clamp opened
    }
}
