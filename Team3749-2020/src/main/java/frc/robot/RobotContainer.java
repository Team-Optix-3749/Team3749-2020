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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

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
  private final Intake m_intake = new Intake();
  // private final Vision m_vision = new Vision();
  
  private final ColorSensor m_ColorSensorSubsystem = new ColorSensor();
  // private final ColorSensorCommand m_ColorSensorCommand = new ColorSensorCommand(m_ColorSensorSubsystem);
//   private final ColorSensorRed m_ColorSensorRed = new ColorSensorRed(m_ColorSensorSubsystem);
//   private final ColorSensorGreen m_ColorSensorGreen = new ColorSensorGreen(m_ColorSensorSubsystem);
//   private final ColorSensorBlue m_ColorSensorBlue = new ColorSensorBlue(m_ColorSensorSubsystem);
//   private final ColorSensorYellow m_ColorSensorYellow = new ColorSensorYellow(m_ColorSensorSubsystem);
// private final SequentialCommandGroup m_autoCommand = new AutonomousCommand();

  private final ControlPanel m_ControlPanelSubsystem = new ControlPanel();

  public XboxController m_xboxController = new XboxController(0);
  public Joystick m_joystick = new Joystick(1);

  public RobotContainer() {
    configureButtonBindings();

    m_drive.setDefaultCommand(
       new ArcadeDrive(
         m_drive,
         () -> m_xboxController.getY(Hand.kLeft),
         () -> m_xboxController.getX(Hand.kRight)));
  }

  private void configureButtonBindings() {
    // Start shooter when X button is pressed
    new JoystickButton(m_xboxController, Button.kX.value)
      .whenHeld(new PidShootStart(m_shooter, 100000), false);
    new JoystickButton(m_xboxController, Button.kX.value)
      .whenReleased(new PidShootStop(m_shooter), false);
    
    // Start intake when right bumper is pressed
    new JoystickButton(m_xboxController, Button.kBumperRight.value)
      .whenHeld(new IntakeStart(m_intake, 0.6), true);
    new JoystickButton(m_xboxController, Button.kBumperRight.value)
      .whenHeld(new IntakeStop(m_intake), true);

    // Start color sensor while B button is held
    new JoystickButton(m_xboxController, Button.kB.value)
      .whenHeld(new ColorSensorCommand(m_ColorSensorSubsystem), false);

    // Start control panel motor while B button is held
    new JoystickButton(m_xboxController, Button.kB.value)
      .whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), false); 
    new JoystickButton(m_xboxController, Button.kB.value)
    .whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), false);

    //move elevator motors to the top  when Y is pressed
    new JoystickButton(m_xboxController, Button.kY.value)
    .whenPressed(new ElevatorTopPosition(m_elevator), true);

    //move elevator motors back down when A is pressed
    new JoystickButton(m_xboxController, Button.kA.value)
    .whenPressed( new ElevatorBottom(m_elevator), true);
    
  //  //Vision to read data, adjust to target, and shoot when X is pressed
  //  new JoystickButton(m_xboxController, Button.kX.value)
  //  .whenPressed(new AdjustToTarget(m_drive, m_vision), true);

  //   // Low Shooter when start button is pressed 
  //   // new JoystickButton(m_xboxController, Button.kStart.value)
  //   // .whenHeld(new LowShootStart(m_lowshooter), true);
  //   // new JoystickButton(m_xboxController, Button.kStart.value)
  //   // .whenReleased(new LowShootStop(m_lowshooter), true);

  //   // control panel code
  //   rJoy.whileHeld(m_ColorSensorCommand, true);
  //   if(m_ColorSensorCommand.isFinished() == false){
  //     rJoy.whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), true);
  //     rJoy.whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), true);
  //   }

  //   a.whileHeld(m_ColorSensorGreen, true);
  //   if(m_ColorSensorGreen.isFinished() == false){
  //     a.whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), true);
  //     a.whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), true);
  //   }

  //   b.whileHeld(m_ColorSensorRed, true);
  //   if(m_ColorSensorRed.isFinished() == false){
  //     b.whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), true);
  //     b.whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), true);
  //   }

  //   x.whileHeld(m_ColorSensorBlue, true);
  //   if(m_ColorSensorBlue.isFinished() == false){
  //     x.whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), true);
  //     x.whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), true);
  //   }

  //   y.whileHeld(m_ColorSensorYellow, true);
  //   if(m_ColorSensorYellow.isFinished() == false){
  //     y.whenHeld(new ControlPanelStart(m_ControlPanelSubsystem), true);
  //     y.whenReleased(new ControlPanelStop(m_ControlPanelSubsystem), true);
  //   }
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
   * @return m_vision
   */
  // public Vision getVision() {
  //   return m_vision;
  // }

  /**
   * A simple getter method for the shooter system
   * @return m_elevatorRight
   */
 
  /**
   * A simple getter method for the shooter system
   * @return m_colorSensorSubsystem
   */
  public ColorSensor getColorSensor() {
    return m_ColorSensorSubsystem;
  }

  /**
   * A simple getter method for the shooter system
   * @return m_controlPanel
   */
  public ControlPanel ControlPanelSubsystem() {
    return m_ControlPanelSubsystem;
  }

  // public LowShooter getLowShooter() {
  //   return m_lowshooter;
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonom  ous
   */
   public Command getAutonomousCommand() {
  // // An ExampleCommand will run in autonomous
    return null; 
  }
}
