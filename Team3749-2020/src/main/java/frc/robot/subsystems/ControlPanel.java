/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ControlPanel extends SubsystemBase {
  VictorSPX _motor = new VictorSPX(20);
  TalonSRX _motor1 = new TalonSRX(11);

  public void activateMotor() {
    _motor.set(ControlMode.PercentOutput, -1);
    _motor1.set(ControlMode.PercentOutput, 1);
  }

  public void stopMotor(){
    _motor.set(ControlMode.PercentOutput, 0);
    _motor1.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
