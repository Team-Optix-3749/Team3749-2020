/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361083984375, 0.52392578125, 0.113037109375);

  public String DetectColor() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 

    Color detectedColor = m_colorSensor.getColor();

    String colorString; 
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    
    System.out.println(match.color.red);
    System.out.println(match.color.green);
    System.out.println(match.color.blue);
    System.out.println(match.confidence);

    if (match.color == kBlueTarget && match.confidence > 0.94) {
      colorString = "Blue";
    } else if (match.color == kRedTarget && match.confidence > 0.94) {
      colorString = "Red";
    } else if (match.color == kGreenTarget && match.confidence > 0.94) {
      colorString = "Green";
    } else if (match.color == kYellowTarget && match.confidence > 0.94) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }

  @Override
  public void periodic() {
    //System.out.println("Color Sensor Activated");
  }
}
