package xbot.edubot.rotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xbot.edubot.BaseDriveTest;
import xbot.edubot.subsystems.drive.commands.DriveToOrientationCommand;

public class TurnLeft180test extends BaseDriveTest{
    @Test
    public void testNorm240(){
        double result = DriveToOrientationCommand.normalizeAngle(240);
        assertEquals(-120, result,0.001);
    }
    @Test
    public void testNormNeg240(){
        double result = DriveToOrientationCommand.normalizeAngle(-240);
        assertEquals(120, result,0.001);
    }
    @Test
    public void testTurningLeft(){
        boolean result = DriveToOrientationCommand.shouldTurnleft(0, 90);
        assertTrue("Expected robot to turn left", result);
        result = DriveToOrientationCommand.shouldTurnleft(-179, 0);
        assertTrue("-179 to 0", result);
        result = DriveToOrientationCommand.shouldTurnleft(90,180);
        assertTrue("90 to 180", result);
        result = DriveToOrientationCommand.shouldTurnleft(145, -154);
        assertTrue("145 to -154", result);
    }
    @Test
    public void testTurningRight(){
        boolean result = DriveToOrientationCommand.shouldTurnleft(90, 0);
        assertFalse("90 to 0", result);
        result = DriveToOrientationCommand.shouldTurnleft(0, -179);
        assertFalse("0 to -179", result);
        result = DriveToOrientationCommand.shouldTurnleft(180, 90);
        assertFalse("180 to 90", result);
        result = DriveToOrientationCommand.shouldTurnleft(-154, 145);
        assertFalse("-154 to 145", result);
    }
}