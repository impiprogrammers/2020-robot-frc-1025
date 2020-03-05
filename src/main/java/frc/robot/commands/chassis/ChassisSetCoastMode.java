/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisSetCoastMode extends InstantCommand {

	private final ChassisSubsystem chassisSubsystem;

	public ChassisSetCoastMode(ChassisSubsystem chassisSubsystem) {
		this.chassisSubsystem = chassisSubsystem;
		addRequirements(chassisSubsystem);			
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		chassisSubsystem.setCoastMode();
	}
}