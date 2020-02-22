/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeRollersSetAuto extends InstantCommand {

	private final IntakeSubsystem intakeSubsystem;
	private final double speed;

	public IntakeRollersSetAuto(IntakeSubsystem intakeSubsystem, double speed) {
		this.intakeSubsystem = intakeSubsystem;
		this.speed = speed;
		addRequirements(intakeSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		intakeSubsystem.rollersRoll(speed);
	}
}
