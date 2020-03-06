/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;

public final class Constants {

    private static HashMap<String, Integer> mapPWM; // the PWM port map
    private static HashMap<String, Integer> mapCAN; // the CAN port map
    private static HashMap<String, Integer> mapDIO; // the DIO port map
    private static HashMap<String, Integer> mapCTRL; // the controller port map
    private static HashMap<String, Integer> mapSys; // the subsystems mode map

    /*
    XBOX BUTTON MAP KEY:
    1 = A
    2 = B
    3 = X
    4 = Y
    5 = left bumper
    6 = right bumper
    7 = back
    8 = start
    9 = left stick click
    10 = right stick click
    */

    // Xbox Map
    public final int kXboxControllerPort = 0;

    public final int A = 1;
    public final int B = 2;
    public final int X = 3;
    public final int Y = 4;

    public final int LB = 5;
    public final int RB = 6;

    public final int BACK = 7;
    public final int START = 8;

    public final int LT = 2;
    public final int RT = 3;

    public final int LS = 9;
    public final int RS = 10;

    public final int LY = 1;
    public final int RX = 4;

    public final int LSX = 0;
    public final int LSY = 1;
    public final int RSX = 4;
    public final int RSY = 5;

    // Joystick map
    public final int kJoystickPort = 1;

    // Motor Map
    public static final int m_leftFrontMotor = 10;
    public static final int m_leftBackMotor = 23;
    public static final int m_rightFrontMotor = 11;
    public static final int m_rightBackMotor = 22;
    public static final int m_shooterMotor = 15;

    // Other constants
    public final double kDriveSpeed = 0.7;
    public final double kDriveOffest = 0.3;
    public final double kSlowDriveSpeed = kDriveSpeed - 0.25;
    public final double kFastDriveSpeed = kDriveSpeed + 0.25;
    public final double kShooterSpeed = 1; 
    public final double kShooterHeight = 0.9906; //meters
    public final double controlPanelSpeed = 0.5; //percent output
    public final double kShooterAngle = 0.5759; //radians
    public final double kLowshooterSpeed = 0.6;
    public final double kMaxSpeed = 3.0; // meters per second
    public final double kMaxAngularSpeed = 2 * Math.PI; // one rotation per second
    public final double kTrackWidth =    0.381 * 2; // meters
    public final double kWheelRadius = 0.0508; // meters
    public final int kEncoderResolution = 4096;
    public final double kControlSpeed = 0.5;
    public final double kIntakeSpeed = 0.6;

    // Shooter constants
    public double kMaxShooterSpeed = 6000;
    public double shooterP = 0.0011;
    public double shooterI = 0;
    public double shooterD = 4;
    public double shooterF = 0.00017;

    public double kVisionP = -0.04;
    public double kVisionLimit = 0.5;

    public final double kTargetHeight = 2.49; //meters

	public final int kSlotIdx = 0;
    public final int kPIDLoopIdx = 0;
    public final int kTimeoutMs = 30;
    public final Gains kGains_Velocit = new Gains( 0.009, 0, 0, 1023.0/7200.0,  300,  1.00);

    //public final boolean kActivateData = true;

    public Constants() {

        // Configure the button bindings
        mapPWM = new HashMap<>();
        mapCAN = new HashMap<>();
        mapDIO = new HashMap<>();
        mapCTRL = new HashMap<>();
        mapSys = new HashMap<>();

        // loading map values for drive
        // first character = left or right
        // second character = front, middle, or back
        setCAN("drive_lf", 11);
        setCAN("drive_lb", 20);
        setCAN("drive_rf", 13);
        setCAN("drive_rb", 23);

        // shooter and elevator map
        setCAN("shooter_motor", 10);
        setCAN("intake_motor_f", 21);
        setCAN("intake_motor_b", 22);
        setCAN("elevator_motor", 24);
        setCAN("control_panel_motor", 15);

        // whether a subsystem is installed and in use
        // 0 = disabled, 1 = enabled, 2 = enabled and debugging (print sensor vals, etc)
        setSys("drive", 1);
        setSys("shooter", 1);
        setSys("intake", 1);
        setSys("elevator", 1);
        setSys("controlPanel", 1);
        setSys("vision", 1);
    }

    /**
     * Method to set a PWM port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public void setPWM(String name, int port) {
        mapPWM.put(name, port);
    }

    /**
     * Method to set a CAN port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public void setCAN(String name, int port) {
        mapCAN.put(name, port);
    }

    /**
     * Method to set a DIO port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public void setDIO(String name, int port) {
        mapDIO.put(name, port);
    }

    /**
     * Method to set a CTRL port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the port number
     */
    public void setCTRL(String name, int port) {
        mapCTRL.put(name, port);
    }

    /**
     * Method to set a subsystem value
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     * @param int    the value (0 = disable, 1 = enabled, 2 = debugging)
     */
    public void setSys(String name, int val) {
        mapSys.put(name, val);
    }

    /**
     * Method to get a PWM port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public int getPWM(String name) {
        return mapPWM.get(name);
    }

    /**
     * Method to get a CAN port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public int getCAN(String name) {
        return mapCAN.get(name);
    }

    /**
     * Method to get a DIO port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public int getDIO(String name) {
        return mapDIO.get(name);
    }

    /**
     * Method to get a controller port
     * 
     * @param String name of what port is for (what you call it throughout the
     *               program)
     */
    public int getCTRL(String name) {
        return mapCTRL.get(name);
    }

    /**
     * Method to get a toggleable setting (for subsystems mostly)
     * 
     * @param String name of setting it is for (what you call it throughout the
     *               program)
     */
    public int getSys(String name) {
        return mapSys.get(name);
    }
}
