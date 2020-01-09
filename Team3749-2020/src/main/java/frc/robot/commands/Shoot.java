package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Shoot extends CommandBase {
    private final Shooter shooter;
    private VictorSPX shooterMotor;

    public Shoot(Shooter shoot) {
        shooter = shoot;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooter);
    }

    public void execute() {
        shooterMotor.set(ControlMode.PercentOutput, 0.7);
    }
}