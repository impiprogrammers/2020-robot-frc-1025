package frc.robot.commands.climber;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.ClimberSubsystem;

public class WinchJoystick extends MotorJoystickControl {
	
	private final ClimberSubsystem climberSubsystem;

	public WinchJoystick(ClimberSubsystem climberSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.climberSubsystem = climberSubsystem;
		addRequirements(climberSubsystem);
	}
	
	public WinchJoystick(ClimberSubsystem climberSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.climberSubsystem = climberSubsystem;
		addRequirements(climberSubsystem);
	}

	@Override
	public void execute() {
		climberSubsystem.spin(getSpeed());
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
