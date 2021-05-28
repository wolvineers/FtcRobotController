package org.firstinspires.ftc.teamcode.Utilities;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;
import org.firstinspires.ftc.teamcode.Hardware.WobbleGoalArm;

public class WolviCube {
    public Lift lift                        = null;
    public Drivetrain dt                    = null;
    public Outtake outtake                  = null;
    public Intake intake                    = null;
    public WobbleGoalArm wobbleGoalArm      = null;
    public Gyro gyro                        = null;

    /* Constructor */
    public WolviCube(){
    }

    public void addModulesToRobot2021(HardwareMap hwMap) {

        // Gyro
        //Gyro gyro = new Gyro()

        // Drivetrain
        Drivetrain dt = new Drivetrain(hwMap.get(DcMotor.class, "frontLeft"),
                hwMap.get(DcMotor .class,"frontRight"),
                hwMap.get(DcMotor .class,"backLeft"),
                hwMap.get(DcMotor .class,"backRight"));
        this.setDrivetrain(dt);

        //this.dt.setGyro(gyro);

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

        // Outtake
        Intake intake = new Intake(hwMap.get(DcMotor.class, "intake"));
        this.setIntake(intake);

        // Wobble Goal Arm
        WobbleGoalArm wobbleGoalArm = new WobbleGoalArm(hwMap.get(Servo.class, "arm"),
                hwMap.get(Servo.class, "clamp"));
        this.setWobbleGoalArm(wobbleGoalArm);
    }

    public void setDrivetrain(Drivetrain newDt){
        dt = newDt;
    }

    public void setLift(Lift newLift) { lift = newLift; }

    public void setOuttake(Outtake newOuttake) { outtake = newOuttake; }

    public void setIntake(Intake newIntake) { intake = newIntake; }

    public void setWobbleGoalArm(WobbleGoalArm newWobbleGoalArm) { wobbleGoalArm = newWobbleGoalArm; }

    public void setGyro(Gyro newGyro) { gyro = newGyro; }

}

