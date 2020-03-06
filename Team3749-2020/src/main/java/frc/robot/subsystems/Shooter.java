package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * class Shooter
 * 
 * @author Raadwan Masum
 */
public class Shooter extends SubsystemBase {

    private CANSparkMax m_shooterMotor;
    CANEncoder m_encoder;
    CANPIDController m_controller;

    public Shooter() {
        m_shooterMotor = new CANSparkMax(Robot.getConstants().getCAN("shooter_motor"), MotorType.kBrushless);

        m_shooterMotor.setIdleMode(IdleMode.kCoast);
        m_encoder = m_shooterMotor.getEncoder();
        m_controller = m_shooterMotor.getPIDController();
        m_controller.setFeedbackDevice(m_encoder);
        stop();
    }

    public void set(double velocity) {
        double setpoint = velocity * 500.0 * 6000 / 600;
        m_controller.setReference(setpoint, ControlType.kVelocity);
    }

    public void stop() {
        m_controller.setReference(0, ControlType.kDutyCycle);
    }

    public void updateConstants() {
    m_controller.setOutputRange(-1, 0);
        m_controller.setP(Robot.getConstants().shooterP);
        m_controller.setI(Robot.getConstants().shooterI);
        m_controller.setD(Robot.getConstants().shooterD);
        m_controller.setFF(Robot.getConstants().shooterF);
    }

    @Override
    public void periodic() {
    }
}
