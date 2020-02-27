/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSpinToAngle extends CommandBase {

	private final TurretSubsystem turretSubsystem;
	private double angle;

	public TurretSpinToAngle(TurretSubsystem turretSubsystem, double angle) {
		this.turretSubsystem = turretSubsystem;
		this.angle = angle;
		addRequirements(turretSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		turretSubsystem.turnToPosition(angle);
	}

	@Override
	public void end(boolean interrupted) {
		turretSubsystem.
	}
}
