// package frc.robot.commands;

// import frc.robot.Constants;
// import frc.robot.Robot;
// import frc.robot.subsystems.BallCollect;
// import edu.wpi.first.wpilibj2.command.CommandBase;

// public class Collect extends CommandBase {
//     public final BallCollect collector;

//     public Collect(final BallCollect collect) {
//         collector = collect;
//     }

//     @Override
//     public void execute() {
//         Robot.getBallCollect().collectMotor.set(Constants.collectSpeed);
//     }

//     @Override
//     public boolean isFinished() {
//         return false;
//     }
// }