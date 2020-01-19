package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command that uses an example subsystem.
 */
public class ElevatorSetPosition extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Elevator[] m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public ElevatorSetPosition(Elevator[] subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }
  // want to see which branch this pushes to 

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