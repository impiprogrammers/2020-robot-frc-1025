package frc.robot.commands.chassis.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.chassis.auto.AutoFollowPath;
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRight3 extends SequentialCommandGroup {

	public AutoRight3(ChassisSubsystem chassisSubsystem, ShooterSubsystem shooterSubsystem) throws IOException {
		super(
			new AutoFollowPath(chassisSubsystem, "output/right3-1.wpilib.json"),
			new ShooterShoot(shooterSubsystem, Constants.AUTO_SHOOT_SETPOINT),
			new WaitCommand(Constants.AUTO_SHOOT_DURATION),
			new ShooterStop(shooterSubsystem)
		);
	}
}
