/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

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


    boolean BackButtonPressed = Robot.m_oi.BackButtonPressed();
    int THEmotorState = 0;     //  //    //    // / / / / / // / / / / // // / / / / // / may have to change where this is but ig it works here for now.
    if(BackButtonPressed == true){
      if (THEmotorState == 1){
        THEmotorState = 0;
      }
      if(THEmotorState == 0){
        THEmotorState = 1;
      }
    }
    else{
      THEmotorState = 0;
    }

    Robot.m_subsystem.setMotor(THEmotorState);
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
