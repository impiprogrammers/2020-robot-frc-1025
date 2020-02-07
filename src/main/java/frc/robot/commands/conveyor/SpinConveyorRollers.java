package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;

public class SpinConveyorRollers extends CommandBase {

	ConveyorSubsystem conveyorSubsystem;
	double speed;

	public SpinConveyorRollers(ConveyorSubsystem conveyorSubsystem, double speed) {
		this.conveyorSubsystem = conveyorSubsystem;
		this.speed = speed;
		addRequirements(conveyorSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		conveyorSubsystem.spin(speed);
	}

	@Override
	public void end(boolean interrupted) {
		conveyorSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}