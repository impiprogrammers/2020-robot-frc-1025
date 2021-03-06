/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSpin extends CommandBase {

	private final TurretSubsystem turretSubsystem;
	private final DoubleSupplier speed;

	public TurretSpin(TurretSubsystem turretSubsystem, DoubleSupplier speed) {
		this.turretSubsystem = turretSubsystem;
		this.speed = speed;
		addRequirements(turretSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		turretSubsystem.turretSpin(ImpiLib2020.parseJoystick(speed));
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		turretSubsystem.turretSpin(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
