package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

    private SerialPort m_arduinoPort;
    private String message;
    private int m_x, m_y;
    private double m_distance;

    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry xEntry;
    private NetworkTableEntry yEntry;
    private NetworkTableEntry distanceEntry;

    public Vision() {
        m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB);
        message = new String("");
        m_x = 0;
        m_y = 0;

        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("example");
        xEntry = table.getEntry("X");
        yEntry = table.getEntry("Y");
        distanceEntry = table.getEntry("Distance");
    }

    public void readData() {
        message = m_arduinoPort.readString();
        m_x = Integer.parseInt(message.substring(0, 1));
        m_y = Integer.parseInt(message.substring(message.indexOf("a")+1,message.indexOf("b")));
        m_distance = Double.parseDouble(message.substring(message.indexOf("b")+1));
        
        setX(m_x);
        setY(m_y);
        setDist(m_distance);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

}

