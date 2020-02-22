/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

	// Motor Controllers
	private CANSparkMax shooterLeft = new CANSparkMax(Constants.SHOOTER_LEFT_PORT, MotorType.kBrushless);
	private CANSparkMax shooterRight = new CANSparkMax(Constants.SHOOTER_RIGHT_PORT, MotorType.kBrushless);

	// PID Controllers
	private CANPIDController pidLeft = shooterLeft.getPIDController();
	

	// Encoders
	private CANEncoder shooterEncoder = shooterLeft.getEncoder();
	// private CANEncoder rightEncoder = shooterRight.getEncoder();

	// Booleans
	private boolean shooterEnabled = false;

	// PID Values
	private double p = 0.0001;
	private double i = 0.0000001;
	private double d = 0;
	private double ff = 0.000105;
	private final double min = -1;
	private final double max = 1;

	public ShooterSubsystem(){

		shooterLeft.setSmartCurrentLimit(40);
		shooterRight.setSmartCurrentLimit(40);

		shooterRight.follow(shooterLeft,true);

		pidLeft.setP(p);
		pidLeft.setI(i);
		pidLeft.setD(d);
		pidLeft.setFF(ff);
		pidLeft.setOutputRange(min, max);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("PID Output", shooterEncoder.getVelocity());
/*
		SmartDashboard.putNumber("P", p);
		SmartDashboard.putNumber("i", i);
		SmartDashboard.putNumber("d", d);
*/

		// p = SmartDashboard.getNumber("P", 0);
		// i = SmartDashboard.getNumber("i", 0);
		// d = SmartDashboard.getNumber("d", 0);
		// ff = SmartDashboard.getNumber("ff", 0);

		// pidLeft.setP(p);
		// pidLeft.setI(i);
		// pidLeft.setD(d);
		// pidLeft.setFF(ff);
	}

	public void shoot(double setpoint) {
		shooterEnabled = true;
		pidLeft.setReference(-setpoint, ControlType.kVelocity);
	//	pidRight.setReference(setpoint, ControlType.kVelocity);

	}

	public void stop() {
		shooterEnabled = false;
		pidLeft.setReference(0, ControlType.kCurrent);
	//	pidRight.setReference(0, ControlType.kCurrent);
	}

	public void toggle(double setpoint) {
		if (shooterEnabled) {
			stop();
		} else {
			shoot(setpoint);
		}
	}

	public boolean atSetpoint() {
		return (Math.abs(5700 - shooterEncoder.getVelocity()) <= 250); 
	}
}