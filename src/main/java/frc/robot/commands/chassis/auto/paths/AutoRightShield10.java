package frc.robot.commands.chassis.auto.paths;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.chassis.auto.AutoFollowPath;
import frc.robot.commands.intake.IntakeRollersSetAuto;
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter.ShooterStop;

public class AutoRightShield10 extends SequentialCommandGroup {

	public AutoRightShield10() throws IOException {
		super(
            new IntakeRollersSetAuto(1),
            new AutoFollowPath("output/right_shield10-1.wpilib.json"),
            new IntakeRollersSetAuto(0),
            new AutoFollowPath("output/right_shield10-2.wpilib.json"),
            new ShooterShoot(Constants.AUTO_SHOOT_SETPOINT),
            new WaitCommand(Constants.AUTO_SHOOT_DURATION),
            new ShooterStop(),
            new IntakeRollersSetAuto(1),
            new AutoFollowPath("output/right_shield10-3.wpilib.json"),
            new WaitCommand(Constants.AUTO_INTAKE_MIN_DURATION),
            new IntakeRollersSetAuto(0),
            new AutoFollowPath("output/right_shield10-3.wpilib.json"),
            new ShooterShoot(Constants.AUTO_SHOOT_SETPOINT),
            new WaitCommand(Constants.AUTO_SHOOT_DURATION),
            new ShooterStop()
		);
	}
}
