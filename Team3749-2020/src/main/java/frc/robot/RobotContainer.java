/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Shooter;

import java.util.HashMap;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  private static HashMap <String, Integer> mapPWM; //the PWM port map
	private static HashMap <String, Integer> mapCAN; //the CAN port map
	private static HashMap <String, Integer> mapDIO; //the DIO port map
	private static HashMap <String, Integer> mapCTRL; //the controller port map
  private static HashMap <String, Integer> mapSys; // the subsystems mode map

  private XboxController xboxController = new XboxController(0);

  public static final int xboxControllerLeftStickX = 0;
  public static final int xboxControllerLeftStickY = 1;
  public static final int xboxControllerRightStickX = 4;
  public static final int xboxControllerRightStickY = 5;
  public static final int xboxControllerAButton = 1;
  public static final int xboxControllerBButton = 2;
  public static final int xboxControllerXButton = 3;
  public static final int xboxControllerYButton = 4;
  public static final int xboxControllerLeftBumper = 5;
  public static final int xboxControllerRightBumper = 6;
  
  public RobotContainer() {
    // Configure the button bindings
    mapPWM = new HashMap<>();
	  mapCAN = new HashMap<>();
    mapDIO = new HashMap<>();
    mapCTRL = new HashMap<>();
    mapSys = new HashMap<>();

    // loading map values for drive
    // first character = left or right
    // second character = front, middle, or back
    setCAN("drive_lf", 10);
    setCAN("drive_lm", 21);
    setCAN("drive_lb", 23);
    setCAN("drive_rf", 11);
    setCAN("drive_rm", 20);
    setCAN("drive_rb", 22);

    // intake/grabber wheel map
    setCAN("wheel_left", 3);
    setCAN("wheel_right", 1);

    // main subsystem srx motor ports
    setCAN("tilt", 1);
    setCAN("elevator", 2);
    
    // victor spx
    setCAN("climb", 2);

    // limit switches
    setDIO("intake_switch", 0);
    setDIO("climb_switch", 1);

    // whether a subsystem is installed and in use
    // 0 = disabled, 1 = enabled, 2 = enabled and debugging (print sensor vals, etc)
    setSys("tilt", 2);
    setSys("drive", 1);
    setSys("wheelio", 1);
    setSys("elevator", 1);
    setSys("climb", 0);
    configureButtonBindings();
  }

  


  /**
	* Method to set a PWM port
	* @param String		name of what port is for (what you call it throughout the program)
	* @param int 		the port number
	*/
	public void setPWM (String name, int port)
	{
		mapPWM.put(name, port);
	}
	
	/**
	* Method to set a CAN port
	* @param String		name of what port is for (what you call it throughout the program)
	* @param int 		the port number
	*/
	public void setCAN (String name, int port)
	{
		mapCAN.put(name, port);
	}
	/**
	* Method to set a DIO port
	* @param String		name of what port is for (what you call it throughout the program)
	* @param int 		the port number
	*/
	public void setDIO (String name, int port)
	{
		mapDIO.put(name, port);
	}
	
	/**
	* Method to set a CTRL port
	* @param String		name of what port is for (what you call it throughout the program)
	* @param int 		the port number
	*/
	public void setCTRL (String name, int port)
	{
		mapCTRL.put(name, port);
	}
	/**
	* Method to set a subsystem value
	* @param String		name of what port is for (what you call it throughout the program)
	* @param int 		the value (0 = disable, 1 = enabled, 2 = debugging)
	*/
	public void setSys (String name, int val)
	{
		mapSys.put(name, val);
	}
	
	/**
	* Method to get a PWM port
	* @param String		name of what port is for (what you call it throughout the program)
	*/
	public int getPWM (String name)
	{
		return mapPWM.get(name);
	}
	
	/**
	* Method to get a CAN port
	* @param String		name of what port is for (what you call it throughout the program)
	*/
	public int getCAN (String name)
	{
		return mapCAN.get(name);
	}
	
	/**
	* Method to get a DIO port
	* @param String		name of what port is for (what you call it throughout the program)
	*/
	public int getDIO (String name)
	{
		return mapDIO.get(name);
	}
	/**
	* Method to get a controller port
	* @param String		name of what port is for (what you call it throughout the program)
	*/
	public int getCTRL (String name)
	{
		return mapCTRL.get(name);
	}
	/**
	* Method to get a toggleable setting (for subsystems mostly)
	* @param String		name of setting it is for (what you call it throughout the program)
	*/
	public int getSys (String name)
	{
		return mapSys.get(name);
  }
  
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private XboxController xboxCtrl;
  private Joystick stick;
  private JoystickButton jsButton1; //trigger on the joystick
  private JoystickButton jsButton2;
  private JoystickButton jsButton3;
  private JoystickButton jsButton4;
  private JoystickButton jsButton5;
  private JoystickButton jsButton6;
  private JoystickButton jsButton7;
  private JoystickButton jsButton8;
  private JoystickButton jsButton9;
  private JoystickButton jsButton10;
  private JoystickButton jsButton11;
  private JoystickButton xbxButton1; //dpad buttons
  private JoystickButton xbxButton2;
  private JoystickButton xbxButton3;
  private JoystickButton xbxButton4;

  private void configureButtonBindings() {
	stick = new Joystick(1);
	jsButton1 = new JoystickButton(stick, 1);
	jsButton2 = new JoystickButton(stick, 2);
	jsButton3 = new JoystickButton(stick, 3);
	jsButton4 = new JoystickButton(stick, 4);
	jsButton5 = new JoystickButton(stick, 5);
	jsButton6 = new JoystickButton(stick, 6);
	jsButton7 = new JoystickButton(stick, 7);
	jsButton8 = new JoystickButton(stick, 8);
	jsButton9 = new JoystickButton(stick, 9);
	jsButton10 = new JoystickButton(stick, 10);
	jsButton11 = new JoystickButton(stick, 11);

	xboxController.getAButtonPressed(Robot.get;
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
} 
