package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class ToggleIntakeArm extends InstantCommand {
	public ToggleIntakeArm(IntakeSubsystem intakeSubsystem) {
		super(intakeSubsystem::toggleIntakeArm, intakeSubsystem);
	}
}
