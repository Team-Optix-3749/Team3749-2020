// package frc.robot.subsystems;

// import frc.robot.Robot;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.ControlMode;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class LowShooter extends SubsystemBase {
//   private WPI_TalonSRX m_shooterMotorL;
//   private WPI_TalonSRX m_shooterMotorR;

// //checking if this
//   public LowShooter() {
//     m_shooterMotorL = new WPI_TalonSRX(Robot.getConstants().getCAN("wheel_left"));
//     m_shooterMotorR = new WPI_TalonSRX(Robot.getConstants().getCAN("wheel_right"));
//   }

//   public void setSpeed(double speed){
//     //Left motor should rotate the other way
//     m_shooterMotorL.set(ControlMode.PercentOutput, -speed);
//     m_shooterMotorR.set(ControlMode.PercentOutput, speed);
//   }

//   public void LowShoot(){
//     setSpeed(Robot.getConstants().kLowshooterSpeed);
//   }

//   public void Stop(){
//     setSpeed(0);
//   }

//   // public void Reset() {

//   // }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
