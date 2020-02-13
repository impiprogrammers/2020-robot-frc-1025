package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class SpinShooterFeeder extends CommandBase {

	ShooterFeederSubsystem shooterFeederSubsystem;
	double speed;

	public SpinShooterFeeder(ShooterFeederSubsystem shooterFeederSubsystem, double speed) {
		this.shooterFeederSubsystem = shooterFeederSubsystem;
		this.speed = speed;
		addRequirements(shooterFeederSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		shooterFeederSubsystem.spin(speed);
	}

	@Override
	public void end(boolean interrupted) {
		shooterFeederSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}