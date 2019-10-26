package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand{
    
    DriveSubsystem drive;
    double target_pos;
    double error;
    double speed;
    double last_point;
    double Cdistance;
    double distance_away;
    boolean TurnLeft;
    double T;
    double C; 
    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    public void setTargetHeading(double heading) {
        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.
        this.target_pos = heading;
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
        this.T = normalizeAngle(target_pos); 
        this.last_point = drive.gyro.getYaw();
        this.C = normalizeAngle(last_point);
        TurnLeft = shouldTurnleft(C, T);
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
        
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
        if (!TurnLeft){
            Cdistance = drive.gyro.getYaw();
            distance_away = Math.abs(target_pos - Cdistance);
            error = distance_away;
            speed = ((error * 0.09) + (Cdistance - last_point) * 0.618); 
            drive.tankDrive(speed,-1 * speed);
            last_point = Cdistance; 
        }  else {
            Cdistance = drive.gyro.getYaw();
            distance_away = Math.abs(target_pos - Cdistance);
            error = distance_away;
            speed = ((error * 0.09) - (Cdistance - last_point) * 0.618); 
            drive.tankDrive(-1 * speed, speed);
            last_point = Cdistance;
        }
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal, 
        // and you're moving fairly slowly (ideally stopped)
        if (Math.abs(drive.gyro.getYaw() - target_pos) <= 0.2){
            return true;
        }
        return false;
    }

    public static double normalizeAngle(double angle){
        if (angle > 180) {
            return ((angle % 180) - 180);
        } 
        if (angle < -180) {
            return 360 + angle;
        }
        return angle;
    }
    public static boolean shouldTurnleft(double start, double finish){
        if(start == 0 && finish == -180){
            return false;
        }
        if(((360 - start + finish) % 360) <= (((start - finish) + 360) % 360)){
            return true;
        } else {
            return false;
        }
    }
    public static double closestDistance(double angle1,double angle2){
        double distance1 = (360 - (angle1 + angle2)) % 360;
        double distance2 = ((angle1 - angle2) + 360) % 360;
        if (distance1 < distance2){
            return distance1;
        } else {
            return distance2;
        }   
    }
}
