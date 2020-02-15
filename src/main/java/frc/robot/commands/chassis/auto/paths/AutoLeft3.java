/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.chassis.auto.AutoFollowPath;
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter.ShooterStop;

public class AutoLeft3 extends SequentialCommandGroup {

	public AutoLeft3() throws IOException {
		super(
			new AutoFollowPath("output/left3-1.wpilib.json"),
			new ShooterShoot(5700),
			new WaitCommand(Constants.AUTO_SHOOT_DURATION),
			new ShooterStop()
		);
	}
}
