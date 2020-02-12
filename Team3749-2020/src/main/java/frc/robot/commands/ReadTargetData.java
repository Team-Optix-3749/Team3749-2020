/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Vision;

public class ReadTargetData extends CommandBase {
  private final Vision m_vision;
  private boolean system;

  /**
   *   
   * @param subsystem
   */
  public ReadTargetData(Vision subsystem) {
    m_vision = subsystem;
    system = true;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    while(system == true) {
        m_vision.readData();
    }
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    system = interrupted;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
