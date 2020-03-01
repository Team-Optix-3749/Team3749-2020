package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * class Shooter
 * 
 * @author Raadwan Masum
 */
public class Shooter extends SubsystemBase {

  private TalonSRX m_shooterMotor;

  /* String for output */
  private StringBuilder m_stringBuilder;

  /* Loop tracker for prints */
  int kLoops = 0;

  private double motorOutput;

  public Shooter() {
    m_shooterMotor = new TalonSRX(Robot.getConstants().getCAN("shooter_motor"));
    m_stringBuilder = new StringBuilder();

    /* Factory Default all hardware to prevent unexpected behaviour */
    m_shooterMotor.configFactoryDefault();

    /**
     * Phase sensor accordingly. Positive Sensor Reading should match Green
     * (blinking) Leds on Talon
     */
    m_shooterMotor.setSensorPhase(true);

    /* Config the peak and nominal outputs */
    m_shooterMotor.configNominalOutputForward(0, Robot.getConstants().kTimeoutMs);
    m_shooterMotor.configNominalOutputReverse(0, Robot.getConstants().kTimeoutMs);
    m_shooterMotor.configPeakOutputForward(1, Robot.getConstants().kTimeoutMs);
    m_shooterMotor.configPeakOutputReverse(-1, Robot.getConstants().kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    m_shooterMotor.config_kF(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kF,
        Robot.getConstants().kTimeoutMs);
    m_shooterMotor.config_kP(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kP,
        Robot.getConstants().kTimeoutMs);
    m_shooterMotor.config_kI(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kI,
        Robot.getConstants().kTimeoutMs);
    m_shooterMotor.config_kD(Robot.getConstants().kPIDLoopIdx, Robot.getConstants().kGains_Velocit.kD,
        Robot.getConstants().kTimeoutMs);

    motorOutput = m_shooterMotor.getMotorOutputPercent();

    reset();
  }

  private void rawSpeed(double speed) {
    m_shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void shoot() {
    rawSpeed(Robot.getConstants().kShooterSpeed);
  }

  public void strings() {
    /* Prepare line to print */
    m_stringBuilder.append("\tout:");
    /* Cast to int to remove decimal places */
    m_stringBuilder.append((int) (motorOutput * 100));
    m_stringBuilder.append("%"); // Percent

    m_stringBuilder.append("\tspd:");
    m_stringBuilder.append(m_shooterMotor.getSelectedSensorVelocity(Robot.getConstants().kPIDLoopIdx));
    m_stringBuilder.append("u"); // Native units
  }

  /**
   * Set desired shooter velocity
   * 
   * @param velocity desired velocity (values -1 to 1)
   */
  public void setVelocity(double velocity) {
    /* Velocity Closed Loop */

    /**
     * Convert 500 RPM to units / 100ms. 4096 Units/Rev * 500 RPM / 600 100ms/min in
     * either direction: velocity setpoint is in units/100ms
     */

    /**
			* Convert 500 RPM to units / 100ms.
			* 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			* velocity setpoint is in units/100ms
			*/
		double targetVelocity_UnitsPer100ms = velocity * 500.0 * 4096 / 600;

    /* 500 RPM in either direction */
    m_shooterMotor.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

    /* Append more signals to print when in speed mode. */
    m_stringBuilder.append("\terr:");
    m_stringBuilder.append(m_shooterMotor.getClosedLoopError(Robot.getConstants().kPIDLoopIdx));
    m_stringBuilder.append("\ttrg:");
    m_stringBuilder.append(velocity);
  }

  public void stop() {
    rawSpeed(0);
  }

  public void reset() {
    rawSpeed(0);
  }

  @Override
  public void periodic() {
    strings();

    /* Print built string every 10 loops */
    if (++kLoops >= 10) {
      kLoops = 0;
      System.out.println(m_stringBuilder.toString());
    }
    /* Reset built string */
    m_stringBuilder.setLength(0);
  }
}
