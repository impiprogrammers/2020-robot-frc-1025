package frc.robot.commands.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.auto.AutoIntakeOn;
import frc.robot.commands.chassis.ChassisDriveDistance;
import frc.robot.commands.conveyor.ConveyorSetAuto;
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter_feeder.ShooterFeederSetAuto;
import frc.robot.commands.turret.TurretSpinToAngle;
import frc.robot.commands.turret.TurretSynchronousAutoMode;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class AutoLeft8b extends SequentialCommandGroup {

	public AutoLeft8b(ChassisSubsystem chassisSubsystem, IntakeSubsystem intakeSubsystem,
			ConveyorSubsystem conveyorSubsystem, ShooterFeederSubsystem shooterFeederSubsystem,
			ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) throws IOException {
		super(
			/* new AutoFollowPath(chassisSubsystem, "output/meters/left8-1.wpilib.json"),
			new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem),
			new IntakeRollersSetAuto(intakeSubsystem, 1),
			new AutoFollowPath(chassisSubsystem, "output/meters/left8-2.wpilib.json"),
			new WaitCommand(Constants.Intake.AUTO_MIN_INTAKE_DURATION),
			new IntakeRollersSetAuto(intakeSubsystem, 0),
			new AutoFollowPath(chassisSubsystem, "output/meters/left8-3.wpilib.json"),
			new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem) */
			new InstantCommand(chassisSubsystem::setBrakeMode, chassisSubsystem), //todo: make unique class
			new AutoIntakeOn(intakeSubsystem),
			new ShooterShoot(shooterSubsystem, 4160),
			new TurretSpinToAngle(turretSubsystem, -65),
			new ChassisDriveDistance(chassisSubsystem, 2.7432, Constants.Chassis.AUTO_PERCENT_OUTPUT), // 108 inches
			new TurretSynchronousAutoMode(turretSubsystem),
			//new WaitCommand(20),
			new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
			new ConveyorSetAuto(conveyorSubsystem, -.4),
		//	new ChassisDriveDistance(chassisSubsystem, .4, Constants.Chassis.AUTO_PERCENT_OUTPUT), // 108 inches
			new WaitCommand(9)
		);
	}
}