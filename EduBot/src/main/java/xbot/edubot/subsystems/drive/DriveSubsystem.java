package xbot.edubot.subsystems.drive;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.edubot.MockHeadingSensor;

@Singleton
public class DriveSubsystem extends BaseSubsystem {

    public MockDistanceSensor distanceSensor;
    public MockHeadingSensor gyro;
    
    public boolean precision;
    public XCANTalon frontLeft;
    public XCANTalon frontRight;
    public XCANTalon rearLeft;
    public XCANTalon rearRight;
        
    @Inject
    public DriveSubsystem(CommonLibFactory factory) {
        // instantiate speed controllers and sensors here, save them as class members
        distanceSensor = new MockDistanceSensor();
        gyro = new MockHeadingSensor();
        
        precision = false;
        frontLeft = factory.createCANTalon(1);
        rearLeft = factory.createCANTalon(3);
        frontRight = factory.createCANTalon(2);
        rearRight = factory.createCANTalon(4);
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        // You'll need to take these power values and assign them to all of the motors. As
        // an example, here is some code that has the frontLeft motor to spin according to
        // the value of leftPower:
        if (precision){
            leftPower = leftPower/2;
            rightPower = rightPower/2;
        }
        frontLeft.simpleSet(leftPower);
        frontRight.simpleSet(rightPower);
        rearLeft.simpleSet(leftPower);
        rearRight.simpleSet(rightPower);
    }
    public void setPrecisionMode(){
        if (precision){
            precision = false;
        } else {
            precision = true;
        }
    }
    public void ArcadeDrive(double straightPower, double sidePower) {
        // You'll need to take these power values and assign them to all of the motors. As
        // an example, here is some code that has the frontLeft motor to spin according to
        // the value of leftPower:

        double leftPower = 0.0;
        double rightPower = 0.0;

        if(straightPower > 0.0 && sidePower != 0.0) {
            leftPower = straightPower + (0.5 * sidePower);
            rightPower = straightPower + (-0.5 * sidePower);
        } else if (straightPower < 0.0 && sidePower != 0.0){
            rightPower = straightPower + (0.5 * sidePower);
            leftPower = straightPower + (-0.5 * sidePower);
        } else {
            leftPower = straightPower + (sidePower);
            rightPower = straightPower - sidePower;
        }

        this.tankDrive(leftPower, rightPower);

    }
}
