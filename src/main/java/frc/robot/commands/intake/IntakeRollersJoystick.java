package frc.robot.commands.intake;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeRollersJoystick extends MotorJoystickControl {

	private final IntakeSubsystem intakeSubsystem;

	public IntakeRollersJoystick(IntakeSubsystem intakeSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.intakeSubsystem = intakeSubsystem;
		addRequirements(intakeSubsystem);
	}

	public IntakeRollersJoystick(IntakeSubsystem intakeSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.intakeSubsystem = intakeSubsystem;
		addRequirements(intakeSubsystem);
	}

	@Override
	public void execute() {
		intakeSubsystem.spin(getSpeed());
	}

	@Override
	public void end(boolean interrupted) {
		intakeSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
