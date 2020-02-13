package frc.robot.commands.shimmy;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShimmySubsystem;

public class SpinShimmy extends CommandBase {

	ShimmySubsystem shimmySubsystem;
	double speed;

	public SpinShimmy(ShimmySubsystem shimmySubsystem, double speed) {
		this.shimmySubsystem = shimmySubsystem;
		this.speed = speed;
		addRequirements(shimmySubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		shimmySubsystem.spin(speed);
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