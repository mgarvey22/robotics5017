//comment out attachments since this is for this year
//FORWARD means REVERSE for the left wheels

package org.firstinspires.ftc.teamcode; //importing OUR package

import com.qualcomm.robotcore.eventloop.opmode.Autonomous; //auto this time, importing
//importing OpModes (linear and teleOp) and importing hardware (motors, sensors, servos)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//importing servos, motors, touch sensors
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "AUTOSHOOTENCODERS", group = "") //name of the file

public class AUTOSHOOTENCODERS extends LinearOpMode { //creating public class, extension of linear opmode

    //creating motors, touch sensors, and servos
    private DcMotor RIGHTFRONT;
    private DcMotor RIGHTBACK;
    private DcMotor LEFTFRONT;
    private DcMotor LEFTBACK;
    private DcMotor SHOOTER;
    private Servo FLICKER;

    final int encRotation = 538;

    @Override
    public void runOpMode() {
        RIGHTFRONT = hardwareMap.dcMotor.get("RIGHTFRONT");
        RIGHTBACK = hardwareMap.dcMotor.get("RIGHTBACK");
        LEFTFRONT = hardwareMap.dcMotor.get("LEFTFRONT");
        LEFTBACK = hardwareMap.dcMotor.get("LEFTBACK");
        SHOOTER = hardwareMap.dcMotor.get("SHOOTER");
        //WOBBLE = hardwareMap.dcMotor.get("WOBBLE");
        //INTAKE = hardwareMap.dcMotor.get("INTAKE");
        FLICKER = hardwareMap.servo.get("FLICKER");

        RIGHTFRONT.setDirection(DcMotorSimple.Direction.REVERSE);
        RIGHTBACK.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {
            minimalEncoderTest();
        }
    }

    private void stopEverything() {
        LEFTFRONT.setPower(0);
        RIGHTFRONT.setPower(0);
        LEFTBACK.setPower(0);
        RIGHTBACK.setPower(0);
    }

    // Just a tester function for the encoders.
    private void minimalEncoderTest() {
        int denc = 20;

        LEFTFRONT.setDirection(DcMotorSimple.Direction.REVERSE);
        LEFTFRONT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LEFTFRONT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LEFTFRONT.setTargetPosition(denc);
        LEFTFRONT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LEFTFRONT.setPower(0.5);

        //while (LEFTFRONT.isBusy()) {
            //idle();
        //}
        sleep(500);
        stopEverything();
    }

    // SHOULD USE ENCODERS PROPERLY, TEST ON TUESDAY
    private void ForwardForDist(double power, double revolutions) {
        int denc = (int)Math.round(revolutions * encRotation);

        RIGHTFRONT.setDirection(DcMotorSimple.Direction.FORWARD);
        LEFTFRONT.setDirection(DcMotorSimple.Direction.REVERSE);
        RIGHTBACK.setDirection(DcMotorSimple.Direction.REVERSE);
        LEFTBACK.setDirection(DcMotorSimple.Direction.FORWARD);

        RIGHTFRONT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LEFTFRONT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RIGHTBACK.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LEFTBACK.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RIGHTFRONT.setPower(power);
        LEFTFRONT.setPower(power);
        RIGHTBACK.setPower(power);
        LEFTBACK.setPower(power);

        RIGHTFRONT.setTargetPosition(denc);
        LEFTBACK.setTargetPosition(denc);
        RIGHTBACK.setTargetPosition(denc);
        LEFTBACK.setTargetPosition(denc);

        RIGHTFRONT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LEFTFRONT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RIGHTBACK.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LEFTBACK.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (RIGHTBACK.isBusy()) {
            sleep(100);
        }
        stopEverything();
    }


    private void resetEncoders() {
        LEFTFRONT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RIGHTFRONT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LEFTBACK.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RIGHTBACK.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
