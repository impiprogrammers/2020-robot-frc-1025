/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ConveyorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.ImpiLib2020;

public class ConveyorRoll extends CommandBase {

	ConveyorSubsystem conveyorSubsystem = RobotContainer.conveyorSubsystem;
	XboxController buttonsController = RobotContainer.buttonsController;

	public ConveyorRoll() {
		addRequirements(conveyorSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double rollJoystickValue = RobotContainer.buttonsController.getY(Hand.kRight);
		double rollTriggerValue = buttonsController.getTriggerAxis(Hand.kRight);

		if (Math.abs(rollJoystickValue) > Math.abs(rollTriggerValue)) {
			conveyorSubsystem.conveyorRoll(ImpiLib2020.parseJoystick(rollJoystickValue));
		} else {
			conveyorSubsystem.conveyorRoll(ImpiLib2020.parseTrigger(rollTriggerValue));
		}
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
