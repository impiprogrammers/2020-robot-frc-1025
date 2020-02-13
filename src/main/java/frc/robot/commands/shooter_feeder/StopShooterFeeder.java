package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class StopShooterFeeder extends InstantCommand {
	public StopShooterFeeder(ShooterFeederSubsystem shooterFeederSubsystem) {
		super(shooterFeederSubsystem::stop, shooterFeederSubsystem);
	}
}