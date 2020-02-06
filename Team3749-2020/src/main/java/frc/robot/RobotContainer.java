/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
  private final Elevator m_elevator = new Elevator();
  private final ColorSensor m_colorSensor = new ColorSensor();
  private final ControlPanel m_controlPanel = new ControlPanel();
  private final LowShooter m_lowshooter = new LowShooter();

  public XboxController m_xboxController = new XboxController(0);
  public Joystick m_joystick = new Joystick(1);

  // private final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(3);
  // private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(3);


  public RobotContainer() {
    configureButtonBindings();

    // // Get the x speed. We are inverting this because Xbox controllers return
    // // negative values when we push forward.
    // final var xSpeed =
    //   -m_speedLimiter.calculate(m_xboxController.getY(Hand.kLeft))
    //     * Robot.getConstants().kMaxSpeed;

    // // Get the rate of angular rotation. We are inverting this because we want a
    // // positive value when we pull to the left (remember, CCW is positive in
    // // mathematics). Xbox controllers return positive values when you pull to
    // // the right by default.
    // final var rot =
    //   -m_rotLimiter.calculate(m_xboxController.getX(Hand.kRight))
    //     * Robot.getConstants().kMaxAngularSpeed;

    // m_drive.setDefaultCommand(new Drive(m_drive, xSpeed, rot));

    m_drive.setDefaultCommand(
       new ArcadeDrive(
         m_drive,
         () -> m_xboxController.getY(Hand.kLeft),
         () -> m_xboxController.getX(Hand.kRight)));
  }

  private void configureButtonBindings() {
    // Increase drive speed when right bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperRight.value)
      .whenHeld(new ArcadeDriveFast(m_drive), true);

    // Decrease drive speed when left bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperLeft.value)
      .whenHeld(new ArcadeDriveSlow(m_drive), true);

    // Shoot when x button is pressed
    new JoystickButton(m_xboxController, Button.kX.value)
      .whenHeld(new PidShootStart(m_shooter, 100000), false);
    new JoystickButton(m_xboxController, Button.kX.value)
      .whenReleased(new PidShootStop(m_shooter), false);

    // Shoot when b button is pressed
    new JoystickButton(m_xboxController, Button.kB.value)
      .whenHeld(new ShootStart(m_shooter), false);
    new JoystickButton(m_xboxController, Button.kB.value)
      .whenReleased(new ShootStop(m_shooter), false);

    // Start color sensor while a button is pressed
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenHeld(new ColorSensorCommand(m_colorSensor), false);

    // Start control panel motor while a button is pressed
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenHeld(new ControlPanelStart(m_controlPanel), false);
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenReleased(new ControlPanelStop(m_controlPanel), false);

    //move elevator motors to the top or the bottom when y is pressed
    new JoystickButton(m_xboxController, Button.kY.value)
    .whenHeld(new ElevatorTopPosition(m_elevator), true);
    
    new JoystickButton(m_xboxController, Button.kStart.value)
    .whenHeld(new LowShootStart(m_lowshooter), true);
    new JoystickButton(m_xboxController, Button.kStart.value)
    .whenReleased(new LowShootStop(m_lowshooter), true);
    
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

  /**
   * A simple getter method for the shooter system
   * @return m_elevatorLeft
   */
  public Elevator getElevator() {
    return m_elevator;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_elevatorRight
   */

  /**
   * A simple getter method for the shooter system
   * @return m_colorSensorSubsystem
   */
  public ColorSensor getColorSensor() {
    return m_colorSensor;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_controlPanel
   */
  public ControlPanel ControlPanelSubsystem() {
    return m_controlPanel;
  }

  public LowShooter getLowShooter() {
    return m_lowshooter;   
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
