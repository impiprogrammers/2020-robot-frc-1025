package frc.robot.commands.chassis;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
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
		double forward = MathUtil.clamp(forwardAxis.getAsDouble(), -1., 1.);
		double turn = MathUtil.clamp(turnAxis.getAsDouble(), -1., 1.);
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