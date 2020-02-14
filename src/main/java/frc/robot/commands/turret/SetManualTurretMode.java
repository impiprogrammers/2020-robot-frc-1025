package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;;

public class SetManualTurretMode extends InstantCommand {
	public SetManualTurretMode(TurretSubsystem turretSubsystem) {
		super(turretSubsystem::setManualMode, turretSubsystem);
	}
}