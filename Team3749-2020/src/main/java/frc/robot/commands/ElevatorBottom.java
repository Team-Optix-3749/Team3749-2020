package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command that uses an example subsystem.
 */
public class ElevatorBottom extends CommandBase {
    public final Elevator m_subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */

    public ElevatorBottom(Elevator subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }
  // want to see which branch this pushes to 

  @Override
  public void initialize() {
      //initialization code here
  }

  private void moveToBottom() {
    //using time right now to implement this, will later move to using limit switches 
    m_subsystem.startMotor(-0.2);
    Timer.delay(1);
    m_subsystem.stopMotor();
  }

  @Override
  public void execute() {
    moveToBottom();
  }

  @Override
  public boolean isFinished() {
      //when should this command be terminated
      return false; 
  }
  
 
}