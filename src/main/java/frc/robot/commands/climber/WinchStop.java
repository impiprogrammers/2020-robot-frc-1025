package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class WinchStop extends InstantCommand {
	public WinchStop(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::stop, climberSubsystem);
	}
}
