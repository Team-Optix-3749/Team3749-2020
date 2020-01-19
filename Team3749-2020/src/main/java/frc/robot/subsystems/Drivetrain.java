package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Drivetrain extends SubsystemBase {

    private WPI_TalonSRX m_leftFrontMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_lf"));
    private WPI_TalonSRX m_leftBackMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_lf"));
    public SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftFrontMotor, m_leftBackMotor);

    private WPI_TalonSRX m_rightFrontMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_rf"));
    private WPI_TalonSRX m_rightBackMotor = new WPI_TalonSRX(Robot.getConstants().getCAN("drive_rb"));
    public SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightFrontMotor, m_rightBackMotor);

    private DifferentialDrive drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    public AHRS gyro = new AHRS(SPI.Port.kMXP);

    public void arcadeDrive(double fwd, double rot) {
        drive.arcadeDrive(fwd, rot);
    }

    public void tankDrive(double fwd, double rot) {
        drive.tankDrive(fwd, rot);
    }

    public void speedLeftMotors(double speed) {
        m_leftFrontMotor.set(ControlMode.PercentOutput, -speed);
        m_leftBackMotor.set(ControlMode.PercentOutput, -speed);
    }

    public void speedRightMotors(double speed) {
        m_rightFrontMotor.set(ControlMode.PercentOutput, speed);
        m_rightBackMotor.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}