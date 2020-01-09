package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class Shoot extends CommandBase {
  private final Shooter shooter;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shoot(Shooter shoot) {
    shooter = shoot;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }
}