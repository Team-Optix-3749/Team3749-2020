package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.LowShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowShoot extends CommandBase {
    private final LowShooter m_lowshooter;

    public LowShoot(LowShooter subsystem) {
        m_lowshooter = subsystem;
        addRequirements(m_lowshooter);
    }
    
    @Override
    public void execute() {
        Robot.getRobotContainer().lowShooter().LowShoot();
    }

    
}