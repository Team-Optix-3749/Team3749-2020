/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ColorSensor;

public class ColorSensorCommand extends CommandBase {
  
  private final ColorSensor m_sColorSensor;
  private String initialColor = "Unknown";
  private boolean isDone = false;
  private int timesSeen = 0;
  private int amountSeen = 0;
  private String currentColor = "Unknown";
  private int rotations = 0;

  /**
   * Creates a new ColorSensorCommand.
   */
  public ColorSensorCommand(ColorSensor subsystem) {
    m_sColorSensor = subsystem;
    addRequirements(m_sColorSensor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialColor = m_sColorSensor.DetectColor();
    currentColor = m_sColorSensor.DetectColor();
    System.out.println("Initial color is: " + initialColor);
    timesSeen = 0;
    amountSeen = 0;
    rotations = 0;
    isDone = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    amountSeen++;
    String color = m_sColorSensor.DetectColor();
    System.out.println(color);
    if(currentColor != color && amountSeen > 40){
      if(color == initialColor){
        timesSeen++;
        System.out.println("Times Seen: " + timesSeen);
      }
      currentColor = color;
      System.out.println(currentColor);
      amountSeen = 0;
    }
    if(timesSeen == 2){
      rotations++;
      timesSeen = 0;
    }

    if(rotations == 4){
      isDone = true;
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
