/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSynchronousAutoMode extends CommandBase {
	
	private final TurretSubsystem turretSubsystem;

	public TurretSynchronousAutoMode(TurretSubsystem turretSubsystem) {
		this.turretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		turretSubsystem.setManualMode(false);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		turretSubsystem.turretSpin(Constants.Turret.AUTO_PERCENT_OUTPUT); //speed is only used if target is not found
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return (turretSubsystem.isTargetCentered());
	}
}
