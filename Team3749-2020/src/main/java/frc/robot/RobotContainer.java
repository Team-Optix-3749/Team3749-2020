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
import edu.wpi.first.wpilibj2.command.Command;
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
  private final Drivetrain m_drive = new Drivetrain();
  private final Shooter m_shooter = new Shooter();
  private final Elevator m_elevator = new Elevator();
  private final Intake m_intake = new Intake();
  private final ControlPanel m_controlPanel = new ControlPanel();
  private final Vision m_vision = new Vision();

  public XboxController m_xboxController = new XboxController(0);
  public XboxController m_xboxController2 = new XboxController(1);
  public Joystick m_joystick = new Joystick(3);

  private final AutonomousCommand m_autonomousCommand = new AutonomousCommand(m_drive, m_shooter);

  public RobotContainer() {
    configureButtonBindings();

    m_drive.setDefaultCommand(
       new ArcadeDrive(
         m_drive,
         () -> m_xboxController.getY(Hand.kLeft),
         () -> m_xboxController.getX(Hand.kRight)));
  }

  private void configureButtonBindings() {
    // toggle to tank drive
    new JoystickButton(m_xboxController, Button.kStart.value)
      .whenPressed(
        new TankDrive(
          m_drive,
          () -> m_xboxController.getY(Hand.kLeft),
          () -> m_xboxController.getY(Hand.kRight)), true);

    // toggle to arcade drive
    new JoystickButton(m_xboxController, Button.kBack.value)
      .whenPressed(
        new ArcadeDrive(
          m_drive,
          () -> m_xboxController.getY(Hand.kLeft),
          () -> m_xboxController.getX(Hand.kRight)), true);

    // increase drive speed
    new JoystickButton(m_xboxController, Button.kStickLeft.value)
        .whenPressed(() -> Robot.getDrivetrain().setMaxOutput(0.9))
        .whenReleased(() -> Robot.getDrivetrain().setMaxOutput(Robot.getConstants().kDriveSpeed));

    // raw shoot
    new JoystickButton(m_xboxController, Button.kA.value)
      .whenPressed(new PidShootStart(m_shooter, 4000), true)
      .whenReleased(new PidShootStop(m_shooter), true);
    
    // intake up
    new JoystickButton(m_xboxController, Button.kBumperLeft.value)
      .whenHeld(new IntakeUp(m_intake), false)
      .whenReleased(new IntakeStop(m_intake), false);

    // intake down
    new JoystickButton(m_xboxController, Button.kBumperRight.value)
      .whenHeld(new IntakeDown(m_intake), false)
      .whenReleased(new IntakeStop(m_intake), false);

    // elevator up
    new JoystickButton(m_xboxController, Button.kY.value)
      .whenPressed( new ElevatorBottom(m_elevator), false)
      .whenReleased(new ElevatorStop(m_elevator), false);

    // elevator down
    new JoystickButton(m_xboxController, Button.kB.value)
      .whenPressed(new ElevatorTopPosition(m_elevator), false)
      .whenReleased(new ElevatorStop(m_elevator), false);

    // intake up 2
    new JoystickButton(m_xboxController2, Button.kBumperLeft.value)
      .whenHeld(new IntakeUp(m_intake), true)
      .whenReleased(new IntakeStop(m_intake), true);

    // intake down 2
    new JoystickButton(m_xboxController2, Button.kBumperRight.value)
      .whenHeld(new IntakeDown(m_intake), true)
      .whenReleased(new IntakeStop(m_intake), true);

    // elevator up 2
    new JoystickButton(m_xboxController2, Button.kY.value)
      .whenPressed( new ElevatorBottom(m_elevator), true)
      .whenReleased(new ElevatorStop(m_elevator), true);

    // elevator down 2
    new JoystickButton(m_xboxController2, Button.kB.value)
      .whenPressed(new ElevatorTopPosition(m_elevator), true)
      .whenReleased(new ElevatorStop(m_elevator), true);
    
    // rotation control
    new JoystickButton(m_xboxController2, Button.kA.value)
      .whenPressed(new RotationControl(m_controlPanel), true);

    // position control
    new JoystickButton(m_xboxController2, Button.kX.value)
      .whenPressed(new PositionControl(m_controlPanel), true);

    // auto shoot
    new JoystickButton(m_xboxController, Button.kX.value)
      .whenPressed(new TargetDataStart(m_vision)
      .andThen(new TargetAdjustment(m_drive, m_vision), new TargetDataStop(m_vision),
      new PidShootAuto(m_shooter).alongWith(new IntakeAuto(m_intake, 0.6))), false);
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
   * A simple getter method for the vision system
   * @return m_vision
   */
  public Vision getVision() {
    return m_vision;
  }

    /**
   * A simple getter method for the Control Panel system
   * @return m_controlPanel
   */
  public ControlPanel getControlPanel() {
    return m_controlPanel;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
   public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}
