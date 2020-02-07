package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ConveyorSubsystem;

public class StopConveyorRollers extends InstantCommand {
	public StopConveyorRollers(ConveyorSubsystem conveyorSubsystem) {
		super(conveyorSubsystem::stop, conveyorSubsystem);
	}
}