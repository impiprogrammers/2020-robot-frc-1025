package frc.robot.commands.conveyor;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorRollersJoystick extends MotorJoystickControl {

	private final ConveyorSubsystem conveyorSubsystem;

	public ConveyorRollersJoystick(ConveyorSubsystem conveyorSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.conveyorSubsystem = conveyorSubsystem;
		addRequirements(conveyorSubsystem);
	}

	public ConveyorRollersJoystick(ConveyorSubsystem conveyorSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.conveyorSubsystem = conveyorSubsystem;
		addRequirements(conveyorSubsystem);
	}

	@Override
	public void execute() {
		conveyorSubsystem.spin(getSpeed());
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
