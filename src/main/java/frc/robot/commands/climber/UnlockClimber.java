package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class UnlockClimber extends InstantCommand {
	public UnlockClimber(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::unlockClimber, climberSubsystem);
	}
}
