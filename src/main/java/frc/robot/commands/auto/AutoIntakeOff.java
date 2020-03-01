/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.IntakeArmRetract;
import frc.robot.commands.intake.IntakeRollersSetAuto;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOff extends SequentialCommandGroup {

	public AutoIntakeOff(IntakeSubsystem intakeSubsystem) {
		super(
			new IntakeRollersSetAuto(intakeSubsystem, 0),
			new IntakeArmRetract(intakeSubsystem)
		);
	}
}
