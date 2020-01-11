package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private VictorSPX shooterMotor;

  CANPIDController controller;

  double setpoint = 0;

  public Shooter() {
    shooterMotor = new VictorSPX(Robot.getMap().getCAN("wheel_right"));
  }

  public void shoot(double speed) {
    shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void set(double setpoint) {
    controller.setReference(setpoint, ControlType.kVelocity);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}