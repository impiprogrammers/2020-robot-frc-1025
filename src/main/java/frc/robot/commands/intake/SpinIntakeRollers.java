package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class SpinIntakeRollers extends CommandBase {

	IntakeSubsystem intakeSubsystem;
	double speed;

	public SpinIntakeRollers(IntakeSubsystem intakeSubsystem, double speed) {
		this.intakeSubsystem = intakeSubsystem;
		this.speed = speed;
		addRequirements(intakeSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		intakeSubsystem.spin(speed);
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