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

public class TargetAdjustment extends CommandBase {
    private final Drivetrain m_drive;
    private final Vision m_vision;
    
    private double error;

    /**
     * @param subsystem1
     * @param subsystem2
     */
    public TargetAdjustment(Drivetrain subsystem1, Vision subsystem2) {
           m_drive = subsystem1;
           m_vision = subsystem2;
           error = 0;

           addRequirements(m_drive);
    }
    
     // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
       double error = m_vision.getX()-1;
       m_drive.arcadeDrive(0, 0.3 * error);
       m_drive.arcadeDrive(0.3, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(error < -1)
            return true;
        return false;
    }
}