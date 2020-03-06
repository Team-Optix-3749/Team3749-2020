/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ControlPanelSimple extends SubsystemBase {
    public VictorSPX m_motor;

    public ControlPanelSimple() {
        m_motor = new VictorSPX(Robot.getConstants().getCAN("control_panel_motor"));
    }

    public void set(double speed) {
        m_motor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        m_motor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
