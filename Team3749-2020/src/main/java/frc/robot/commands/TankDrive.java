package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase {
    private final Drivetrain m_drive;
    private final DoubleSupplier m_rightSide;
    private final DoubleSupplier m_leftSide;

    /**
     * 
     * @param drive
     * @param forward
     * @param rotation
     */
    public TankDrive(Drivetrain drive, DoubleSupplier rightSide, DoubleSupplier LeftSide) {
        m_drive = drive;
        m_rightSide = rightSide;
        m_leftSide = LeftSide;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        m_drive.tankDrive(m_rightSide.getAsDouble(), m_leftSide.getAsDouble());
    }
}