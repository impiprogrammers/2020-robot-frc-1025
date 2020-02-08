/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederStop extends CommandBase {

	ShooterFeederSubsystem shooterFeederSubsystem = RobotContainer.shooterFeederSubsystem;

	public ShooterFeederStop() {
		addRequirements(shooterFeederSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		shooterFeederSubsystem.stop();
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}