/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.ChassisSubsystem;
import java.util.function.DoubleSupplier;

public class ChassisDrive extends CommandBase {
	
	private final ChassisSubsystem chassisSubsystem;
	private final DoubleSupplier move;
	private final DoubleSupplier turn;

	public ChassisDrive(ChassisSubsystem chassisSubsystem, DoubleSupplier move, DoubleSupplier turn) {
		this.chassisSubsystem = chassisSubsystem;
		this.move = move;
		this.turn = turn;
		addRequirements(chassisSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		chassisSubsystem.setCoastMode();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		chassisSubsystem.arcadeDrive(ImpiLib2020.parseJoystick(move), ImpiLib2020.parseJoystick(turn));
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// chassisSubsystem.arcadeDrive(0, 0);
		chassisSubsystem.stopChassis();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}