package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterStop extends InstantCommand {

	private final ShooterSubsystem shooterSubsystem;

	public ShooterStop(ShooterSubsystem shooterSubsystem) {
		addRequirements(shooterSubsystem);
		this.shooterSubsystem = shooterSubsystem;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		shooterSubsystem.stop();
	}
}
