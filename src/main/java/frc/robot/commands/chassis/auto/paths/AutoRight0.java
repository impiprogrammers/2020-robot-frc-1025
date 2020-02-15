/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.chassis.auto.AutoFollowPath;

public class AutoRight0 extends SequentialCommandGroup {

	public AutoRight0() throws IOException {
		super(
			new AutoFollowPath("output/left3-1.wpilib.json")
		);
	}
}
