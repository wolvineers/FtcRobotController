package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {

    //////////////////// CONSTANTS ////////////////////

    private final double DISTANCE_TO_LIFT = -950;    // Motor steps from encoder

    //////////////////// ATRIBUTES ////////////////////
    public enum a_pos {
        UP, DOWN,
    }

    public DcMotor motor = null;

    public Lift(DcMotor m) {
        motor = m;

        // Set motor config
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void up(Telemetry telemetry) {
        // Pujar
        motor.setPower(-1);

        while (motor.getCurrentPosition() > DISTANCE_TO_LIFT) {
            telemetry.addData("Which", "Up");
            telemetry.addData("Moving motor", motor.getCurrentPosition());
            telemetry.update();
        }

        motor.setPower(0);
    }

    public void down(Telemetry telemetry) {
        // Baixar
        motor.setPower(1);

        while (motor.getCurrentPosition() < 0) {
            telemetry.addData("Which", "Down");
            telemetry.addData("Moving motor", motor.getCurrentPosition());
            telemetry.update();
        }

        motor.setPower(0);
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
