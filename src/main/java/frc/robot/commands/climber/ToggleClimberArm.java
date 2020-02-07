package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class ToggleClimberArm extends InstantCommand {
	public ToggleClimberArm(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::toggleClimberArm, climberSubsystem);
	}
}
