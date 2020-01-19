package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private VictorSPX shooterMotor = null;

//checking if this
  public Shooter() {
   
   //this assigns an instance to the motor variable 
    // shooterMotor = new VictorSPX(Robot.getConstants().getCAN("wheel_right"));
 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
