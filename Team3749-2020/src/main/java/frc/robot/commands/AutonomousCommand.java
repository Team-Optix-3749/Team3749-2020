package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommand extends CommandBase {
    private final Shooter m_shooter;
    private final Drivetrain m_drivetrain;

    private final Timer m_timer = new Timer();

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
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        // drive
        if (m_timer.get() < 1.0) {
            m_drivetrain.arcadeDrive(-0.5, 0.0); // drive forwards half speed
        } else {
            m_drivetrain.stopMotors();; // stop robot
        }

        // shoot
        if (m_timer.get() < 10.0) {
            m_shooter.setVelocity(2000);
        } else {
            m_shooter.stop(); // stop robot
        }
    }

    @Override
    public boolean isFinished() {
        // when should this command be terminated
        return false;
    }

}