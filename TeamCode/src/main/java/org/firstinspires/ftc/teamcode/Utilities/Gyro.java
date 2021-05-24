package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class Gyro {

    //////////////////// ATRIBUTES ////////////////////
    BNO055IMU               imu;
    Orientation             angles, lastAngles = new Orientation();
    double                  globalAngle, deltaAngle

    // Structure for IMU parameters
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    parameters.mode                 = BNO055IMU.SensorMode.IMU;
    parameters.angleUnit            = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit            = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.loggingEnabled       = false;


    public Gyro(BNO055IMU newImu) {
        imu = newImu;

        imu.initialize(parameters);

        // In the initialization gyro is calibrated
        while (!imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }
    }

    public void init() {
        imu.initialize(parameters);

        // In the initialization gyro is calibrated
        while (!imu.isGyroCalibrated()) {
            sleep(50);
            idle();
        }
    }

    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        globalAngle = 0;
    }

    public double getAngle() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180) {
            deltaAngle += 360;
        }
        else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }

        globalAngle += deltaAngle;
        lastAngles = angles;

        return globalAngle;
    }

}