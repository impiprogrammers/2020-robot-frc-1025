/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis.auto;

import java.io.IOException;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ChassisSubsystem;

public class AutoFollowPath extends RamseteCommand {

    static ChassisSubsystem chassisSubsystem = RobotContainer.chassisSubsystem;

	public AutoFollowPath(String trajectoryJSON) throws IOException {
        super(
            TrajectoryUtil.fromPathweaverJson(Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON)),
            chassisSubsystem::getPose,
            new RamseteController(Constants.CHASSIS_AUTO_RAMSETE_B, Constants.CHASSIS_AUTO_RAMSETE_ZETA),
            new SimpleMotorFeedforward(Constants.CHASSIS_AUTO_FFS, Constants.CHASSIS_AUTO_FFV, Constants.CHASSIS_AUTO_FFA),
            chassisSubsystem.driveKinematics,
            chassisSubsystem::getWheelSpeeds,
            new PIDController(Constants.CHASSIS_AUTO_P, 0, 0),
            new PIDController(Constants.CHASSIS_AUTO_P, 0, 0),
            chassisSubsystem::voltageTankDrive,
            chassisSubsystem
		);
		
		if (!Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON).toFile().isFile()) {
			throw new IOException();
		}
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
        chassisSubsystem.voltageTankDrive(0, 0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}