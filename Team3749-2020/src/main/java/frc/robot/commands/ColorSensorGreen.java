/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ColorSensor;

public class ColorSensorGreen extends CommandBase {
  private final ColorSensor m_sColorSensor;
  private boolean isDone = false;
  private int amountSeen = 0;
  private String targetColor = "Yellow";

  /**
   * Creates a new ColorSensorCommand.
   */
  public ColorSensorGreen(ColorSensor subsystem) {
    m_sColorSensor = subsystem;
    addRequirements(m_sColorSensor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    amountSeen = 0;
    isDone = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    amountSeen++;
    String color = m_sColorSensor.DetectColor();
    if(amountSeen > 40){
      if(color == targetColor){
        isDone = true;
      }
      amountSeen = 0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}
