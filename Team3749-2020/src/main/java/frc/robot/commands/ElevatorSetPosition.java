package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Elevator;
import frc.robot.Constants;

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
      //
      Robot.getElevator().moveToBottom();
  }

  @Override
  public void execute() {
      //add whatever code u wanna execute here
       //setting it so that if B is pressed the elevator goes up
    if (Constants.xboxController.getRawButton(Constants.B)) {
      Robot.getElevator().moveToTop();
    }
    //setting it so that if X is pressed the elevator goes down ]
    if (Constants.xboxController.getRawButton(Constants.X)) {
      Robot.getElevator().moveToBottom();
    }
  }

  @Override
  public boolean isFinished() {
      //when should this command be terminated
      return false; 
  }
  
 
}