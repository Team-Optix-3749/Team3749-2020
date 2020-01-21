package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Elevator extends SubsystemBase {
    //include more specific code and stuff, setting up the stuff for the lifting mechanism team-skywalker
    private WPI_TalonSRX leftMotorControl;
    private WPI_TalonSRX rightMotorControl;

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    public void moveToTop() {

    }
 
    public void moveToBottom() {

    }

    //Do we need a set position??
}