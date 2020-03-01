package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ControlPanel extends SubsystemBase {
  private final ColorSensor colorSensor = new ColorSensor();
  private final String gameData = DriverStation.getInstance().getGameSpecificMessage();
  
  private TalonSRX m_controlPanel = new TalonSRX(Robot.getConstants().getCAN("control_panel_motor"));



  public void stopMotor(){
    m_controlPanel.set(ControlMode.PercentOutput, 0);
  }

  public void RotationControl(){
    int NumberOfPasses = 0;
    String firstColor = colorSensor.DetectColor();
    String currentColor = firstColor;
    while (NumberOfPasses < 8) {
      m_controlPanel.set(ControlMode.PercentOutput, Robot.getConstants().controlPanelSpeed);
      if (!colorSensor.DetectColor().equals(currentColor)) {
        currentColor = colorSensor.DetectColor();
        if (currentColor == firstColor) {
          NumberOfPasses++;
        }
      }
    }
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
    while ( !colorSensor.DetectColor().equals(color)) {
      m_controlPanel.set(ControlMode.PercentOutput, Robot.getConstants().controlPanelSpeed);
    }
  }


 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
