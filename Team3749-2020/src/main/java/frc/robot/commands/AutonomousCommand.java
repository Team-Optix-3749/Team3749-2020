package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommand extends CommandBase {

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */

    public AutonomousCommand() {
   
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
  
  }

  @Override
  public boolean isFinished() {
      //when should this command be terminated
      return false; 
  }
  
 
}