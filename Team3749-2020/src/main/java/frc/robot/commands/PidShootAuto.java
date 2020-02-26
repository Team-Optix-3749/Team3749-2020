/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class PidShootAuto extends CommandBase {
  private final Shooter m_shooter;
  private long start;

  /**
   * @param subsystem
   */
  public PidShootAuto(Shooter subsystem) {
    m_shooter = subsystem;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    start = System.currentTimeMillis()
  }

  //Gets the distance variable from Pixy2 table
  public double getdistance() {
    NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
    NetworkTable networkTable;
    networkTableInstance.startServer();
    networkTable = networkTableInstance.getTable("Pixy2");
        return networkTable.getEntry("distance").getDouble(10);
 }

 //Calculates and returns the angular velocity needed based on distance away from target
 public double getVelocity() {
   double xDisplacement = (getdistance() * 0.0254) + .207;
   double yDisplacement = 2.49 - Robot.getConstants().kShooterHeight;

   double velocity = (-4.9 * Math.pow(xDisplacement, 2) * Math.pow(Math.acos(Robot.getConstants().kShooterAngle), 2)) /
                      (yDisplacement - Math.asin(Robot.getConstants().kShooterAngle) * xDisplacement * Math.acos(Robot.getConstants().kShooterAngle));
   return Robot.getConstants().kWheelRadius * velocity;
 }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setVelocity(getVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (System.currentTimeMillis() - start > 5000) // check if current time - initial time > 10 seconds
      return true;
  }
}
