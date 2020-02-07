package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.ImpiLib2020;

public class WinchJoystickAxis extends CommandBase {
	
	private final ClimberSubsystem climberSubsystem;
	private final DoubleSupplier joystickAxis;

	public WinchJoystickAxis(ClimberSubsystem climberSubsystem, DoubleSupplier joystickAxis) {
		this.climberSubsystem = climberSubsystem;
		this.joystickAxis = joystickAxis;
		addRequirements(climberSubsystem);
	}

	@Override
	public void execute() {
		double value = ImpiLib2020.clampedDeadzone(joystickAxis.getAsDouble(), 0.05, -1., 1.);
		climberSubsystem.winch(value);
	}

	@Override
	public void end(boolean interrupted) {
		climberSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
