/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberLockToggle extends InstantCommand {
	
	private final ClimberSubsystem climberSubsystem;

	public ClimberLockToggle(ClimberSubsystem climberSubsystem) {
		this.climberSubsystem = climberSubsystem;
		addRequirements(climberSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		climberSubsystem.lockToggle();
	}
}
