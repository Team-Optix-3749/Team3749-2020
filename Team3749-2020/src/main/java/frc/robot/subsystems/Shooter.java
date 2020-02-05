package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * class Shooter
 * @author Raadwan Masum
 */
public class Shooter extends SubsystemBase {

  private TalonSRX m_shooterMotor;
  private StringBuilder m_StringBuilder;

  public Shooter() {

    m_shooterMotor = new TalonSRX(Robot.getConstants().getCAN("shooter_motor"));

    m_StringBuilder = new StringBuilder();


    reset();
  }
  
  private void rawSpeed(double speed){
    m_shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void shoot(){
    rawSpeed(Robot.getConstants().kShooterSpeed);
  }

  public void stop(){
    rawSpeed(0);
  }

  public void reset()
  {
    rawSpeed(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
