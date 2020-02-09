package frc.robot.commands.shooter;

import org.opencv.features2d.FlannBasedMatcher;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterShoot extends CommandBase {

	ShooterSubsystem shooterSubsystem = RobotContainer.shooterSubsystem;
	XboxController buttonsController = RobotContainer.buttonsController;
	double setpoint;

	public ShooterShoot(double setpoint) {
		addRequirements(shooterSubsystem);
		this.setpoint = setpoint;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		shooterSubsystem.shoot(setpoint);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
	   return false;
	}
}
