package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimberArm extends InstantCommand {
	public RetractClimberArm(ClimberSubsystem climberSubsystem) {
		super(climberSubsystem::retractClimberArm, climberSubsystem);
	}
}
