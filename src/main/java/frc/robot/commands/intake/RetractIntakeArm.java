package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class RetractIntakeArm extends InstantCommand {
	public RetractIntakeArm(IntakeSubsystem intakeSubsystem) {
		super(intakeSubsystem::retractIntakeArm, intakeSubsystem);
	}
}
