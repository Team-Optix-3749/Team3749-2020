package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Low_Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {
    private final Low_Shooter m_shooter;

    public Shoot(Low_Shooter subsystem) {
        m_shooter = subsystem;
        addRequirements(m_shooter);
    }
    
    @Override
    public void execute() {
        Robot.getShooter().Shoot();
    }
}