/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//package frc.robot.subsystems;

//import frc.robot.Robot;
//import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;

//public class BallCollect extends SubsystemBase {

//  public m_leftCollectMotor = new Spark(Robot.getConstants().getCAN("collect_l"));
//  public m_rightCollectMotor = new Spark(Robot.getConstants().getCAN("collect_r")); //need to set these later lol

//  public BallCollect() {
//    stopCollect();
//  }
  
//  public void collect(double speed) {
//    m_leftCollectMotor.set(speed);
//    m_rightCollectMotor.set(-speed);
//  }

//  public void eject(double speed) {
//    m_leftCollectMotor.set(-speed);
//    m_rightCollectMotor.set(speed);
// }
  
//  public void stopCollectMotors() {
//    m_leftCollectMotor.stopMotor();
//    m_rightCollectMotor.stopMotor();
//  }

//  @Override
//  public void periodic() {
    // This method will be called once per scheduler run
//  }
//}
