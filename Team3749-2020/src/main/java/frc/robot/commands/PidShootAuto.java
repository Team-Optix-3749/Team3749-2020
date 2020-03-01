/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class PidShootAuto extends CommandBase {
  private final Shooter m_shooter;
  private final Vision m_vision;
  private long start;
  private boolean isFinished = false;

  /**
   * @param subsystem
   */
  public PidShootAuto(Shooter subsystem1, Vision subsystem2) {
    m_shooter = subsystem1;
    m_vision = subsystem2;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    start = System.currentTimeMillis();
  }

 //Calculates and returns the angular velocity needed based on distance away from target
 public double getVelocity() {
   double xDisplacement = (m_vision.getDist() * 0.0254) + .207;
   double yDisplacement = 2.49 - Robot.getConstants().kShooterHeight;

   double velocity = (-4.9 * Math.pow(xDisplacement, 2) * Math.pow(Math.acos(Robot.getConstants().kShooterAngle), 2)) /
                      (yDisplacement - Math.sin(Robot.getConstants().kShooterAngle) * xDisplacement * Math.cos(Robot.getConstants().kShooterAngle));
   return Robot.getConstants().kWheelRadius * velocity;
 }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(System.currentTimeMillis() - start > 5000)
      m_shooter.setVelocity(getVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(System.currentTimeMillis() - start > 10000)
          isFinished = true;
          return isFinished;
  }
}
