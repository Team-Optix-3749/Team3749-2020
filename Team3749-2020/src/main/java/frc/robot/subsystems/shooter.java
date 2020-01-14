package frc.robot.subsystems;

import frc.robot.Constants;
// import frc.robot.Robot;

// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  // private VictorSPX shooterMotor;
  private CANSparkMax shooterMotor;

  CANEncoder encoder;
  CANPIDController controller;

  double setpoint = 0;
//checking if this works
  public Shooter() {
    // shooterMotor = new VictorSPX(Robot.getMap().getCAN("wheel_right"));
    CANSparkMax[] shooter_motor = new CANSparkMax[] {
      new CANSparkMax(Constants.shooterMotor, MotorType.kBrushless),
    };
  
  }

  // public void shoot(double speed) {
  //   shooter_motor.set(ControlMode.PercentOutput, speed);
  // }

  public void set(double setpoint) {
    controller.setReference(setpoint, ControlType.kVelocity);
  }

  public void stop() {
    controller.setReference(0, ControlType.kDutyCycle);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}