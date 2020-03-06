/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSimple;

public class ControlPanelCommand extends CommandBase {
    private final ControlPanelSimple m_controlPanel;

    private final Timer m_timer = new Timer();

    public ControlPanelCommand(ControlPanelSimple subsystem) {
        m_controlPanel = subsystem;
        addRequirements(m_controlPanel);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_timer.reset();
        m_timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_timer.get() < 4.0) {
            m_controlPanel.set(1); // drive forwards half speed
        } else {
            m_controlPanel.stop();
            ; // stop robot
        }
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
