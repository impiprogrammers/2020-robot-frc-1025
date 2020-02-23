/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.conveyor.ConveyorSetAuto;
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter_feeder.ShooterFeederSetAuto;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShoot extends SequentialCommandGroup {

	public AutoShoot(ConveyorSubsystem conveyorSubsystem, ShooterFeederSubsystem shooterFeederSubsystem, ShooterSubsystem shooterSubsystem) {
		super(
			new ShooterShoot(shooterSubsystem, Constants.Shooter.AUTO_SETPOINT),
			new WaitCommand(Constants.Shooter.CHARGE_DURATION),
			new ShooterFeederSetAuto(shooterFeederSubsystem, Constants.ShooterFeeder.AUTO_SPEED),
			new ConveyorSetAuto(conveyorSubsystem, Constants.Conveyor.AUTO_SPEED),
			new WaitCommand(Constants.Shooter.AUTO_SHOOT_DURATION),
			new ShooterStop(shooterSubsystem)
		);
	}
}