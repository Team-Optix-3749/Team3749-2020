package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command that uses an example subsystem.
 */
public class ElevatorTopPosition extends CommandBase {
  
    private final Elevator m_elevator;

    public ElevatorTopPosition(Elevator subsystem) {
      m_elevator = subsystem;
    addRequirements(m_elevator);


  }

  private void moveToTop() {
    //using time right now to implement this, will later move to using limit switches 
    m_elevator.startMotor(1);
    
    //Timer.delay(1);
    //m_subsystem.stopMotor();
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