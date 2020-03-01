/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_panel;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanelSpinFour extends CommandBase {
	private final ControlPanelSubsystem controlPanelSubsystem;
	int colorTracker = 0;
	int currentlyRed;
	private final Color Red = ColorMatch.makeColor(Constants.ControlPanel.red[0] , Constants.ControlPanel.red[1] , Constants.ControlPanel.red[2]);

	public ControlPanelSpinFour(ControlPanelSubsystem controlPanelSubsystem) {
		this.controlPanelSubsystem = controlPanelSubsystem;
		this.colorTracker = 0;

		addRequirements(controlPanelSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		colorTracker = 0;
		 if (controlPanelSubsystem.getCurrentColor() == Red){
			 currentlyRed = 1;
		 } else {
			 currentlyRed = 0;
		 }
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		controlPanelSubsystem.controlPanelManual(1.0);
		if (controlPanelSubsystem.getCurrentColor() == Red) {
			if (currentlyRed == 0) {
				colorTracker += 1;
				currentlyRed = 1;
			}
		} else {
			if (currentlyRed == 1) {
				currentlyRed = 0;
			}

		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return colorTracker == 8;
	}
}
