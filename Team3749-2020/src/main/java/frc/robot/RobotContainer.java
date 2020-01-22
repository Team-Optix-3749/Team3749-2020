/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.ColorSensorCommand;
import frc.robot.subsystems.ColorSensor;

import frc.robot.commands.ControlPanelStart;
import frc.robot.commands.ControlPanelStop;
import frc.robot.subsystems.ControlPanelSubsystem;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Drivetrain m_drive = new Drivetrain();
  private final Shooter m_shooter = new Shooter();
  private final Elevator m_elevator_left = new Elevator();
  private final Elevator m_elevator_right = new Elevator();

  XboxController m_xboxController = new XboxController(0);
  Joystick m_joystick = new Joystick(1);
  
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final ColorSensor m_ColorSensorSubsystem = new ColorSensor();
  private final ColorSensorCommand m_ColorSensorCommand = new ColorSensorCommand(m_ColorSensorSubsystem);

  private final ControlPanelSubsystem m_ControlPanelSubsystem = new ControlPanelSubsystem();
  private final ControlPanelStart m_ControlPanelStart = new ControlPanelStart(m_ControlPanelSubsystem);
  private final ControlPanelStop m_ControlPanelStop = new ControlPanelStop(m_ControlPanelSubsystem);

  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();

    m_drive.setDefaultCommand(
        new ArcadeDrive(
            m_drive,
            () -> m_xboxController.getY(Hand.kLeft),
            () -> m_xboxController.getX(Hand.kLeft)));
  }

  private void configureButtonBindings() {
    // Increase drive speed when right bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperRight.value)
      .whenPressed(new ArcadeDriveFast(m_drive));
    // Decrease drive speed when left bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperLeft.value)
      .whenPressed(new ArcadeDriveSlow(m_drive));
    // Shoot when the A button is pressed
    new JoystickButton(m_xboxController, Robot.getConstants().RT)
      .whileHeld(new Shoot(m_shooter));
    
    XboxController controller = new XboxController(0);
    JoystickButton aButton = new JoystickButton(controller, 1);
    aButton.whileHeld(new ColorSensorCommand(m_ColorSensorSubsystem), false);
    aButton.whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), false);

    aButton.whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), false);
  }

  /**
   * A simple getter method for the drivetrain system
   * @return m_drive
   */
  public Drivetrain getDrivetrain() {
    return m_drive;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_shooter
   */
  public Shooter getShooter() {
    return m_shooter;
  }

  public Elevator getElevatorLeft() {
    return m_elevator_left;
  }

  public Elevator getElevatorRight() {
    return m_elevator_right;
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  // // An ExampleCommand will run in autonomous
  // return m_autoCommand;
  // }
}
