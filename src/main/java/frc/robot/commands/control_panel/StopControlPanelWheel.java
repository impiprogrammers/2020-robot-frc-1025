package frc.robot.commands.control_panel;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ControlPanelSubsystem;

public class StopControlPanelWheel extends InstantCommand {
	public StopControlPanelWheel(ControlPanelSubsystem controlPanelSubsystem) {
		super(controlPanelSubsystem::stop, controlPanelSubsystem);
	}
}
