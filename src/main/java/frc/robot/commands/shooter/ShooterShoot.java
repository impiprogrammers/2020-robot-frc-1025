package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class ShooterShoot extends CommandBase {

	private final ShooterSubsystem shooterSubsystem;
	private final TurretSubsystem turretSubsystem;
	private final double setpoint;

	public ShooterShoot(ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem, double setpoint) {
		addRequirements(shooterSubsystem);
		this.shooterSubsystem = shooterSubsystem;
		this.turretSubsystem = turretSubsystem;
		this.setpoint = setpoint;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		shooterSubsystem.shoot(setpoint);
		if (turretSubsystem.isTargetFound()) {
			shooterSubsystem.shoot(shooterSubsystem.calcRPM(turretSubsystem.getArea()));
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		shooterSubsystem.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
