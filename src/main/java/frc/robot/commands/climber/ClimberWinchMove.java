/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberWinchMove extends CommandBase {
	
	private final ClimberSubsystem climberSubsystem;
	private final DoubleSupplier leftSpeed;
	private final DoubleSupplier rightSpeed;

	public ClimberWinchMove(ClimberSubsystem climberSubsystem, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
		this.climberSubsystem = climberSubsystem;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		addRequirements(climberSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		climberSubsystem.winchMove(ImpiLib2020.parseJoystick(rightSpeed.getAsDouble() - leftSpeed.getAsDouble()));
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
