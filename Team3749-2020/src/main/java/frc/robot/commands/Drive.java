package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Drive extends CommandBase {
    private final Drivetrain m_drive;
    private double xSpeed;
    private double rot;

    public Drive(Drivetrain drive, double xSpeed, double rot) {
        m_drive = drive;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        m_drive.drive(xSpeed, rot);
    }
}