package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    public double target_Position;
    double power;
    double distance_away;
    double Cdistance;
    double last_point;
    double speed;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
        this.power = 1;
        this.distance_away = 0;
        this.speed = 0;
        this.Cdistance = 0;
        this.last_point = 0;

    }
    
    public void setTargetPosition(double position) {
        target_Position = position;
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position 
        // - Hint: use drive.distanceSensor.get() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
        
        Cdistance = drive.distanceSensor.getDistance();
        distance_away = target_Position - drive.distanceSensor.getDistance();
        power = distance_away / target_Position;
        speed = (power * 4.2) - ((Cdistance - last_point) * 4.2); 
        drive.tankDrive(speed, speed);
        last_point = Cdistance;
    }
    
    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal, 
        // and you're moving fairly slowly (ideally stopped)

        if (Math.abs(drive.distanceSensor.getDistance() - target_Position) < 0.2){
            return true;
        }
        return false;
    }

}
