package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.SerialPort;

public class Vision {
  private NetworkTable table;
  private SerialPort m_arduinoPort;
  private double pixy_xVal = -1;

  public Vision() {
    table = NetworkTableInstance.getDefault().getTable("limelight");

    /*Instantiating arduino port as SerialPort, trying every instantiation
        with kUSB, kUSB1, and kUSB2. We used try-catch to see which instantiation failed*/
        try {
          m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB);
          System.out.println("Connected to arduino in vision!");
        } catch (Exception e) {
          System.out.println("kUSB failed");
    
          try {
            m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB1);
            System.out.println("Connected to arduino in vision!");
          } catch (Exception e1) {
            System.out.println("kUSB1 failed");
            
            try {
              m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB2);
              System.out.println("Connected to arduino in vision!"); 
            } catch(Exception e2){
              System.out.println("kUSB2 failed");
            }
          }
        }
  }

  public void readData() {
    try {
      pixy_xVal = Integer.parseInt(m_arduinoPort.readString());
    } catch(Exception e) {
      pixy_xVal = -1;
    }
  }

  public double getPixyXValue() {
    return pixy_xVal;
  }

  public double getAngleX() {
    return table.getEntry("tx").getDouble(0);
  }

  public double getAngleY() {
    return table.getEntry("ty").getDouble(0);
  }

  public double getArea() {
    return table.getEntry("ta").getDouble(0);
  }

  public double getSkew() {
    return table.getEntry("ts").getDouble(0);
  }

  public double getDist() {
    return (2.496 - 0.991) / Math.tan(getAngleY());
  }
}