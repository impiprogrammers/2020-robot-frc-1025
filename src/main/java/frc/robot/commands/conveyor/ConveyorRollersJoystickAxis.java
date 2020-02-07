package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;
import java.util.function.DoubleSupplier;
import frc.robot.ImpiLib2020;

public class ConveyorRollersJoystickAxis extends CommandBase {

	private final ConveyorSubsystem conveyorSubsystem;
	private final DoubleSupplier joystickAxis;

	public ConveyorRollersJoystickAxis(ConveyorSubsystem conveyorSubsystem, DoubleSupplier joystickAxis) {
		this.conveyorSubsystem = conveyorSubsystem;
		this.joystickAxis = joystickAxis;
		addRequirements(conveyorSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		double value = ImpiLib2020.clampedDeadzone(joystickAxis.getAsDouble(), 0.05, -1., 1.);
		conveyorSubsystem.spin(value);
	}

	@Override
	public void end(boolean interrupted) {
		conveyorSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
