/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorRollCosineAuto extends CommandBase {

	private final ConveyorSubsystem conveyorSubsystem;
	private final double amplitude;
	private final double period;
	private final double intercept;
	private final Timer timer = new Timer();

	public ConveyorRollCosineAuto(ConveyorSubsystem conveyorSubsystem, double amplitude, double period, double intercept) {
		this.conveyorSubsystem = conveyorSubsystem;
		this.amplitude = amplitude;
		this.period = period;
		this.intercept = intercept;
		addRequirements(conveyorSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		conveyorSubsystem.conveyorRoll(amplitude * Math.cos(2 * Math.PI * timer.get() / period) + intercept);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		timer.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
