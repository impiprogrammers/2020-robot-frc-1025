package frc.robot.commands.control_panel;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanelJoystickAxis extends CommandBase {

	ControlPanelSubsystem controlPanelSubsystem;
	DoubleSupplier joystickAxis;

	public ControlPanelJoystickAxis(ControlPanelSubsystem controlPanelSubsystem, DoubleSupplier joystickAxis) {
		this.controlPanelSubsystem = controlPanelSubsystem;
		this.joystickAxis = joystickAxis;
		addRequirements(controlPanelSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		double value = ImpiLib2020.clampedDeadzone(joystickAxis.getAsDouble(), 0.05, -1., 1.);
		controlPanelSubsystem.spin(value);
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
