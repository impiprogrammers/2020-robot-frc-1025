package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendIntakeArm extends InstantCommand {
	public ExtendIntakeArm(IntakeSubsystem intakeSubsystem) {
		super(intakeSubsystem::extendIntakeArm, intakeSubsystem);
	}
}
