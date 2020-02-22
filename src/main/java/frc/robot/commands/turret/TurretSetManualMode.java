/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSetManualMode extends InstantCommand {

	private final TurretSubsystem turretSubsystem;
	private final boolean state;

	public TurretSetManualMode(TurretSubsystem turretSubsystem, boolean state) {
		addRequirements(turretSubsystem);
		this.turretSubsystem = turretSubsystem;
		this.state = state;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		turretSubsystem.setManualMode(state);
	}
}