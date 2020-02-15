package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.commands.ArcadeDrive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AdjustToTarget extends Command {
    private final Drivetrain m_drive;
    private final Vision m_vision;
    //private final DoubleSupplier m_forward;
    //private final DoubleSupplier m_rotation;
    private boolean continueRotating;

    public AdjustToTarget(Drivetrain drive, Vision vision) {
           m_drive = drive;
           m_vision = vision;
    }

   public double getx() {
       NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
       NetworkTable networkTable;
       networkTableInstance.startServer();
       networkTable = networkTableInstance.getTable("networkTable");
           return networkTable.getEntry("x").getDouble(-1);
    }

    //continueRotating = true;
    //if(continueRotating==true){
    //   rotate();
        
     //   }


   
    private void rotate() {
       Object table;
       double error = getx()-1;
       if (getx() != -1) {
           m_drive.arcadeDrive(0, 0.3 * error);
        }
    }

   

   @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
   }
}