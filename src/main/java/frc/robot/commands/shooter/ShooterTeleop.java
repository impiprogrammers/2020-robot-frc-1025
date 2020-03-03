package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterTeleop extends CommandBase {

	private final ShooterSubsystem shooterSubsystem;

	public ShooterTeleop(ShooterSubsystem shooterSubsystem) {
		addRequirements(shooterSubsystem);
		this.shooterSubsystem = shooterSubsystem;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		shooterSubsystem.stop();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
