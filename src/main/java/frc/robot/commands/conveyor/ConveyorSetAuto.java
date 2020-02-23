/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorSetAuto extends InstantCommand {

	private final ConveyorSubsystem conveyorSubsystem;
	private final double speed;

	public ConveyorSetAuto(ConveyorSubsystem conveyorSubsystem, double speed) {
		this.conveyorSubsystem = conveyorSubsystem;
		this.speed = speed;
		addRequirements(conveyorSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		conveyorSubsystem.conveyorRoll(speed);
	}
}
