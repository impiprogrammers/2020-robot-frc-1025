package frc.robot.commands.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.auto.AutoFollowPath;
import frc.robot.commands.auto.AutoShoot;
import frc.robot.commands.intake.IntakeRollersSetAuto;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoCenter8 extends SequentialCommandGroup {

	public AutoCenter8(ChassisSubsystem chassisSubsystem, IntakeSubsystem intakeSubsystem,
			ConveyorSubsystem conveyorSubsystem, ShooterFeederSubsystem shooterFeederSubsystem,
			ShooterSubsystem shooterSubsystem) throws IOException {
		super(
			new AutoFollowPath(chassisSubsystem, "output/meters/center8-1.wpilib.json"),
			new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem),
			new IntakeRollersSetAuto(intakeSubsystem, 1),
			new AutoFollowPath(chassisSubsystem, "output/meters/center8-2.wpilib.json"),
			new WaitCommand(Constants.Intake.AUTO_MIN_INTAKE_DURATION),
			new IntakeRollersSetAuto(intakeSubsystem, 0),
			new AutoFollowPath(chassisSubsystem, "output/meters/center8-3.wpilib.json"),
			new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem)
		);
	}
}