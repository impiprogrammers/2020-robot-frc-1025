package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.PIDShooterSubsystem;

public class ShooterToggle extends CommandBase {

	PIDShooterSubsystem shooterSubsystem = RobotContainer.shooterSubsystem;
	double setpoint;

	public ShooterToggle(double setpoint) {
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
		
		shooterSubsystem.toggle(setpoint);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return true;
	}
}
