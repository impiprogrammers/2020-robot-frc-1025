/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberWinchMove extends CommandBase {
	
	ClimberSubsystem climberSubsystem = RobotContainer.climberSubsystem;
	XboxController driverController = RobotContainer.driverController;

	public ClimberWinchMove() {
		addRequirements(climberSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double leftTriggerAxis = ImpiLib2020.deadzone(driverController.getTriggerAxis(Hand.kLeft), 0.05);
		double rightTriggerAxis = ImpiLib2020.deadzone(driverController.getTriggerAxis(Hand.kRight), 0.05);
		if (rightTriggerAxis > 0) {
			climberSubsystem.winchMove(Math.pow(rightTriggerAxis, 2));
		} else if (leftTriggerAxis > 0) {
			climberSubsystem.winchMove(-Math.pow(rightTriggerAxis, 2));
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
