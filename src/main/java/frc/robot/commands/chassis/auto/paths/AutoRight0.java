package frc.robot.commands.chassis.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.chassis.auto.AutoFollowPath;
import frc.robot.subsystems.ChassisSubsystem;

public class AutoRight0 extends SequentialCommandGroup {

	public AutoRight0(ChassisSubsystem chassisSubsystem) throws IOException {
		super(
			new AutoFollowPath(chassisSubsystem, "output/left3-1.wpilib.json")
		);
	}
}
