package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ControlPanel extends SubsystemBase {
  
  private final String gameData = DriverStation.getInstance().getGameSpecificMessage();
  
  VictorSPX _motor = new VictorSPX(20);
  TalonSRX _motor1 = new TalonSRX(11);

  // public void activateMotor() {
  //   _motor.set(ControlMode.PercentOutput, -1);
  //   _motor1.set(ControlMode.PercentOutput, 1);
  // }

  public void stopMotor(){
    _motor.set(ControlMode.PercentOutput, 0);
    _motor1.set(ControlMode.PercentOutput, 0);
  }

  public void RotationControl(){
    _motor.set(ControlMode.PercentOutput, -1);
    _motor1.set(ControlMode.PercentOutput, 1);
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
      _motor.set(ControlMode.PercentOutput, -1);
      _motor1.set(ControlMode.PercentOutput, 1);  
    }
  }


 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
