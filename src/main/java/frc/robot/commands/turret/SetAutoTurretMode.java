package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretSubsystem;;

public class SetAutoTurretMode extends InstantCommand {
	public SetAutoTurretMode(TurretSubsystem turretSubsystem) {
		super(turretSubsystem::setAutoMode, turretSubsystem);
	}
}