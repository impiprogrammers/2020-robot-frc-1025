package frc.robot.commands.auto.paths;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.auto.AutoIntakeOff;
import frc.robot.commands.auto.AutoIntakeOn;
import frc.robot.commands.chassis.ChassisDriveDistance;
import frc.robot.commands.conveyor.ConveyorSetAuto;
import frc.robot.commands.intake.IntakeRollersSetAuto;
import frc.robot.commands.shooter.ShooterSetAuto;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter_feeder.ShooterFeederSetAuto;
import frc.robot.commands.turret.TurretSpinToAngle;
import frc.robot.commands.turret.TurretTrackTarget;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class AutoTrench extends SequentialCommandGroup {
	public AutoTrench(ChassisSubsystem chassisSubsystem, IntakeSubsystem intakeSubsystem,
			ConveyorSubsystem conveyorSubsystem, ShooterFeederSubsystem shooterFeederSubsystem,
			ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
		super(
			/*
			 * new AutoFollowPath(chassisSubsystem, "output/meters/left8-1.wpilib.json"),
			 * new AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem),
			 * new IntakeRollersSetAuto(intakeSubsystem, 1), new
			 * AutoFollowPath(chassisSubsystem, "output/meters/left8-2.wpilib.json"), new
			 * WaitCommand(Constants.Intake.AUTO_MIN_INTAKE_DURATION), new
			 * IntakeRollersSetAuto(intakeSubsystem, 0), new
			 * AutoFollowPath(chassisSubsystem, "output/meters/left8-3.wpilib.json"), new
			 * AutoShoot(conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem)
			 */
		
			// Part 1
			new ConveyorSetAuto(conveyorSubsystem, 0),
			new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
			new AutoIntakeOn(intakeSubsystem),
			new ShooterSetAuto(shooterSubsystem, turretSubsystem.calcRPM()),
			new TurretSpinToAngle(turretSubsystem, 70),
			new ChassisDriveDistance(chassisSubsystem, 2.7432, 0.75), // 108 inches
			new ParallelRaceGroup(
				new TurretTrackTarget(turretSubsystem),
				new SequentialCommandGroup(
					new WaitCommand(0.5),
					///////////////new TurretTurnToTarget(turretSubsystem),
					new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
					new ConveyorSetAuto(conveyorSubsystem, 0.7),
					new WaitCommand(3.5),
					// new ShooterStop(shooterSubsystem)
                    new ShooterFeederSetAuto(shooterFeederSubsystem, 0),

                    // Part 2
			        new ConveyorSetAuto(conveyorSubsystem, 1),
		        	// new TurretSetManualMode(turretSubsystem, true),
	     		    new ShooterSetAuto(shooterSubsystem, turretSubsystem.calcRPM()),
		        	// new TurretSetManualMode(turretSubsystem, true),
		            // new TurretSpinToAngle(turretSubsystem, 70),
                    new ChassisDriveDistance(chassisSubsystem, 1.5, .55), // 72 inches
                    new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
                    new WaitCommand(3)
				)

            ),
            new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
            new ConveyorSetAuto(conveyorSubsystem, 0),
            new ChassisDriveDistance(chassisSubsystem, 1.3, .7),
			// new AutoIntakeOff(intakeSubsystem),
            // new TurretSetManualMode(turretSubsystem, false),
            new ConveyorSetAuto(conveyorSubsystem, 1),
            new ShooterSetAuto(shooterSubsystem, turretSubsystem.calcRPM()),
            new ChassisDriveDistance(chassisSubsystem, 2.8, -0.7),
            new WaitCommand(0.2),
			new ParallelRaceGroup(
				new TurretTrackTarget(turretSubsystem),
				new SequentialCommandGroup(
					// new TurretTurnToTarget(turretSubsystem),
					// new IntakeRollersSetAuto(intakeSubsystem, 0.6),
					new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
					// new ConveyorSetAuto(conveyorSubsystem, 0.8), 
					new WaitCommand(3),
					new ConveyorSetAuto(conveyorSubsystem, 0),
					new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
					new ShooterStop(shooterSubsystem), new AutoIntakeOff(intakeSubsystem)
				)
			)
		);
	}
}