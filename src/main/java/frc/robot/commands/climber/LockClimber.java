package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class LockClimber extends InstantCommand {
	public LockClimber(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::lockClimber, climberSubsystem);
	}
}
