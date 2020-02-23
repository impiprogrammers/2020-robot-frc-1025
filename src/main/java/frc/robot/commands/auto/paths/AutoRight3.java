package frc.robot.commands.auto.paths;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.AutoFollowPath;
import frc.robot.commands.auto.AutoShoot;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRight3 extends SequentialCommandGroup {

	public AutoRight3(ChassisSubsystem chassisSubsystem, ConveyorSubsystem conveyorSubsystem,
			ShooterFeederSubsystem shooterFeederSubsystem, ShooterSubsystem shooterSubsystem) throws IOException {
		super(
			new AutoFollowPath(chassisSubsystem, "output/right3-1.wpilib.json"),
			new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem)
		);
	}
}
