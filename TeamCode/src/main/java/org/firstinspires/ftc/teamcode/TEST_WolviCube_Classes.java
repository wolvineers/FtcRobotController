package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Utilities.WolviCube;

@TeleOp(name="Test Lift", group="Test")
public class TEST_WolviCube_Classes extends LinearOpMode {

    WolviCube robot = new WolviCube();

    public void initRobot() {
        HardwareMap hwMap = hardwareMap;

        // Drivetrain
        /*Drivetrain dt = new Drivetrain(hwMap.get(DcMotor.class, "frontLeft"),
                hwMap.get(DcMotor .class,"frontRight"),
                hwMap.get(DcMotor .class,"backLeft"),
                hwMap.get(DcMotor .class,"backRight"));
        robot.setDrivetrain(dt);*/

        // Lift
        Lift lift = new Lift(hwMap.get(DcMotor.class, "lift"),
                hwMap.get(AnalogInput.class, "liftTouchUp"),
                hwMap.get(AnalogInput.class, "liftTouchDown"));
        robot.setLift(lift);
    }

    @Override
    public void runOpMode() {
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        initRobot();

        waitForStart();

        telemetry.addData("INITIALIZATING LIFT WITH UP OR DOWN...", "END WITH A");
        telemetry.update();
        robot.lift.init(gamepad1);

        DcMotor m1 = hardwareMap.get(DcMotor .class,"outtake_l");
        m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m1.setDirection(DcMotor.Direction.FORWARD);

        DcMotor m2 = hardwareMap.get(DcMotor .class,"outtake_r");
        m2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m2.setDirection(DcMotor.Direction.FORWARD);

        //telemetry.addData("LET'S CONTROL...", "YES WE CAAAAN");
        //telemetry.update();

        m1.setPower(-1);
        m2.setPower(1);

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                robot.lift.up();
            } else if (gamepad1.dpad_down) {
                robot.lift.down();
            }
        }
    }
}
