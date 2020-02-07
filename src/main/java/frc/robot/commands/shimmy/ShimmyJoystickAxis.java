package frc.robot.commands.shimmy;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.ShimmySubsystem;
import frc.robot.ImpiLib2020;

public class ShimmyJoystickAxis extends CommandBase {
	
	private final ShimmySubsystem shimmySubsystem;
	private final DoubleSupplier joystickAxis;

	public ShimmyJoystickAxis(ShimmySubsystem shimmySubsystem, DoubleSupplier joystickAxis) {
		this.shimmySubsystem = shimmySubsystem;
		this.joystickAxis = joystickAxis;
		addRequirements(shimmySubsystem);
	}

	@Override
	public void execute() {
		double value = ImpiLib2020.clampedDeadzone(joystickAxis.getAsDouble(), 0.05, -1., 1.);
		shimmySubsystem.spin(value);
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
