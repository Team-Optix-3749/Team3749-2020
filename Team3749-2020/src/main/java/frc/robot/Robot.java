/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.Constants;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private static Constants m_constants;
  private static Gains m_gains;
  private static RobotContainer m_robotContainer;

  private static Drivetrain m_drive;
  private static Vision m_vision;
  private static Elevator m_elevator;
  private static Shooter m_shooter;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_constants = new Constants();
    m_robotContainer = new RobotContainer(m_vision);
    m_drive = new Drivetrain();
    m_shooter = new Shooter();
    m_elevator = new Elevator();
    m_vision = new Vision();

    Robot.getDrivetrain().resetGyro();

    initCamera();
  }

  /**
   * A simple getter method for RobotContainer.java
   * 
   * @return m_robotContainer
   */
  public static RobotContainer getRobotContainer() {
    return m_robotContainer;
  }

  /**
   * A simple getter method for Constants.java
   * 
   * @return m_constants
   */
  public static Constants getConstants() {
    return m_constants;
  }

  /**
   * A simple getter method for Gains.java
   * 
   * @return m_gains
   */
  public static Gains getGains() {
    return m_gains;
  }

  /**
   * A simple getter method for Drivetrain.java
   * 
   * @return m_drive
   */
  public static Drivetrain getDrivetrain() {
    return m_drive;
  }

    /**
   * A simple getter method for Shooter.java
   * 
   * @return m_shooter
   */
  public static Shooter getShooter() {
    return m_shooter;
  }

  /**
   * A simple getter method for Elevator.java
   * 
   * @return m_elevator
   */
  public static Elevator getElevator() {
    return m_elevator;
  }

  /**
   * A simple getter method for Vision.java
   * 
   * @return m_vision
   */
  public static Vision getVision() {
    return m_vision;
  }

  public void initCamera() {
    // start running camera from roboRIO
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setBrightness(8);
    // camera.setExposureManual(10);
    camera.setExposureAuto();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  } 

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();

    }

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // CommandScheduler.getInstance().setDefaultCommand(m_vision, new
    // TargetGetData(m_vision));
    // m_vision.setDefaultCommand(new TargetGetData(m_vision));
    m_vision.readData();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // CommandScheduler.getInstance().setDefaultCommand(m_vision, new
    // TargetGetData(m_vision));
    // m_vision.setDefaultCommand(new TargetGetData(m_vision));
    m_vision.readData();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    // CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
