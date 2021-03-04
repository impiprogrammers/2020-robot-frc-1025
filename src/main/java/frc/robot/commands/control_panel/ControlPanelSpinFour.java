/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_panel;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanelSpinFour extends CommandBase {
	private final ControlPanelSubsystem controlPanelSubsystem;
	int colorTracker = 0;
	int currentlyRed;
	private Color detectedColor;
    private ColorMatchResult match;

	public ControlPanelSpinFour(ControlPanelSubsystem controlPanelSubsystem) {
		this.controlPanelSubsystem = controlPanelSubsystem;

		addRequirements(controlPanelSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		controlPanelSubsystem.controlPanelSpin(1.0);
		detectedColor = controlPanelSubsystem.getColorSensor().getColor();
        match = controlPanelSubsystem.getColorMatch().matchClosestColor(detectedColor);
		colorTracker = 0;
		 if (controlPanelSubsystem.getCurrentColorString(match.color) == "red") {
			 currentlyRed = 1;
		 } else {
			 currentlyRed = 0;
		 }
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		detectedColor = controlPanelSubsystem.getColorSensor().getColor();
        match = controlPanelSubsystem.getColorMatch().matchClosestColor(detectedColor);
		// SmartDashboard.putString("SpinFour Color String", controlPanelSubsystem.getCurrentColorString(match.color));
		if (controlPanelSubsystem.getCurrentColorString(match.color) == "red") {
			if (currentlyRed == 0) {
				colorTracker += 1;
				currentlyRed = 1;
			}
		} else {
			if (currentlyRed == 1) {
				currentlyRed = 0;
			}

		}

		// SmartDashboard.putNumber("Color Sensor Currently Red", currentlyRed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		controlPanelSubsystem.controlPanelStop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return colorTracker == 8;
	}
}
