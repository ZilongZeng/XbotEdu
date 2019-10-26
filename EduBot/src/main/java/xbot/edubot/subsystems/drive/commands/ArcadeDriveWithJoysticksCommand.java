package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    DriveSubsystem drive;
    OperatorInterface operate;
    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        drive = driveSubsystem;
        operate = oi;
        this.requires(drive);
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        double straightValue = operate.gamepad.getLeftVector().y;
        double sideValue = operate.gamepad.getLeftVector().x;
        drive.ArcadeDrive(straightValue, sideValue);
    }

}
