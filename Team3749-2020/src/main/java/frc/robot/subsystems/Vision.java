package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

    private static I2C m_arduinoPort = new I2C(Port.kOnboard, 4);

    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry xEntry;
    private NetworkTableEntry yEntry;
    private NetworkTableEntry distanceEntry;

    private boolean enabled;

    private static final int MAX_BYTES = 32;

    public Vision() {
        enabled = false;

        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("Pixy2");
        xEntry = table.getEntry("X");
        yEntry = table.getEntry("Y");
        distanceEntry = table.getEntry("Distance");
    }

    public void readData() {
        if (enabled == true) {
          String[] message = read().split("ab");

          if(message[0].equals("0") && message[1].equals("0")) {
            setX(Integer.parseInt("0"));
            setX(Integer.parseInt("0"));
            setDist(Double.parseDouble("0"));
          }
          else if(message.length == 3) {
            setX(Integer.parseInt(message[0]));
            setX(Integer.parseInt(message[1]));
            setDist(Double.parseDouble(message[2]));
          }
        }
    }

    private String read(){
      byte[] data = new byte[MAX_BYTES];
      m_arduinoPort.read(4, MAX_BYTES, data);
      String output = new String(data);
      int pt = output.indexOf((char)255);
      return (String) output.subSequence(0, pt < 0 ? 0 : pt);
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

    public void start() {
      enabled = true;
    }

    public void stop() {
        enabled = false;
    }

    @Override
    protected void initDefaultCommand() {

    }

}

