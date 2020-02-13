package frc.robot.commands.shooter_feeder;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederJoystick extends MotorJoystickControl {
	
	private final ShooterFeederSubsystem shooterFeederSubsystem;

	public ShooterFeederJoystick(ShooterFeederSubsystem shooterFeederSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.shooterFeederSubsystem = shooterFeederSubsystem;
		addRequirements(shooterFeederSubsystem);
	}

    public ShooterFeederJoystick(ShooterFeederSubsystem shooterFeederSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.shooterFeederSubsystem = shooterFeederSubsystem;
		addRequirements(shooterFeederSubsystem);
	}

	@Override
	public void execute() {
		shooterFeederSubsystem.spin(getSpeed());
	}

	@Override
	public void end(boolean interrupted) {
		shooterFeederSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
