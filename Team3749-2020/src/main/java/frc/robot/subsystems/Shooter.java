package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * class Shooter
 * @author Raadwan Masum
 */
public class Shooter extends SubsystemBase {

  private final double ENCODER_IN = 1100000;
  private final double ENCODER_OUT = 100;

  private TalonSRX m_shooterMotor;

  private double velocity;
  private double motorOut;
  private boolean pidEnabled;

  private final double kAF = 0.08;

  public Shooter() {

    m_shooterMotor = new TalonSRX(Robot.getConstants().getCAN("shooter_motor"));
    
    m_shooterMotor.config_kP(0, 0.003);
    m_shooterMotor.config_kI(0, 0);
    m_shooterMotor.config_kD(0, 0.00003);
    m_shooterMotor.config_kF(0, 0);

    m_shooterMotor.setSensorPhase(true);
    m_shooterMotor.setInverted(true);

    m_shooterMotor.configClosedloopRamp(1);

    velocity = 0;
    motorOut = 0;
    pidEnabled =  true;

    reset();
  }

  public void setVelocity(double pos)
  {
    if (pidEnabled)
    {
      velocity  += toEncoder(pos);
    }
    else
    {
      motorOut = kAF + pos * 0.4;
    }
    update();
  }

  // public void pidSpeed(double feedforward){
  //   double targetPos = 1;
  //   m_shooterMotor.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, feedforward);
  // }
  
  private void rawSpeed(double speed){
    m_shooterMotor.set(ControlMode.PercentOutput, speed);
  }

  public void shoot(){
    rawSpeed(Robot.getConstants().kShooterSpeed);
  }

  public void stop(){
    rawSpeed(0);
  }

  public double getPosition()
  {
    return fromEncoder(m_shooterMotor.getSelectedSensorPosition());
  }

  public double getSetpoint()
  {
    return fromEncoder(velocity);
  }

  public void reset()
  {
    m_shooterMotor.setSelectedSensorPosition(0);
    rawSpeed(0);
  }
  
  private void update()
  {
    if (pidEnabled)
    {
      if (velocity > ENCODER_IN)
      velocity = ENCODER_IN;
      if (velocity < toEncoder(0))
      velocity = toEncoder(0);
      
      m_shooterMotor.set(ControlMode.Velocity, velocity, DemandType.ArbitraryFeedForward, kAF);
    }
    else
    {
      rawSpeed(motorOut);
    }
  }

  public boolean usingPid()
  {
    return pidEnabled;
  }

  public void setPidEnabled(boolean bool)
  {
    pidEnabled = bool;
  }

  private double fromEncoder(double in)
  {
    // become bigger
    return in * ENCODER_OUT / ENCODER_IN;
  }

  private double toEncoder(double in)
  {
    // become smaller
    return in * ENCODER_IN / ENCODER_OUT;
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
