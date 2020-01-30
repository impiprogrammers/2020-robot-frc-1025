package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorRoll extends CommandBase {

	ConveyorSubsystem conveyorSubsystem = RobotContainer.conveyorSubsystem;

	public ConveyorRoll() {
		addRequirements(conveyorSubsystem);
	}

	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		XboxController buttonsController = RobotContainer.buttonsController;
		conveyorSubsystem.conveyorRoll(Math.pow(ImpiLib2020.deadzone(buttonsController.getTriggerAxis(Hand.kRight), 0.05), 2));
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