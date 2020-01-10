package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Drivetrain extends SubsystemBase {

    private WPI_TalonSRX motorLeftFront = new WPI_TalonSRX(Constants.motorLeftFront);
    private WPI_TalonSRX motorLeftBack = new WPI_TalonSRX(Constants.motorLeftBack);
    public SpeedControllerGroup leftSideMotors = new SpeedControllerGroup(motorLeftFront, motorLeftBack);

    private WPI_TalonSRX motorRightFront = new WPI_TalonSRX(Constants.motorRightFront);
    private WPI_TalonSRX motorRightBack = new WPI_TalonSRX(Constants.motorRightBack);
    public SpeedControllerGroup rightSideMotors = new SpeedControllerGroup(motorRightFront, motorRightBack);

    private DifferentialDrive drive = new DifferentialDrive(leftSideMotors, rightSideMotors);

    public void arcadeDrive() {
        drive.arcadeDrive(Constants.LSX, Constants.LSY);
    }

    public void tankDrive() {
        drive.tankDrive(Constants.LSY, Constants.RSY);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}