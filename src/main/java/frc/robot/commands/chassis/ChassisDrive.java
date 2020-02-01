/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class ChassisDrive extends CommandBase {
	
	ChassisSubsystem chassisSubsystem = RobotContainer.chassisSubsystem;
	XboxController driverController = RobotContainer.driverController;

	public ChassisDrive(ChassisSubsystem chassisSubsystem) {
		addRequirements(chassisSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double move = ImpiLib2020.parseJoystick(driverController.getY(Hand.kLeft), 0.05);
		double turn = ImpiLib2020.parseJoystick(driverController.getX(Hand.kRight), 0.05);
		chassisSubsystem.arcadeDrive(move, turn);
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