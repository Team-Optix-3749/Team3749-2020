package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {
    private final Shooter shooter;

    public Shoot(Shooter subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        Robot.getShooter().Shoot();
    }
}