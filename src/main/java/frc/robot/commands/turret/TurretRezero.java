/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class TurretRezero extends InstantCommand {

	private final TurretSubsystem turretSubsystem;

	public TurretRezero(TurretSubsystem turretSubsystem) {
		this.turretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		turretSubsystem.zeroTurret();
	}
}
