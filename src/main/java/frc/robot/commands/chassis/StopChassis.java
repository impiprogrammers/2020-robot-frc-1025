package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ChassisSubsystem;

public class StopChassis extends InstantCommand {
    public StopChassis(ChassisSubsystem chassisSubsystem) {
		super(chassisSubsystem::stop, chassisSubsystem);
    }
}
