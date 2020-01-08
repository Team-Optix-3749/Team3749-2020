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

import frc.robot.Constants;

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
  
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
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
  private JoystickButton xbxButton1; //extra buttons
  private JoystickButton xbxButton2;
  private JoystickButton xbxButton3;
  private JoystickButton xbxButton4;
  private JoystickButton xbxButton5;
  private JoystickButton xbxButton6;

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

	if (Constants.xboxController.getAButtonPressed() == true){
		Robot.getShooter().shoot(0.5);
	}
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
