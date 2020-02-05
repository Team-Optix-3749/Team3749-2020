package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;

/**
 *
 */
public class Vision extends Subsystem {

    public Pixy2 pixy = Pixy2.createInstance(new SPILink());

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}