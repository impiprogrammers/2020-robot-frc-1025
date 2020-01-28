/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class PIDShooterSubsystem extends PIDSubsystem {

	// Motor Controllers
	private CANSparkMax shooterLeft = new CANSparkMax(Constants.SHOOTER_LEFT_PORT, MotorType.kBrushless);
	private CANSparkMax shooterRight = new CANSparkMax(Constants.SHOOTER_RIGHT_PORT, MotorType.kBrushless);

	// Encoders
	private CANEncoder shooterEncoder = shooterLeft.getEncoder();
	// private CANEncoder rightEncoder = shooterRight.getEncoder();

	// Booleans
	private boolean shooterEnabled = false;

	public PIDShooterSubsystem() {
		super(new PIDController(5e-5, 1e-6, 0));
	}

	@Override
	public void useOutput(double output, double setpoint) {
		shooterLeft.set(output);
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