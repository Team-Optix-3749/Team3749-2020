package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;

/**
 *
 */
public class Vision extends Subsystem {

    private static Pixy2 pixy;

    @Override
    protected void initDefaultCommand() {
        pixy = Pixy2.createInstance(new SPILink());
    }
}