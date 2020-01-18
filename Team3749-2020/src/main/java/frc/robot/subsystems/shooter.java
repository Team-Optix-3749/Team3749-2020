package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private VictorSPX shooterMotor;

//checking if this
  public Shooter() {
    shooterMotor = new VictorSPX(Robot.getMap().getCAN("wheel_right"));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}