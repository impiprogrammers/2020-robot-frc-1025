package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterShoot extends CommandBase {

	private final ShooterSubsystem shooterSubsystem;
	private double speed;

	public ShooterShoot(ShooterSubsystem shooterSubsystem, double speed) {
		this.shooterSubsystem = shooterSubsystem;
		this.speed = speed;

		addRequirements(shooterSubsystem);
	}

	@Override
	public void initialize() {
		shooterSubsystem.shoot(speed);
	}

	@Override
	public void execute() {
	}

	@Override
	public void end(boolean interrupted) {
		shooterSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
