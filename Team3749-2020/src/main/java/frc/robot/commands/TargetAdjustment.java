package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TargetAdjustment extends CommandBase {
    private final Drivetrain m_drive;

    public TargetAdjustment(Drivetrain subsystem) {
           m_drive = subsystem;
    }

   public double getx() {
       NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
       NetworkTable networkTable;
       networkTableInstance.startServer();
       networkTable = networkTableInstance.getTable("networkTable");
           return networkTable.getEntry("x").getDouble(-1);
    }

    public double getdistance() {
        NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
        NetworkTable networkTable;
        networkTableInstance.startServer();
        networkTable = networkTableInstance.getTable("networkTable");
            return networkTable.getEntry("distance").getDouble(10);
     }

    @Override
    public void execute() {
       double error = getx()-1;
       while(getx() != -1) {
           m_drive.arcadeDrive(0, 0.3 * error);
        }
        while (getdistance() >= 10) {
            m_drive.arcadeDrive(0.3, 0);
        }
        // data.end(false);

    }

}