package frc.robot.commands.intake;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeRollersJoystickAxis extends CommandBase {

	IntakeSubsystem intakeSubsystem;
	DoubleSupplier joystickAxis;

	public IntakeRollersJoystickAxis(IntakeSubsystem intakeSubsystem, DoubleSupplier joystickAxis) {
		this.intakeSubsystem = intakeSubsystem;
		this.joystickAxis = joystickAxis;
		addRequirements(intakeSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		double value = ImpiLib2020.clampedDeadzone(joystickAxis.getAsDouble(), 0.05, -1., 1.);
		intakeSubsystem.spin(value);
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
