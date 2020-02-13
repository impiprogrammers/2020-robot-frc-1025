package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class StopShooter extends InstantCommand {
	public StopShooter(ShooterSubsystem shooterSubsystem) {
		super(shooterSubsystem::stop, shooterSubsystem);
	}
}
