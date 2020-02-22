package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterShoot extends CommandBase {

	private final ShooterSubsystem shooterSubsystem;
	private final double setpoint;

	public ShooterShoot(ShooterSubsystem shooterSubsystem, double setpoint) {
		addRequirements(shooterSubsystem);
		this.shooterSubsystem = shooterSubsystem;
		this.setpoint = setpoint;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		shooterSubsystem.shoot(setpoint);
	}
}
