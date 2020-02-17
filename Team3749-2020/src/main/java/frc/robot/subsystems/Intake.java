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
    public VictorSPX m_rightIntakeMotor;
    public VictorSPX m_leftIntakeMotor;
    public VictorSPX m_frontIndexMotor;
    public VictorSPX m_backIndexMotor;

    public Intake() {
        m_rightIntakeMotor = new VictorSPX(Robot.getConstants().getCAN("intake_motor_r"));
        m_leftIntakeMotor = new VictorSPX(Robot.getConstants().getCAN("intake_motor_l"));
        m_frontIndexMotor = new VictorSPX(Robot.getConstants().getCAN("index_motor_f"));
        m_backIndexMotor = new VictorSPX(Robot.getConstants().getCAN("index_motor_b"));
    }

    public void setIntake(double speed) {
        m_rightIntakeMotor.set(ControlMode.PercentOutput, speed);
        m_leftIntakeMotor.set(ControlMode.PercentOutput, speed);
    }
    
    public void stopIntake() {
        m_rightIntakeMotor.set(ControlMode.PercentOutput, 0);
        m_leftIntakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void setIndex(double speed) {
        m_frontIndexMotor.set(ControlMode.PercentOutput, speed);
        m_backIndexMotor.set(ControlMode.PercentOutput, -speed);
    }

    public void stopIndex() {
        m_frontIndexMotor.set(ControlMode.PercentOutput, 0);
        m_backIndexMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
