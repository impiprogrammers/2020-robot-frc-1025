package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class ToggleClimberLock extends InstantCommand {
	public ToggleClimberLock(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::toggleClimberLock, climberSubsystem);
	}
}
