package frc.robot.commands.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.AutoFollowPath;
import frc.robot.commands.auto.AutoShoot;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoLeft3 extends SequentialCommandGroup {

	public AutoLeft3(ChassisSubsystem chassisSubsystem, ConveyorSubsystem conveyorSubsystem,
			ShooterFeederSubsystem shooterFeederSubsystem, ShooterSubsystem shooterSubsystem) throws IOException {
		super(
			new AutoFollowPath(chassisSubsystem, "output/meters/left3-1.wpilib.json"),
			new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem)
		);
	}
}
