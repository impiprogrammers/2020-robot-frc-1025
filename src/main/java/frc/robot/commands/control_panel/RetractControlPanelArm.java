package frc.robot.commands.control_panel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ControlPanelSubsystem;

public class RetractControlPanelArm extends InstantCommand {
	public RetractControlPanelArm(ControlPanelSubsystem controlPanelSubsystem) {
		super(controlPanelSubsystem::retractControlPanelArm, controlPanelSubsystem);
	}
}
