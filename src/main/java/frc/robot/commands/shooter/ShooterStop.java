package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.PIDShooterSubsystem;

public class ShooterStop extends CommandBase {

	PIDShooterSubsystem shooterSubsystem = RobotContainer.shooterSubsystem;

	public ShooterStop() {
		this.shooterSubsystem = shooterSubsystem;
		addRequirements(shooterSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		shooterSubsystem.stop();
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return true;
	}
}
