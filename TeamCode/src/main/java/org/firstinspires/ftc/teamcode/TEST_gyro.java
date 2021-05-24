package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Outtake;
import org.firstinspires.ftc.teamcode.Utilities.WolviCube;
import org.firstinspires.ftc.teamcode.Utilities.Gyro;

@TeleOp(name="Test gyro", group="Test")
public class TEST_gyro extends LinearOpMode {

    WolviCube robot = new WolviCube();

    public void initRobot() {
        HardwareMap hwMap = hardwareMap;

        Gyro gyro = new Gyro(hwMap.get(BNO055IMU.class, "imu"));
    }

    @Override
    public void runOpMode() {
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        initRobot();

        waitForStart();

        robot.gyro.init();
        robot.gyro.resetAngle();

        while (opModeIsActive()) {
            double global = robot.gyro.getAngle();
            telemetry.addData("Angle:", globals);
            telemetry.update();
        }
    }
}
