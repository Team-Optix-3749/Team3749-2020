package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDriveFast extends CommandBase {
    private final Drivetrain m_drive;


    public ArcadeDriveFast(Drivetrain drive) {
        m_drive = drive;
    }

    @Override
    public void initialize() {
      m_drive.setMaxOutput(Robot.getConstants().kFastDriveSpeed);
    }
  
    @Override
    public void end(boolean interrupted) {
      m_drive.setMaxOutput(1);
    }
}