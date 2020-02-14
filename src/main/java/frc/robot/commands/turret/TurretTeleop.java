package frc.robot.commands.turret;

import java.util.function.DoubleSupplier;
import frc.robot.Constants;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.TurretSubsystem;

public class TurretTeleop extends MotorJoystickControl {

    private final TurretSubsystem turretSubsystem;
 
	public TurretTeleop(TurretSubsystem turretSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
		this.turretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);
	}
	
	public TurretTeleop(TurretSubsystem turretSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.turretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);
	}

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Check if the joystick axis has been moved
        if (getSpeed() > Constants.OI.JOYSTICK_DEADZONE) {
            turretSubsystem.setManualMode();
        }

        // Handle manual and auto mode
        if (turretSubsystem.isManualMode()) {
            turretSubsystem.spin(getSpeed());
        } else {

        }
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
