package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;


public class Elevator extends SubsystemBase {
    //include more specific code and stuff, setting up the stuff for the lifting mechanism team-skywalker
    private WPI_TalonSRX m_left = new WPI_TalonSRX(Robot.getConstants().getCAN("elevatorLeft"));
    private WPI_TalonSRX m_right = new WPI_TalonSRX(Robot.getConstants().getCAN("elevatorRight"));
    


    @Override
    public void periodic() { 
      // This method will be called once per scheduler run

    }

    public void startMotor(double speed) {

      m_left.set(ControlMode.PercentOutput, speed);
      m_right.set(ControlMode.PercentOutput,speed);
    }

    public void stopMotor() {

      
      m_left.set(ControlMode.PercentOutput, 0);
      m_right.set(ControlMode.PercentOutput, 0);
    }
    //Do we need a set position??
}