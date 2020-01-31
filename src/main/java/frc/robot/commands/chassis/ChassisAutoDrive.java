/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisAutoDrive extends PIDCommand {
	
	ChassisSubsystem chassisSubsystem = RobotContainer.chassisSubsystem;

	public ChassisAutoDrive() {
		super(
				// The controller that the command will use
				new PIDController(0, 0, 0),
				// This should return the measurement
				() -> 0,
				// This should return the setpoint (can also be a constant)
				() -> 0,
				// This uses the output
				output -> {
					// Use the output here
				});
		// Use addRequirements() here to declare subsystem dependencies.
		// Configure additional PID options by calling `getController` here.
	}

	@Override
	public void execute() {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
