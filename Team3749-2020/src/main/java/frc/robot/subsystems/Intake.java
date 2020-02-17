/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    public TalonSRX m_frontIntakeMotor;
    public TalonSRX m_backIntakeMotor;
    public TalonSRX m_frontIndexMotor;
    public TalonSRX m_backIndexMotor;

    public Intake() {
        m_frontIntakeMotor = new TalonSRX(Robot.getConstants().getCAN("intake_motor_f"));
        m_backIntakeMotor = new TalonSRX(Robot.getConstants().getCAN("intake_motor_b"));
        m_frontIndexMotor = new TalonSRX(Robot.getConstants().getCAN("index_motor_f"));
        m_backIndexMotor = new TalonSRX(Robot.getConstants().getCAN("index_motor_b"));
    }

    public void setIntake(double speed) {
        m_frontIntakeMotor.set(ControlMode.PercentOutput, speed);
        m_backIntakeMotor.set(ControlMode.PercentOutput, -speed);
    }
    
    public void setIndex(double speed) {
        m_frontIndexMotor.set(ControlMode.PercentOutput, speed);
        m_backIndexMotor.set(ControlMode.PercentOutput, -speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
