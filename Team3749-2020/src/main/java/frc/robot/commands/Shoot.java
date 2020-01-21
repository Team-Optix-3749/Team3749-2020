package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {
    private final Shooter m_shooter;

    public Shoot(Shooter subsystem) {
        m_shooter = subsystem;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        Robot.getRobotContainer().getShooter().Shoot();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    @Override
    public void end(boolean interrupted) {
        Robot.getRobotContainer().getShooter().Stop();
    }
}