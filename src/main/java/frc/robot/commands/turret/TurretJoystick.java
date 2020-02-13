package frc.robot.commands.turret;

import java.util.function.DoubleSupplier;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.TurretSubsystem;

public class TurretJoystick extends MotorJoystickControl {
	
	private final TurretSubsystem turretSubsystem;

	public TurretJoystick(TurretSubsystem turretSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.turretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);
	}
	
	public TurretJoystick(TurretSubsystem turretSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.turretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);
	}

	@Override
	public void execute() {
		turretSubsystem.spin(getSpeed());
	}

	@Override
	public void end(boolean interrupted) {
		turretSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
