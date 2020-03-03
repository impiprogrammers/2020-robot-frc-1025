/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisDriveDistance extends CommandBase {

	private final ChassisSubsystem chassisSubsystem;
	private double distance;
	private double speed;

	public ChassisDriveDistance(ChassisSubsystem chassisSubsystem, double distance, double speed) {
		this.chassisSubsystem = chassisSubsystem;
		this.distance = distance;
		this.speed = speed;
		addRequirements(chassisSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		chassisSubsystem.resetEncoders();
		chassisSubsystem.resetGyro();
		chassisSubsystem.setBrakeMode();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		chassisSubsystem.arcadeDrive(-speed, -chassisSubsystem.getAngle() * 0.2);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		chassisSubsystem.arcadeDrive(0, 0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		SmartDashboard.putNumber("Auto Drive Position", Math.abs(chassisSubsystem.getPosition()));
		SmartDashboard.putNumber("Auto Drive Target Distance", Math.abs(distance));
		return (Math.abs(chassisSubsystem.getPosition()) >= Math.abs(distance));
	}
}
