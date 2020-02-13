package frc.robot.commands.shimmy;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.ShimmySubsystem;

public class ShimmyJoystick extends MotorJoystickControl {
	
	private final ShimmySubsystem shimmySubsystem;

	public ShimmyJoystick(ShimmySubsystem shimmySubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.shimmySubsystem = shimmySubsystem;
		addRequirements(shimmySubsystem);
	}

	public ShimmyJoystick(ShimmySubsystem shimmySubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.shimmySubsystem = shimmySubsystem;
		addRequirements(shimmySubsystem);
	}

	@Override
	public void execute() {
		shimmySubsystem.spin(getSpeed());
	}

	@Override
	public void end(boolean interrupted) {
		shimmySubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
