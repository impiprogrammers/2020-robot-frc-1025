package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class ResetOdometry extends CommandBase {

    private final ChassisSubsystem chassisSubsystem;
    private final Pose2d position;

    public ResetOdometry(ChassisSubsystem chassisSubsystem, Pose2d position) {
        this.chassisSubsystem = chassisSubsystem;
        this.position = position;
    }

    @Override
	public void initialize() {
		chassisSubsystem.resetOdometry(position);
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
