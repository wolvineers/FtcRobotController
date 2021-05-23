package org.firstinspires.ftc.teamcode.Utilities;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;

public class WolviCube {
    public Lift lift;
    public Drivetrain dt;
    public Outtake outtake;

    /* Constructor */
    public WolviCube(){
    }

    public void addModulesToRobot2021(HardwareMap hwMap) {
        // Drivetrain
        Drivetrain dt = new Drivetrain(hwMap.get(DcMotor.class, "frontLeft"),
                hwMap.get(DcMotor .class,"frontRight"),
                hwMap.get(DcMotor .class,"backLeft"),
                hwMap.get(DcMotor .class,"backRight"));
        this.setDrivetrain(dt);

        // Lift
        Lift lift = new Lift(hwMap.get(DcMotor.class, "lift"),
                hwMap.get(AnalogInput.class, "liftTouchUp"),
                hwMap.get(AnalogInput.class, "liftTouchDown"));
        this.setLift(lift);

        // Outtake
        Outtake outtake = new Outtake(hwMap.get(DcMotor.class, "outtake_l"),
                hwMap.get(DcMotor.class, "outtake_r"),
                hwMap.get(Servo.class, "shooter"));
        this.setOuttake(outtake);
    }

    public void setDrivetrain(Drivetrain newDt){
        dt = newDt;
    }

    public void setLift(Lift newLift) { lift = newLift; }

    public void setOuttake(Outtake newOuttake) { outtake = newOuttake; }

 }

