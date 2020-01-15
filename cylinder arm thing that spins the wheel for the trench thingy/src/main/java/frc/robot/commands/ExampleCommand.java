/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class ExampleCommand extends Command {
  public ExampleCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double LeftTriggerY = Robot.m_oi.GetDriverRawAxis(RobotMap.LeftTriggerY);
    double RightTriggerY = Robot.m_oi.GetDriverRawAxis(RobotMap.RightTriggerY);

    boolean LeftBumperPressed = Robot.m_oi.LeftBumperPressed(GenericHID.Hand.kRight);
    int LeftBumperMotor = 0;
    if(LeftBumperPressed == true){
      LeftBumperMotor = 1;
    }
    else{
      LeftBumperMotor = 0;
    }

    boolean RightBumperPressed = Robot.m_oi.RightBumperPressed(GenericHID.Hand.kRight);
    int RightBumperMotor = 0;
    if(RightBumperPressed == true){
      RightBumperMotor = 1;
    }
    else{
      RightBumperMotor = 0;
    }

    Robot.m_subsystem.setMotor(RightTriggerY);
    Robot.m_subsystem.setMotor(-LeftTriggerY);

    Robot.m_subsystem.setMotor(-LeftBumperMotor);
    Robot.m_subsystem.setMotor(RightBumperMotor);

  }


// Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
