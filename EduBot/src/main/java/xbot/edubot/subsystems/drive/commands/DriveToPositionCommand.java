package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.common.math.PIDFactory;
import xbot.common.math.PIDManager;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    public double target_Position;
    PIDManager pid;


    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PIDFactory pf){
        this.drive = driveSubsystem;
        this.pid = pf.createPIDManager("DriveToPoint");
        
        pid.setEnableErrorThreshold(true); // Turn on distance checking
        pid.setErrorThreshold(0.1);
        pid.setEnableDerivativeThreshold(true); // Turn on speed checking
        pid.setDerivativeThreshold(0.1);

        // manually adjust these values to adjust the action
        pid.setP(0.5);
        pid.setD(2);
    }
    
    public void setTargetPosition(double position) {
        target_Position = position;
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
        pid.reset();
    }

    @Override
    public void execute() {
        double currentPosition = drive.distanceSensor.getDistance();
        double power = pid.calculate(target_Position, currentPosition);
        drive.tankDrive(power, power);
    }
    
    @Override
    public boolean isFinished() {
        return pid.isOnTarget();
    }

}
