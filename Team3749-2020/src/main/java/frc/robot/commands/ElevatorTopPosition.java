package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command that uses an example subsystem.
 */
public class ElevatorTopPosition extends CommandBase {
  
    private final Elevator m_subsystem;

    public ElevatorTopPosition(Elevator subsystem) {
    m_subsystem = subsystem;


  }

  private void moveToTop() {
    //using time right now to implement this, will later move to using limit switches 
    m_subsystem.startMotor(0.4);
    Timer.delay(1);
    m_subsystem.stopMotor();
  }

  @Override
  public void initialize() {
     
  }

  @Override
  public void execute() {
    moveToTop();

  }

  @Override
  public boolean isFinished() {
      //when should this command be terminated
      return false; 
  }
  
 
}