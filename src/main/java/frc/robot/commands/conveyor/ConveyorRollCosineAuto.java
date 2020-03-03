/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorRollCosineAuto extends CommandBase {

	private final ConveyorSubsystem conveyorSubsystem;
	private final double speed;
	private final double intercept;
	private final double period;

	private double iteration = 0;

	public ConveyorRollCosineAuto(ConveyorSubsystem conveyorSubsystem, double speed, double intercept, double period) {
		this.conveyorSubsystem = conveyorSubsystem;
		this.speed = speed;
		this.intercept = intercept;
		this.period = period;
		addRequirements(conveyorSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		iteration++;
		conveyorSubsystem.conveyorRoll(speed * Math.cos(iteration / period) + intercept);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
