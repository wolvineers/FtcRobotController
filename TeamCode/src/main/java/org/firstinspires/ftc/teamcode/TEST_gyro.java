package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.Gyro;
import org.firstinspires.ftc.teamcode.Utilities.WolviCube;

@TeleOp(name="Test gyro", group="Test")
public class TEST_gyro extends LinearOpMode {

    WolviCube robot = new WolviCube();

    public void initRobot() {
        HardwareMap hwMap = hardwareMap;

        Gyro gyro = new Gyro(hwMap.get(BNO055IMU.class, "imu"));
        robot.setGyro(gyro);
    }

    @Override
    public void runOpMode() {
        // Wait for the game to start (driver presses PLAY)
        initRobot();

        robot.gyro.init();

        waitForStart();

        robot.gyro.resetAngle();

        int i = 0;
        while (opModeIsActive()) {
            double global = robot.gyro.getAngle();
            telemetry.addData("Angle:", global);
            telemetry.update();
        }
    }
}
