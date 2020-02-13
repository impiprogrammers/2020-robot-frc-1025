package frc.robot.commands.control_panel;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanelWheelJoystick extends MotorJoystickControl {

	private final ControlPanelSubsystem controlPanelSubsystem;

	public ControlPanelWheelJoystick(ControlPanelSubsystem controlPanelSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.controlPanelSubsystem = controlPanelSubsystem;
		addRequirements(controlPanelSubsystem);
	}

	public ControlPanelWheelJoystick(ControlPanelSubsystem controlPanelSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.controlPanelSubsystem = controlPanelSubsystem;
		addRequirements(controlPanelSubsystem);
	}

	@Override
	public void execute() {
		controlPanelSubsystem.spin(getSpeed());
	}

	@Override
	public void end(boolean interrupted) {
		controlPanelSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
