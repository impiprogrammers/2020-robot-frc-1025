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
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

	// Motor Controllers
	private final CANSparkMax shooterLeft = new CANSparkMax(Constants.CAN.SHOOTER_LEFT_PORT, MotorType.kBrushless);
	private final CANSparkMax shooterRight = new CANSparkMax(Constants.CAN.SHOOTER_RIGHT_PORT, MotorType.kBrushless);

	// PID Controllers
	private final CANPIDController pidLeft = shooterLeft.getPIDController();
	

	// Encoders
	private final CANEncoder shooterEncoder = shooterLeft.getEncoder();

	// Booleans
	private boolean shooterEnabled = false;
	private boolean shooterReady = false;
	private Timer timer = new Timer();


	public ShooterSubsystem(){
		shooterRight.follow(shooterLeft,true);

		pidLeft.setP(Constants.Shooter.SHOOT_P);
		pidLeft.setI(Constants.Shooter.SHOOT_I);
		pidLeft.setD(Constants.Shooter.SHOOT_D);
		pidLeft.setFF(Constants.Shooter.SHOOT_FF);
		pidLeft.setOutputRange(Constants.Shooter.SHOOT_OUTPUT_MIN, Constants.Shooter.SHOOT_OUTPUT_MAX);

		setCoastMode();
		setSmartCurrentLimit(Constants.Shooter.CURRENT_LIMIT);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("PID Output", shooterEncoder.getVelocity());
		SmartDashboard.putBoolean("Shooter Ready", shooterReady);
	}

	public double getShooterVelocity() {
		return shooterEncoder.getVelocity();
	}

	public void shoot(double setpoint) {
		pidLeft.setReference(-setpoint, ControlType.kVelocity);
		timer.reset();
		shooterEnabled = true;
		shooterReady = false;
		SmartDashboard.putNumber("Shooter Set RPM", setpoint);
	}

	public void stop() {
		pidLeft.setReference(0, ControlType.kCurrent);
		shooterEnabled = false;
		shooterReady = false;
	}

	public void toggle(double setpoint) {
		if (shooterEnabled) {
			stop();
		} else {
			shoot(setpoint);
		}
	}

	public boolean isShooterReady() {
		return shooterReady;
	}

    public boolean isShooterEnabled() {
		return shooterEnabled;
	}

	public void setBrakeMode() {
		shooterLeft.setIdleMode(IdleMode.kBrake);
		shooterRight.setIdleMode(IdleMode.kBrake);
	}

	public void setCoastMode() {
		shooterLeft.setIdleMode(IdleMode.kCoast);
		shooterRight.setIdleMode(IdleMode.kCoast);
	}
	public void setSmartCurrentLimit(int currentLimit) {
		shooterLeft.setSmartCurrentLimit(currentLimit);
		shooterRight.setSmartCurrentLimit(currentLimit);
	}

	public boolean atSetpoint() {
		return (Math.abs(5700 - shooterEncoder.getVelocity()) <= 250);
	} 

}