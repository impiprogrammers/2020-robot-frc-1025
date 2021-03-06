package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSetAuto extends InstantCommand {

	private final ShooterSubsystem shooterSubsystem;
	private final double setpoint;

	public ShooterSetAuto(ShooterSubsystem shooterSubsystem, double setpoint) {
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
