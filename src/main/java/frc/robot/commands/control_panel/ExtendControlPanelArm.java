package frc.robot.commands.control_panel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ExtendControlPanelArm extends InstantCommand {
	public ExtendControlPanelArm(ControlPanelSubsystem controlPanelSubsystem) {
		super(controlPanelSubsystem::extendControlPanelArm, controlPanelSubsystem);
	}
}
