package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ShooterSubsystem extends PIDSubsystem {
	private final CANSparkMax shooterLeft = new CANSparkMax(Constants.CAN.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
	private final CANSparkMax shooterRight = new CANSparkMax(Constants.CAN.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
	private final CANEncoder shooterEncoder = shooterLeft.getEncoder();

	private boolean shooterEnabled = false;

	public ShooterSubsystem() {
		super(new PIDController(5e-5, 1e-6, 0));

		shooterRight.setInverted(true);
	}

	@Override
	public void useOutput(double output, double setpoint) {
		shooterLeft.set(output);
		shooterRight.set(output);
	}

	@Override
	public double getMeasurement() {
		return shooterEncoder.getVelocity();
	}

	public void shoot(double setpoint) {
		setSetpoint(setpoint);
		enable();
		shooterEnabled = true;
	}

	public void stop() {
		disable();
		shooterEnabled = false;
	}

	public void toggle(double setpoint) {
		if (shooterEnabled) {
			stop();
		} else {
			shoot(setpoint);
		}
	}
}