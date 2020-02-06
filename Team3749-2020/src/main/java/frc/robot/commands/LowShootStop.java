package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.LowShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowShootStop extends CommandBase {
    private final LowShooter m_lowshooter;

    public LowShootStop(LowShooter subsystem) {
        m_lowshooter = subsystem;
    }
    
    @Override
    public void execute() {
        Robot.getRobotContainer().getLowShooter().Stop();
    }

    @Override
    public boolean isFinished() {
      return true;
    }
}