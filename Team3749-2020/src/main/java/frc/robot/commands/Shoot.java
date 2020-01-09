package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Shoot extends CommandBase {
    private final Shooter shooter;

    public Shoot(Shooter shoot) {
        shooter = shoot;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooter);
    }

    public void execute() {
        Robot.getShooter().shoot(0.7);
    }
}