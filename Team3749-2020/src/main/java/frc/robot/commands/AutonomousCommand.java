package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommand extends CommandBase {
    private final Shooter m_shooter;
    private final Drivetrain m_drivetrain;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public AutonomousCommand(Drivetrain drivetrain, Shooter shooter) {
        m_drivetrain = drivetrain;
        m_shooter = shooter;
        addRequirements(m_drivetrain, m_shooter);
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