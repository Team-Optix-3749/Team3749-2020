package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

    public SerialPort m_arduinoPort;
    private String message;
    private int m_x, m_y;
    private double m_distance;
    public Timer timer;

    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry xEntry;
    private NetworkTableEntry yEntry;
    private NetworkTableEntry distanceEntry;

    private boolean enabled;

    public Vision() {
      try {
        m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB);
        System.out.println("Connected to arduino in vision!");
      } catch (Exception e) {
        System.out.println("kUSB failed");
  
        try {
          m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB1);
          System.out.println("Connected to arduino!");
        } catch (Exception e1) {
          System.out.println("kUSB1 failed");
          
          try {
            m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB2);
            System.out.println("Connected to arduino!"); 
          } catch(Exception e2){
            System.out.println("kUSB2 failed");
          }
        }
      }

      timer = new Timer();
      timer.start();
        message = new String("");
        m_x = m_y = 0;
        m_distance = 0;
        enabled = false;

        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("Pixy2");
        xEntry = table.getEntry("X");
        yEntry = table.getEntry("Y");
        distanceEntry = table.getEntry("Distance");
    }

    public void readData() {
        if (enabled == true) {
          message = m_arduinoPort.readString();
          System.out.println(message);
          m_x = Integer.parseInt(message.substring(0, 1));
          m_y = Integer.parseInt(message.substring(message.indexOf("a")+1,message.indexOf("b")));
          m_distance = Double.parseDouble(message.substring(message.indexOf("b")+1));
          
          setX(m_x);
          setY(m_y);
          setDist(m_distance);
        }
    }

    public double getX() {
        return xEntry.getDouble(0);
      }
    
      public double getY() {
        return yEntry.getDouble(0);
      }
    
      public double getDist() {
        return distanceEntry.getDouble(0);
      }
    
      public void setX(int entry) {
        xEntry.forceSetDouble(entry);
      }
    
      public void setY(int entry) {
        yEntry.forceSetDouble(entry);
      }
    
      public void setDist(double entry) {
        distanceEntry.forceSetDouble(entry);
      }

      public boolean getStatus() {
        return enabled;
      }

    public String getMessage() {
        return message;
    }

    public void start() {
      enabled = true;
    }

    public void stop() {
        enabled = false;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected void initDefaultCommand() {

    }

}