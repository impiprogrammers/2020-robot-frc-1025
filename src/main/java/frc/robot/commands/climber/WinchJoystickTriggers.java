package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.ImpiLib2020;

public class WinchJoystickTriggers extends CommandBase {
	
	private final ClimberSubsystem climberSubsystem;
	private final DoubleSupplier leftTrigger;
	private final DoubleSupplier rightTrigger;

	public WinchJoystickTriggers(ClimberSubsystem climberSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		this.climberSubsystem = climberSubsystem;
		this.leftTrigger = leftTrigger;
		this.rightTrigger = rightTrigger;
		addRequirements(climberSubsystem);
	}

	@Override
	public void execute() {
		double value = rightTrigger.getAsDouble() - leftTrigger.getAsDouble();
		value = ImpiLib2020.clampedDeadzone(value, 0.05, -1., 1.);
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
