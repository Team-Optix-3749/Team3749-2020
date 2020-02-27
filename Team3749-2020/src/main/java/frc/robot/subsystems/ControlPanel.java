package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

// import edu.wpi.first.wpilibj.I2C;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ControlPanel extends SubsystemBase {
  // private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensor colorSensor = new ColorSensor();
  private final String gameData = DriverStation.getInstance().getGameSpecificMessage();
  
  private WPI_VictorSPX m_controlPanel = new WPI_VictorSPX(Robot.getConstants().getCAN("intake_motor_r"));



  public void stopMotor(){
    m_controlPanel.set(ControlMode.PercentOutput, 0);
  }

  public void RotationControl(){
    int noOfPasses = 0;
    String firstColor = colorSensor.DetectColor();
    String currentColor = firstColor;
    while (noOfPasses < 8) {
      m_controlPanel.set(ControlMode.PercentOutput, Robot.getConstants().controlPanelSpeed);
      if (!colorSensor.DetectColor().equals(currentColor)) {
        currentColor = colorSensor.DetectColor();
        if (currentColor == firstColor) {
          noOfPasses++;
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
