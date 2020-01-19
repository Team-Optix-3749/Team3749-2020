package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Drivetrain extends SubsystemBase {

    private WPI_TalonSRX motorLeftFront = new WPI_TalonSRX(Constants.m_leftFrontMotor);
    private WPI_TalonSRX motorLeftBack = new WPI_TalonSRX(Constants.m_leftBackMotor);
    public SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(motorLeftFront, motorLeftBack);

    private WPI_TalonSRX motorRightFront = new WPI_TalonSRX(Constants.m_rightFrontMotor);
    private WPI_TalonSRX motorRightBack = new WPI_TalonSRX(Constants.m_rightBackMotor);
    public SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(motorRightFront, motorRightBack);

    private DifferentialDrive drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    public AHRS gyro = new AHRS(SPI.Port.kMXP);

    public void arcadeDrive(double fwd, double rot) {
        drive.arcadeDrive(fwd, rot);
    }

    public void tankDrive(double fwd, double rot) {
        drive.tankDrive(fwd, rot);
    }

    public void speedLeftMotors(double speed) {
        motorLeftFront.set(ControlMode.PercentOutput, -speed);
        motorLeftBack.set(ControlMode.PercentOutput, -speed);
    }

    public void speedRightMotors(double speed) {
        motorRightFront.set(ControlMode.PercentOutput, speed);
        motorRightBack.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}