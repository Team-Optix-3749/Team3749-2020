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
    public TalonSRX m_frontCollectMotor;
    public TalonSRX m_backCollectMotor;

    public Intake() {
        m_frontCollectMotor = new TalonSRX(Robot.getConstants().getCAN("intake_motor_f"));
        m_backCollectMotor = new TalonSRX(Robot.getConstants().getCAN("intake_motor_b"));
    }

    public void set(double speed) {
        m_frontCollectMotor.set(ControlMode.PercentOutput, speed);
        m_backCollectMotor.set(ControlMode.PercentOutput, -speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
