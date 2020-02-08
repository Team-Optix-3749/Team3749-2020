package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
//import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

    private SerialPort m_arduinoPort;
    private String message;
    private int m_x, m_y;
    private double m_distance;

    public Vision() {
        m_arduinoPort = new SerialPort(115200, SerialPort.Port.kUSB);
        message = new String("");
        m_x = 0;
        m_y = 0;
    }

    public void readData() {
        message = m_arduinoPort.readString();
        m_x = Integer.parseInt(message.substring(0, 1));
        m_y = Integer.parseInt(message.substring(message.indexOf("a")+1,message.indexOf("b")));
        m_distance = Double.parseDouble(message.substring(message.indexOf("b")+1));
        System.out.print("x = " + m_x + " y = " + m_y + " distance = " + m_distance);
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

