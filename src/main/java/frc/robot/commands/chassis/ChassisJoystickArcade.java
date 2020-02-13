package frc.robot.commands.chassis;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisJoystickArcade extends CommandBase {
	
	private final ChassisSubsystem chassisSubsystem;
	private final DoubleSupplier forwardAxis;
	private final DoubleSupplier turnAxis;

	public ChassisJoystickArcade(ChassisSubsystem chassisSubsystem, DoubleSupplier forwardAxis, DoubleSupplier turnAxis) {
		this.chassisSubsystem = chassisSubsystem;
		this.forwardAxis = forwardAxis;
		this.turnAxis = turnAxis;
		addRequirements(chassisSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {		
		double forward = ImpiLib2020.clampedDeadzone(forwardAxis.getAsDouble(), Constants.OI.JOYSTICK_DEADZONE, -1., 1.);
		double turn = ImpiLib2020.clampedDeadzone(turnAxis.getAsDouble(), Constants.OI.JOYSTICK_DEADZONE, -1., 1.);
		chassisSubsystem.arcadeDrive(forward, turn);
	}

	@Override
	public void end(boolean interrupted) {
		chassisSubsystem.stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}