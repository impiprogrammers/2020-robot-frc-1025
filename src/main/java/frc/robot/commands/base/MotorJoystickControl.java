package frc.robot.commands.base;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import frc.robot.Constants;
import frc.robot.ImpiLib2020;

public class MotorJoystickControl extends CommandBase {
	
	private DoubleSupplier leftTrigger = null;
	private DoubleSupplier rightTrigger = null;
	private DoubleSupplier joystickAxis = null;

	public MotorJoystickControl(DoubleSupplier joystickAxis) {
		this.joystickAxis = joystickAxis;
	}
	
	public MotorJoystickControl(DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
		this.leftTrigger = leftTrigger;
		this.rightTrigger = rightTrigger;
	}

	public double getSpeed() {
		double speed = 0.;
		if (joystickAxis != null) {
			speed = joystickAxis.getAsDouble();
		} else {
			speed = rightTrigger.getAsDouble() - leftTrigger.getAsDouble();
		}
        speed = ImpiLib2020.clampedDeadzone(speed, Constants.OI.JOYSTICK_DEADZONE, -1., 1.);
        return speed;
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
