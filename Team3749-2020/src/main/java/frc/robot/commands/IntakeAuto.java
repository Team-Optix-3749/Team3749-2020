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

public class IntakeAuto extends CommandBase {
    private final Intake m_intake;
    private double speed;
    private long start;

    /**
     * 
     * @param subsystem subsystem
     * @param speed set speed
     */
    public IntakeAuto(Intake subsystem, double set) {
        m_intake = subsystem;
        addRequirements(m_intake);

        speed = set;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        start = System.currentTimeMillis();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_intake.setIntake(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(System.currentTimeMillis() - start > 5000)
            return true;
    }
}
