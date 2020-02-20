/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Intake;

public class IndexAuto extends CommandBase {
    private final Intake m_intake;
    private double speed;

    /**
     * 
     * @param subsystem subsystem
     * @param speed set speed
     */
    public IndexAuto(Intake subsystem, double set) {
        m_intake = subsystem;
        addRequirements(m_intake);

        speed = set;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Timer.delay(5.00);
        m_intake.setIndex(speed);
        Timer.delay(5.00);
        m_intake.stopIndex();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
