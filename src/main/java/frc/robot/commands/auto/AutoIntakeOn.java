/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.IntakeArmExtend;
import frc.robot.commands.intake.IntakeRollersSetAuto;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOn extends SequentialCommandGroup {

	// ask mitch if this class should be removed
	public AutoIntakeOn(IntakeSubsystem intakeSubsystem) {
		super(
			new IntakeArmExtend(intakeSubsystem),
			new IntakeRollersSetAuto(intakeSubsystem, 1)
		);
	}
}
