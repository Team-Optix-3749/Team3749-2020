package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private VictorSPX shooterMotor;

  public Shooter() {
    shooterMotor = new VictorSPX(Robot.getMap().getCAN("wheel_right"));
  }

  public void shoot(double speed) {
    shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}