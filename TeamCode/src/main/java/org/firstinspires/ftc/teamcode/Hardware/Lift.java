package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift {

    //////////////////// ATRIBUTES ////////////////////

    public DcMotor motor                    = null;
    public AnalogInput limitSwitch_up       = null;
    public AnalogInput limitSwitch_down     = null;

    public Lift(DcMotor m, AnalogInput up, AnalogInput down) {
        motor               = m;
        limitSwitch_up      = up;
        limitSwitch_down    = down;

        // Set motor config
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void up() {
        motor.setPower(-1);

        while (limitSwitch_up.getVoltage()>1) { } // Our limit switch is normally closed

        motor.setPower(0);
    }

    public void down() {
        motor.setPower(1);

        while (limitSwitch_down.getVoltage()>1) { } // Our limit switch is normally closed

        motor.setPower(0);
    }

    public void init() {
        // Motor power at 0.5 to go down until detect limit switch.
        motor.setPower(0.5);
        while (limitSwitch_down.getVoltage()>1) { } // Our limit switch is normally closed

        // Reset encoder
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void init(Gamepad gamepad) {
        while (!gamepad.a) {
            if (gamepad.dpad_down){
                motor.setPower(0.5);
            } else if (gamepad.dpad_up) {
                motor.setPower(-0.5);
            } else{
                motor.setPower(0);
            }
        }

        // Reset encoder
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


}
