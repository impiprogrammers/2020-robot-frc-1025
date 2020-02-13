package frc.robot.commands.shimmy;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShimmySubsystem;

public class StopShimmy extends InstantCommand {
	public StopShimmy(ShimmySubsystem shimmySubsystem) {
		super(shimmySubsystem::stop, shimmySubsystem);
	}
}