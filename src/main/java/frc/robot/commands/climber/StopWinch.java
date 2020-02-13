package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class StopWinch extends InstantCommand {
	public StopWinch(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::stop, climberSubsystem);
	}
}
