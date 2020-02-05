package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * class Shooter
 * @author Raadwan Masum
 */
public class Shooter extends SubsystemBase {

  private TalonSRX m_shooterMotor;
  private StringBuilder m_StringBuilder;
  private int kloops = 0;

  public Shooter() {

    m_shooterMotor = new TalonSRX(Robot.getConstants().getCAN("shooter_motor"));
    m_StringBuilder = new StringBuilder();
    
    /* Factory Default all hardware to prevent unexpected behaviour */
    m_shooterMotor.configFactoryDefault();

		/* Config sensor used for Primary PID [Velocity] */
        m_shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                                            Robot.getConstants().kPIDLoopIdx, 
                                            Robot.getConstants().kTimeoutMs);

        /**
		 * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
		m_shooterMotor.setSensorPhase(true);

		/* Config the peak and nominal outputs */
		m_shooterMotor.configNominalOutputForward(0, Robot.getConstants().kTimeoutMs);
		m_shooterMotor.configNominalOutputReverse(0, Robot.getConstants().kTimeoutMs);
		m_shooterMotor.configPeakOutputForward(1, Robot.getConstants().kTimeoutMs);
		m_shooterMotor.configPeakOutputReverse(-1, Robot.getConstants().kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		m_shooterMotor.config_kF(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kF, Robot.getConstants().kTimeoutMs);
		m_shooterMotor.config_kP(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kP, Robot.getConstants().kTimeoutMs);
		m_shooterMotor.config_kI(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kI, Robot.getConstants().kTimeoutMs);
		m_shooterMotor.config_kD(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kD, Robot.getConstants().kTimeoutMs);

    reset();
  }
  
  private void rawSpeed(double speed){
    m_shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void shoot(){
    rawSpeed(Robot.getConstants().kShooterSpeed);
  }

  public void stop(){
    rawSpeed(0);
  }

  public void reset()
  {
    rawSpeed(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
