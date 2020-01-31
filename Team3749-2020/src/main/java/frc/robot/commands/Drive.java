package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Drive extends CommandBase {
    private final Drivetrain m_drive;
    
    private final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(3);
    private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(3);

    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    double xSpeed =
        -m_speedLimiter.calculate(Robot.getRobotContainer().m_xboxController.getY(Hand.kLeft))
            * Robot.getConstants().kMaxSpeed;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    double rot =
        -m_rotLimiter.calculate(Robot.getRobotContainer().m_xboxController.getX(Hand.kRight))
            * Robot.getConstants().kMaxAngularSpeed;

    public Drive(Drivetrain drive, double xSpeed, double rot) {
        m_drive = drive;


        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        m_drive.drive(xSpeed, rot);
    }
}