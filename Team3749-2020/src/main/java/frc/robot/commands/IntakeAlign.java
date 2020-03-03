/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class IntakeAlign extends CommandBase {
    private final Drivetrain m_drive;
    private final Vision m_vision;

    /**
     * 
     * @param subsystem subsystem1
     * @param subsystem subsystem2
     * @param speed set speed
     */
    public IntakeAlign(Drivetrain subsystem1, Vision subsystem2) {
        m_drive = subsystem1;
        m_vision = subsystem2;
        addRequirements(m_drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //start = System.currentTimeMillis();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(m_vision.getPixyXValue() > 158)
            m_drive.arcadeDrive(0, -0.3);
        if(m_vision.getPixyXValue() < 158)
            m_drive.arcadeDrive(0, 0.3);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_vision.getPixyXValue() == 158)
            return true;
        return false;
    }
}
