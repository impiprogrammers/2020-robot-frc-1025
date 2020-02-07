package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class ExtendClimberArm extends InstantCommand {
	public ExtendClimberArm(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::extendClimberArm, climberSubsystem);
	}
}
