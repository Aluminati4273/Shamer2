/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static android.os.SystemClock.sleep;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See SHAMER_TeleOp and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "leftDrive1"
 * Motor channel:  Left  drive motor:        "leftDrive2"
 * Motor channel:  Right drive motor:        "rightDrive1"
 * Motor channel:  Right drive motor:        "rightDrive2"
 * Servo channel:  Servo to open marker dropper:  "marker"
 */
public class HardwareShamer
{

    private ElapsedTime runtime = new ElapsedTime();



    /* Public OpMode members. */
    public DcMotor  leftDrive1   = null;
    public DcMotor  leftDrive2  = null;
    public DcMotor  rightDrive1  = null;
    public DcMotor  rightDrive2 = null;
    public DcMotor collectionMotor = null;

    public Servo marker1 = null;
    public Servo marker2 = null;

    public double marker1Start = 0;
    public double marker1Open = 1;

    public double marker2start = 0;
    public double marker2open = 1;







    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareShamer(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Drive Motors
        leftDrive1  = hwMap.get(DcMotor.class, "left_drive1");
        leftDrive2 =  hwMap.get(DcMotor.class, "left_drive2");
        rightDrive1 = hwMap.get(DcMotor.class, "right_drive1");
        rightDrive2 = hwMap.get(DcMotor.class, "right_drive2");

        // define and initialize collector motor
        collectionMotor = hwMap.get(DcMotor.class, "collection_Motor");

        //initialize servos
        marker1 = hwMap.get(Servo.class, "marker_extend");
        marker2 = hwMap.get(Servo.class, "marker_drop");

        //set direction of drive motors
        leftDrive1.setDirection(DcMotor.Direction.FORWARD);
        leftDrive2.setDirection(DcMotor.Direction.FORWARD);
        rightDrive1.setDirection(DcMotor.Direction.REVERSE);
        rightDrive2.setDirection(DcMotor.Direction.REVERSE);



        // Set all drive motors to zero power
        leftDrive1.setPower(0);
        leftDrive2.setPower(0);
        rightDrive1.setPower(0);
        rightDrive2.setPower(0);

        // set collection motor to zero
        collectionMotor.setPower(0);

        // set team marker servo position
        marker1.setPosition(marker1Start);
        marker2.setPosition(marker2start);

    }

    //control the left side of the robot
    public void leftDrive(double power){
        leftDrive1.setPower(power);
        leftDrive2.setPower(power);
    }

    //control the left side of the robot
    public void rightDrive(double power){
        rightDrive1.setPower(power);
        rightDrive2.setPower(power);

    }

    //control both sides of the robot simultaneously, used to drive forward or backward
    public void drive(double power){
        rightDrive(power);
        leftDrive(power);
    }


    public void dropMarker(){
        marker1.setPosition(marker1Open);

        sleep(250);

        marker2.setPosition(marker2open);

    }


}

