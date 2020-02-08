/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSpin extends CommandBase {

	TurretSubsystem turretSubsystem = RobotContainer.turretSubsystem;

	public TurretSpin() {
		addRequirements(turretSubsystem);
		// Use addRequirements() here to declare subsystem dependencies.
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {

		XboxController buttonsController = RobotContainer.buttonsController;
		TurretSubsystem.TurretSpin(Math.pow(ImpiLib2020.deadzone(buttonsController.getX(Hand.kLeft), 0.05), 2));
		if(buttonsController.getXButton()) {
			TurretSubsystem.ToggleLimelightLock();
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
