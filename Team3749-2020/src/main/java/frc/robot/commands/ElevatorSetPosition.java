package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command that uses an example subsystem.
 */
public class ElevatorSetPosition extends CommandBase {
    public final Elevator m_subsystem;

  public ElevatorSetPosition(Elevator subsystem) {
    m_subsystem = subsystem;
  
  }
  
  @Override
  public void initialize() {
      //initialization code here
  }

  @Override
  public void execute() {
      //add whatever code u wanna execute here
  }

  @Override
  public boolean isFinished() {
      //when should this command be terminated
      return false; 
  }
  
 
}