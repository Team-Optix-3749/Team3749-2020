package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.commands.ArcadeDrive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class RotateToTarget extends Command {
    private final Drivetrain m_drive;
    private final Vision m_vision;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;

    public RotateToTarget(Drivetrain drive, DoubleSupplier forward, DoubleSupplier rotation, Vision vision) {
           m_rotation = rotation;
           m_drive = drive;
           m_forward = forward;
           m_vision = vision;
    }

   public double getx() {
       NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
       NetworkTable networkTable;
       networkTableInstance.startServer();
       networkTable = networkTableInstance.getTable("networkTable");
           return networkTable.getEntry("x").getDouble(-1);
    }

   private void rotate() {
       Object table;
       double error = getx()-1;
       if (getx() != -1) {
           ((Drivetrain) m_rotation).arcadeDrive(0, 0.3 * error);
        }
    }

   @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
   }
}