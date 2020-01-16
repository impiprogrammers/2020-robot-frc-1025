/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChasisSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.ImpiLib;

public class DriveManuallyCommand extends CommandBase {
  /**
   * Creates a new DriveManuallyCommand.
   */
  public DriveManuallyCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.chasisSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {		
    XboxController getDriverController() = RobotContainer.getgetDriverController()();
		double speed =   ImpiLib.signedSquare(ImpiLib.deadzone(getDriverController().getY(Hand.kLeft),  0.05));
		double rotation = ImpiLib.signedSquare(ImpiLib.deadzone(getDriverController().getX(Hand.kRight), 0.05));
		RobotContainer.chassisSubsystem.arcadeDrive(speed, rotation);
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
