/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    public VictorSPX m_frontIntakeMotor;
    public VictorSPX m_backIntakeMotor;

    public Intake() {
        m_frontIntakeMotor = new VictorSPX(Robot.getConstants().getCAN("intake_motor_f"));
        m_backIntakeMotor = new VictorSPX(Robot.getConstants().getCAN("intake_motor_b"));
    }

    public void setIntake(double speed) {
        m_frontIntakeMotor.set(ControlMode.PercentOutput, speed);
        m_backIntakeMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void stopIntake() {
        m_frontIntakeMotor.set(ControlMode.PercentOutput, 0);
        m_backIntakeMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
