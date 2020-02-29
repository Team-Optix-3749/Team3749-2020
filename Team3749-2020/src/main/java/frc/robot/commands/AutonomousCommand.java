package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommand extends CommandBase {
    private final Shooter m_shooter;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public AutonomousCommand(Shooter subsystem) {
        m_shooter = subsystem;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        m_shooter.setVelocity(2000);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        // when should this command be terminated
        return false;
    }

}