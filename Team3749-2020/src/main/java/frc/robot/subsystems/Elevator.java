package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;

//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Elevator extends SubsystemBase {
  private WPI_VictorSPX m_elevator = new WPI_VictorSPX(Robot.getConstants().getCAN("elevator_motor"));
  DigitalInput topLimitSwitch = new DigitalInput(1);
  DigitalInput bottomLimitSwitch = new DigitalInput(2);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void startMotor(double speed) {
    if (topLimitSwitch.get() || bottomLimitSwitch.get()) {
      m_elevator.set(ControlMode.PercentOutput, 0);
    } else {
      m_elevator.set(ControlMode.PercentOutput, speed);
    }
  }

  public void stopMotor() {
    m_elevator.set(ControlMode.PercentOutput, 0);
  }
}