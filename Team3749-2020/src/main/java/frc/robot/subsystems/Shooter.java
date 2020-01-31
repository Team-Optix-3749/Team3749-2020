package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX m_shooterMotor;

  public Shooter() {
    m_shooterMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("shooter_motor"));
  }

  // fix this:
  public void pidSpeed(double feedforward){
    double targetPos = 1;
    m_shooterMotor.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, feedforward);
  }
  
  private void setSpeed(double speed){
    m_shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void shoot(){
    setSpeed(Robot.getConstants().kShooterSpeed);
  }

  public void stop(){
    setSpeed(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
