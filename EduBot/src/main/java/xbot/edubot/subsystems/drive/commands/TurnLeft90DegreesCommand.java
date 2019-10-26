package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
    
    DriveSubsystem drive;
    double target_pos;
    double error;
    double speed;
    double last_point;
    double Cdistance;
    double distance_away;

    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    
    @Override
    public void initialize() {
        this.target_pos = (drive.gyro.getYaw() + 90) % 360;
        if (Math.abs(target_pos) > 180) {
            this.target_pos = (this.target_pos % 180) - 180;
        }
        this.last_point = drive.gyro.getYaw(); 
    }

    @Override
    public void execute() {
        Cdistance = drive.gyro.getYaw();
        distance_away = Math.abs(target_pos - Cdistance);
        error = distance_away;
        speed = ((error * 0.09) - (Cdistance - last_point) * 0.551); 
        drive.tankDrive(-1 * speed, speed);
        last_point = Cdistance;
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(drive.gyro.getYaw() - target_pos) <= 0.2){
            return true;
        }
        return false;
    }
}
