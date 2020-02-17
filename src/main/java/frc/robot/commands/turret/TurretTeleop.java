package frc.robot.commands.turret;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.Constants;
import frc.robot.commands.base.MotorJoystickControl;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class TurretTeleop extends MotorJoystickControl {

    private final TurretSubsystem turretSubsystem;
    private final ChassisSubsystem chassisSubsystem;
    private boolean lastManualMode = true;
 
	public TurretTeleop(TurretSubsystem turretSubsystem, ChassisSubsystem chassisSubsystem, DoubleSupplier joystickAxis) {
		super(joystickAxis);
        this.turretSubsystem = turretSubsystem;
        this.chassisSubsystem = chassisSubsystem;
		addRequirements(turretSubsystem);
	}
	
	public TurretTeleop(TurretSubsystem turretSubsystem, ChassisSubsystem chassisSubsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		super(leftTrigger, rightTrigger);
		this.turretSubsystem = turretSubsystem;
        this.chassisSubsystem = chassisSubsystem;
		addRequirements(turretSubsystem);
	}

    @Override
    public void initialize() {
        lastManualMode = true;
    }

    @Override
    public void execute() {
        // Check if the joystick axis has been moved
        if (getSpeed() > Constants.OI.JOYSTICK_DEADBAND) {
            turretSubsystem.setManualMode();
        }

        // Handle manual and auto mode
        if (turretSubsystem.isManualMode()) {
            turretSubsystem.spin(getSpeed());
        } else {
            // Rest the PID controller if auto mode is just starting
            if (lastManualMode) {
                turretSubsystem.resetController();
            }

            // Calculate the angle to the target
            Rotation2d angle = chassisSubsystem.getAngleToTarget();
            turretSubsystem.spinToAngle(angle.getDegrees());
        }

        lastManualMode = turretSubsystem.isManualMode();
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
