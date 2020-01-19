package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX shooterMotor = null;

//checking if this
  public Shooter() {
    shooterMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("wheel_right"));
  }

  private void setSpeed(double speed){
    shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void Shoot(){
    setSpeed(Robot.getConstants().shooterSpeed);
  }

  public void Stop(){
    setSpeed(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
