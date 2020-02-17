package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.chassis.ResetOdometry;
import frc.robot.subsystems.ChassisSubsystem;

public class TestAutonomous extends SequentialCommandGroup {

    public TestAutonomous(ChassisSubsystem chassisSubsystem) {
        super(
            new ResetOdometry(chassisSubsystem, new Pose2d(new Translation2d(0., 0.), new Rotation2d(1., 0)))
        );
    }
}
