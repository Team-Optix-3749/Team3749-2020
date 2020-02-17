package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {

    private WPI_TalonSRX m_leftFrontMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_lf"));
    private WPI_TalonSRX m_leftBackMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_lb"));
    public SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftFrontMotor, m_leftBackMotor);

    private WPI_TalonSRX m_rightFrontMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_rf"));
    private WPI_TalonSRX m_rightBackMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_rb"));
    public SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightFrontMotor, m_rightBackMotor);

    public DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    public AHRS m_gyro = new AHRS(SPI.Port.kMXP);

    public Drivetrain()
    {
        m_gyro.reset();
    }

    public void arcadeDrive(double fwd, double rot) {
        m_drive.arcadeDrive(fwd * Robot.getConstants().kDriveSpeed, rot, true);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }

    public void speedLeftMotors(double speed) {
        m_leftFrontMotor.set(ControlMode.PercentOutput, -speed);
        m_leftBackMotor.set(ControlMode.PercentOutput, -speed);
    }

    public void speedRightMotors(double speed) {
        m_rightFrontMotor.set(ControlMode.PercentOutput, speed);
        m_rightBackMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setMaxOutput(double maxOutput){
        m_drive.setMaxOutput(maxOutput);
    }

    /**
    * Returns the angle of the robot as a Rotation2d.
    *
    * @return The angle of the robot.
    */
    public Rotation2d getAngle() {
        // Negating the angle because WPILib gyros are CW positive.
        return Rotation2d.fromDegrees(-m_gyro.getAngle());
    }

    /**
    * Resets gyro
    */
    public void resetGyro() {
        m_gyro.reset();
    }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}