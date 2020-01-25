package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.ImpiLib2020;

public class ConveyorRoll extends CommandBase {

	ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();

	public ConveyorRoll() {
		addRequirements(conveyorSubsystem);
	}

	@Override
	public void initialize() {
		XboxController buttonsController = new XboxController(Constants.XBOX_CONTROLLER_BUTTONS);
		conveyorSubsystem.conveyorRoll(0.05);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		XboxController buttonsController = new XboxController(Constants.XBOX_CONTROLLER_BUTTONS);
		conveyorSubsystem.conveyorRoll(0.05);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}