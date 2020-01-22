package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Low_Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Stop extends CommandBase {
    private final Low_Shooter m_shooter;

    public Stop(Low_Shooter subsystem) {
        m_shooter = subsystem;
        m_shooter.setSpeed(0);
    }
    
    @Override
    public void execute() {
        Robot.getShooter().Shoot();
    }
}