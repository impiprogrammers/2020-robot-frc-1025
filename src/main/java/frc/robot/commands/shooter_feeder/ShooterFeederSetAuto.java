/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederSetAuto extends InstantCommand {

	private final ShooterFeederSubsystem shooterFeederSubsystem;
	private final double speed;

	public ShooterFeederSetAuto(ShooterFeederSubsystem shooterFeederSubsystem, double speed) {
		this.shooterFeederSubsystem = shooterFeederSubsystem;
		this.speed = speed;
		addRequirements(shooterFeederSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		shooterFeederSubsystem.spin(speed);
	}
}
