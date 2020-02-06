/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberShimmyMove extends CommandBase {
	
	private final ClimberSubsystem climberSubsystem;

	public ClimberShimmyMove(ClimberSubsystem climberSubsystem) {
		this.climberSubsystem = climberSubsystem;
		addRequirements(climberSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		XboxController driverController = RobotContainer.driverController;
		if (driverController.getPOV() == 90) {
			climberSubsystem.climberShimmyMove(0.5);
		} else if (driverController.getPOV() == 270) {
			climberSubsystem.climberShimmyMove(-0.5);
		}
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
