package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ControlPanel extends SubsystemBase {
  
  private final String gameData = DriverStation.getInstance().getGameSpecificMessage();
  
  private WPI_VictorSPX m_controlPanel = new WPI_VictorSPX(Robot.getConstants().getCAN("intake_motor_r"));


  // public void activateMotor() {
  //   _motor.set(ControlMode.PercentOutput, -1);
  //   _motor1.set(ControlMode.PercentOutput, 1);
  // }

  public void stopMotor(){
    m_controlPanel.set(ControlMode.PercentOutput, 0);
  }

  public void RotationControl(){
    m_controlPanel.set(ControlMode.PercentOutput, 1);
  }

  public void PositionControl(){
      
      if(gameData.length() > 0)
      {
        switch (gameData.charAt(0))
        {
          case 'B' :
            rotateMotor("Red");
            break;
          case 'G' :
            rotateMotor("Yellow");
            break;
          case 'R' :
            rotateMotor("Blue");
            break;
          case 'Y' :
            rotateMotor("Green");
            break;
          default :
            System.out.println("Corrupt Data");
            break;
        }
      } else {
        //Code for no data received yet
    }
  }

  public void rotateMotor(String color) {
    ColorSensor colorSensor = new ColorSensor();
    while ( !colorSensor.DetectColor().equals(color)) {
      m_controlPanel.set(ControlMode.PercentOutput, 1);
    }
  }


 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
