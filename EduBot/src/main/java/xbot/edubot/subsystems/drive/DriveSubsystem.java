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
        
        /*if (sidePower==0){
            frontLeft.simpleSet(straightPower);
            frontRight.simpleSet(straightPower);
            rearLeft.simpleSet(straightPower);
            rearRight.simpleSet(straightPower);
        } else if(straightPower == 0 && sidePower > 0){
            frontLeft.simpleSet(sidePower);
            frontRight.simpleSet(sidePower * -1);
            rearLeft.simpleSet(sidePower);
            rearRight.simpleSet(sidePower * -1);
        } else if(straightPower == 0 && sidePower < 0){
            frontLeft.simpleSet(sidePower * -1);
            frontRight.simpleSet(sidePower);
            rearLeft.simpleSet(sidePower * -1);
            rearRight.simpleSet(sidePower);
        } else if(straightPower > 0 && sidePower > 0){
            frontLeft.simpleSet(sidePower + straightPower);
            frontRight.simpleSet(straightPower);
            rearLeft.simpleSet(sidePower + straightPower);
            rearRight.simpleSet(straightPower);
        } else if(straightPower > 0 && sidePower < 0){
            frontLeft.simpleSet(straightPower);
            frontRight.simpleSet(sidePower + straightPower);
            rearLeft.simpleSet(straightPower);
            rearRight.simpleSet(sidePower + straightPower);
        }  else if(straightPower < 0 && sidePower > 0){
            frontLeft.simpleSet(-1 * (sidePower + straightPower));
            frontRight.simpleSet(-1 * (straightPower));
            rearLeft.simpleSet(-1 * (sidePower + straightPower));
            rearRight.simpleSet(-1 * (straightPower));
        } else if(straightPower < 0 && sidePower < 0){
            frontLeft.simpleSet(-1 * (straightPower));
            frontRight.simpleSet(-1 * (sidePower + straightPower));
            rearLeft.simpleSet(-1 * (straightPower));
            rearRight.simpleSet(-1 * (sidePower + straightPower));
        } else {
            frontLeft.simpleSet(0);
            frontRight.simpleSet(0);
            rearLeft.simpleSet(0);
            rearRight.simpleSet(0);
        } */
        double L = sidePower + straightPower;
        double R = (-1 * straightPower) + straightPower;
        if(straightPower < 0 && sidePower > 0){
            frontLeft.simpleSet(-1 * (sidePower + straightPower));
            frontRight.simpleSet(-1 * (straightPower));
            rearLeft.simpleSet(-1 * (sidePower + straightPower));
            rearRight.simpleSet(-1 * (straightPower));
        } else if(straightPower < 0 && sidePower < 0){
            frontLeft.simpleSet(-1 * (straightPower));
            frontRight.simpleSet(-1 * (sidePower + straightPower));
            rearLeft.simpleSet(-1 * (straightPower));
            rearRight.simpleSet(-1 * (sidePower + straightPower));
        } else if(straightPower > 0 && sidePower > 0){
            frontLeft.simpleSet(sidePower + straightPower);
            frontRight.simpleSet(straightPower);
            rearLeft.simpleSet(sidePower + straightPower);
            rearRight.simpleSet(straightPower);
        } else if(straightPower > 0 && sidePower < 0){
            frontLeft.simpleSet(straightPower);
            frontRight.simpleSet(sidePower + straightPower);
            rearLeft.simpleSet(straightPower);
            rearRight.simpleSet(sidePower + straightPower);
        } else {
            frontLeft.simpleSet(L);
            frontRight.simpleSet(R);
            rearLeft.simpleSet(L);
            rearRight.simpleSet(R);
        }
        }

    }
}
