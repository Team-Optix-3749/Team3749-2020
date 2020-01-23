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

  private final Drivetrain m_driveSubsystem = new Drivetrain();
  private final Shooter m_shooterSubsystem = new Shooter();
  private final Elevator m_elevatorLeftSubsystem = new Elevator();
  private final Elevator m_elevatorRightSubsystem = new Elevator();
  private final ColorSensor m_colorSensorSubsystem = new ColorSensor();
  private final ControlPanelSubsystem m_controlPanelSubsystem = new ControlPanelSubsystem();

  XboxController m_xboxController = new XboxController(0);
  Joystick m_joystick = new Joystick(1);

  public RobotContainer() {
    configureButtonBindings();

    m_driveSubsystem.setDefaultCommand(
        new ArcadeDrive(
            m_driveSubsystem,
            () -> m_xboxController.getY(Hand.kLeft),
            () -> m_xboxController.getX(Hand.kLeft)));
  }

  private void configureButtonBindings() {
    // Increase drive speed when right bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperRight.value)
      .whenHeld(new ArcadeDriveFast(m_driveSubsystem), true);

    // Decrease drive speed when left bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperLeft.value)
      .whenHeld(new ArcadeDriveSlow(m_driveSubsystem), true);

    // Shoot when right trigger is pressed
    new JoystickButton(m_xboxController, Robot.getConstants().RT)
      .whenHeld(new ShootStart(m_shooterSubsystem), false);
    new JoystickButton(m_xboxController, Robot.getConstants().RT)
      .whenReleased(new ShootStart(m_shooterSubsystem), false);

    // Start color sensor while a button is pressed
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenHeld(new ColorSensorCommand(m_colorSensorSubsystem), false);

    // Start control panel motor while a button is pressed
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenHeld(new ControlPanelStart(m_controlPanelSubsystem), false);
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenReleased(new ControlPanelStop(m_controlPanelSubsystem), false);
    

    // old code (go back to it if something breaks):
    // XboxController controller = new XboxController(0);
    // JoystickButton aButton = new JoystickButton(controller, 1);
    // aButton.whileHeld(new ColorSensorCommand(m_colorSensorSubsystem), false);
    // aButton.whenHeld(new ControlPanelStart(m_controlPanelSubsystem), false);

    // aButton.whenReleased(new ControlPanelStop(m_controlPanelSubsystem), false);
  }

  /**
   * A simple getter method for the drivetrain system
   * @return m_drive
   */
  public Drivetrain getDrivetrain() {
    return m_driveSubsystem;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_shooter
   */
  public Shooter getShooter() {
    return m_shooterSubsystem;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_elevatorLeft
   */
  public Elevator getElevatorLeft() {
    return m_elevatorLeftSubsystem;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_elevatorRight
   */
  public Elevator getElevatorRight() {
    return m_elevatorRightSubsystem;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_colorSensorSubsystem
   */
  public ColorSensor getColorSensor() {
    return m_colorSensorSubsystem;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_controlPanelSubsystem
   */
  public ControlPanelSubsystem gControlPanelSubsystem() {
    return m_controlPanelSubsystem;
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
