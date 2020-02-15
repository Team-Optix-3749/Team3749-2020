package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.commands.ReadTargetData;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AdjustToTarget extends CommandBase {
    private final Drivetrain m_drive;
    private final Vision m_vision;
    private final ReadTargetData data;

    public AdjustToTarget(Drivetrain drive, Vision vision) {
           m_drive = drive;
           m_vision = vision;
           data = new ReadTargetData(m_vision);
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

    //continueRotating = true;
    //if(continueRotating==true){
    //   rotate();
        
     //   }


    @Override
    public void execute() {
       double error = getx()-1;
       if (getx() != -1) {
           m_drive.arcadeDrive(0, 0.3 * error);
        }
        if (getdistance() != 10) {
            m_drive.arcadeDrive(0.3, 0);
        }
        data.end(false);

    }

}