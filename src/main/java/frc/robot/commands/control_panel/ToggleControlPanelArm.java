package frc.robot.commands.control_panel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ToggleControlPanelArm extends InstantCommand {
	public ToggleControlPanelArm(ControlPanelSubsystem controlPanelSubsystem) {
		super(controlPanelSubsystem::toggleControlPanelArm, controlPanelSubsystem);
	}
}
