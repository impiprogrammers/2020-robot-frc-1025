/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shimmy;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShimmySubsystem;

public class ShimmyMove extends CommandBase {

  private final ShimmySubsystem shimmySubsystem;
  private final XboxController driverController;

  public ShimmyMove(ShimmySubsystem shimmySubsystem, XboxController driverController) {
    this.driverController = driverController;
    this.shimmySubsystem = shimmySubsystem;
    addRequirements(shimmySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
		if (driverController.getPOV() == 90) {
			shimmySubsystem.ShimmyMove(0.5);
		} else if (driverController.getPOV() == 270) {
			shimmySubsystem.ShimmyMove(0.5);
		} else {
			shimmySubsystem.ShimmyMove(0);
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
