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
	private final double duration;
	private final Timer timer = new Timer();

	public ConveyorRollCosineAuto(ConveyorSubsystem conveyorSubsystem, double intercept, double amplitude, double period, double duration) {
		this.conveyorSubsystem = conveyorSubsystem;
		this.intercept = intercept;
		this.amplitude = amplitude;
		this.period = period;
		this.duration = duration;
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
		return (timer.get() >= duration);
	}
}
