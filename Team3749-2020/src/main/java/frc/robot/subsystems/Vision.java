package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends SubsystemBase {

  private NetworkTableInstance inst = new NetworkTableInstance;
  private NetworkTable table = new NetworkTable;
  private NetworkTableEntry xEntry = new NetworkTableEntry;
  private NetworkTableEntry yEntry = new NetworkTableEntry;
  private NetworkTableEntry distanceEntry = new NetworkTableEntry;
  
  public Vision() {
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("example");
    xEntry = networkTable.getEntry("X");
    yEntry = networkTable.getEntry("Y");
    distanceEntry = networkTable.getEntry("Distance");
  }

  public double getX() {
    return xEntry.getDouble();
  }

  public double getY() {
    return yEntry.getDouble();
  }

  public double getDist() {
    return distanceEntry.getDouble();
  }

  public void setX(double entry) {
    xEntry.forceSetDouble(entry);
  }

  public void setY(double entry) {
    yEntry.forceSetDouble(entry);
  }

  public void setDist(double entry) {
    distanceEntry.forceSetDouble(entry);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}