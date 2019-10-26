package xbot.edubot.rotation;

import org.junit.Test;

import xbot.edubot.rotation.RotationTestVisualizer.OrientationTest;
import xbot.edubot.rotation.RotationTestVisualizer.SelectableOrientationTest;
import xbot.edubot.subsystems.drive.commands.DriveToOrientationCommand;

public class GoToOrientationTest extends BaseOrientationEngineTest implements SelectableOrientationTest {

    @Test
    public void testGoToOrientation() {
        DriveToOrientationCommand command = injector.getInstance(DriveToOrientationCommand.class);
        double start = 0;
        double finish = -180;
        command.setTargetHeading(finish);

        setUpTestEnvironment(command, start, finish);
        runTestEnv();
    }

    @Override
    public void invokeOrientationTest(OrientationTest test) {
        switch (test) {
            case ROTATE_TO_ORIENTATION:
                this.testGoToOrientation();
                break;
            default:
                throw new RuntimeException("The requested orientation test is not available in this test class.");
        }
    }
}
