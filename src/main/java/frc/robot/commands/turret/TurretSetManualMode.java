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

	TurretSubsystem turretSubsystem;
	boolean state;

	public TurretSetManualMode(TurretSubsystem subsystem, boolean state) {
		addRequirements(subsystem);
		turretSubsystem = subsystem;
		this.state = state;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		turretSubsystem.setManualMode(state);
	}
}
