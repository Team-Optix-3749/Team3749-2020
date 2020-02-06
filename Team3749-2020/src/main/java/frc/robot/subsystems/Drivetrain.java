package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
// import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
// import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
// import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
// import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
// import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {

    private WPI_TalonSRX m_leftFrontMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_lf"));
    private WPI_TalonSRX m_leftBackMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_lb"));
    public SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftFrontMotor, m_leftBackMotor);

    private WPI_TalonSRX m_rightFrontMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_rf"));
    private WPI_TalonSRX m_rightBackMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_rb"));
    public SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightFrontMotor, m_rightBackMotor);

    // private Encoder m_leftEncoder = new Encoder(0, 1);
    // private Encoder m_rightEncoder = new Encoder(2, 3);

    // private PIDController m_leftPIDController = new PIDController(1, 0, 0);
    // private PIDController m_rightPIDController = new PIDController(1, 0, 0);

    // private final DifferentialDriveKinematics m_kinematics
        // = new DifferentialDriveKinematics(Robot.getConstants().kTrackWidth);

    // private final DifferentialDriveOdometry m_odometry;

    // private final SimpleMotorFeedforward m_feedforward = new SimpleMotorFeedforward(1, 3);

    public DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    public AHRS m_gyro = new AHRS(SPI.Port.kMXP);

    public Drivetrain()
    {
        m_gyro.reset();

        // m_leftEncoder.setDistancePerPulse(2 * Math.PI * Robot.getConstants().kWheelRadius / Robot.getConstants().kEncoderResolution);
        // m_rightEncoder.setDistancePerPulse(2 * Math.PI * Robot.getConstants().kWheelRadius / Robot.getConstants().kEncoderResolution);
    
        // m_leftEncoder.reset();
        // m_rightEncoder.reset();
    
        // m_odometry = new DifferentialDriveOdometry(getAngle());
    }

    public void arcadeDrive(double fwd, double rot) {
        m_drive.arcadeDrive(fwd, rot, true);
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

    // /**
    // * Sets the desired wheel speeds.
    // *
    // * @param speeds The desired wheel speeds.
    // */
    // public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
    //     final double leftFeedforward = m_feedforward.calculate(speeds.leftMetersPerSecond);
    //     final double rightFeedforward = m_feedforward.calculate(speeds.rightMetersPerSecond);

    //     final double leftOutput = m_leftPIDController.calculate(m_leftEncoder.getRate(),
    //         speeds.leftMetersPerSecond);
    //     final double rightOutput = m_rightPIDController.calculate(m_rightEncoder.getRate(),
    //         speeds.rightMetersPerSecond);
    //     m_leftMotors.setVoltage(leftOutput + leftFeedforward);
    //     m_rightMotors.setVoltage(rightOutput + rightFeedforward);
    // }

    // /**
    // * Drives the robot with the given linear velocity and angular velocity.
    // *
    // * @param xSpeed Linear velocity in m/s.
    // * @param rot    Angular velocity in rad/s.
    // */
    // @SuppressWarnings("ParameterName")
    // public void drive(double xSpeed, double rot) {
    //     var wheelSpeeds = m_kinematics.toWheelSpeeds(new ChassisSpeeds(xSpeed, 0.0, rot));
    //     setSpeeds(wheelSpeeds);
    // }


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

    /**
    * Updates the field-relative position.
    */
    // public void updateOdometry() {
    //     m_odometry.update(getAngle(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
    // }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}