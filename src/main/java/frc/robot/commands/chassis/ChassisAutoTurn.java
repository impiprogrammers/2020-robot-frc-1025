/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisAutoTurn extends PIDCommand { // probably won't be used in favor of trajectory following/odometry

	static ChassisSubsystem chassisSubsystem = RobotContainer.chassisSubsystem;

	public ChassisAutoTurn() {
		super(
				// The controller that the command will use
				new PIDController(5e-5, 1e-6, 0),
				// This should return the measurement
				() -> chassisSubsystem.getAngle(),
				// This should return the setpoint (can also be a constant)
				SmartDashboard.getNumber("Target Angle", 0), // eventually make this command parameter
				// This uses the output
				output -> {
					chassisSubsystem.arcadeDrive(0, output);
				});
		this.chassisSubsystem = chassisSubsystem;
		addRequirements(chassisSubsystem);

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return getController().atSetpoint();
	}
}
