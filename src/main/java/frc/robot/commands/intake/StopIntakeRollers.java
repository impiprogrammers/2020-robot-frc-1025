package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class StopIntakeRollers extends InstantCommand {
	public StopIntakeRollers(IntakeSubsystem intakeSubsystem) {
		super(intakeSubsystem::stop, intakeSubsystem);
	}
}