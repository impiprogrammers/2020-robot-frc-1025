/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends SubsystemBase {

	// Motor Controller
	private CANSparkMax turretRotate = new CANSparkMax(Constants.TURRET_ROTATE_PORT, MotorType.kBrushless);

	// PID Controller
	private CANPIDController turretPID = turretRotate.getPIDController();

	// Encoder
	private CANEncoder turretEncoder = turretRotate.getEncoder();

	// Booleans
	public boolean manualMode = true;

	// Network Tables
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	NetworkTableEntry tv = table.getEntry("tv");

	// Subsystems
	ShooterSubsystem shooterSubsystem = RobotContainer.shooterSubsystem;

	// read values periodically
	private double x = tx.getDouble(0);
	private double y = ty.getDouble(0);
	private double area = ta.getDouble(0);
	private double v = tv.getDouble(0);

	public TurretSubsystem() {
		turretRotate.setIdleMode(IdleMode.kBrake);

		turretRotate.setSmartCurrentLimit(20);

		turretEncoder.setPosition(0);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void updateLimelightTracking() {
		x = tx.getDouble(0);
		y = ty.getDouble(0);
		area = ta.getDouble(0);
		v = tv.getDouble(0);

	}

	public boolean isTargetFound() {
		if (v == 1 && y >= Constants.TURRET_MIN_TRACKING_HEIGHT) {
			return true;
		}
		return false;
	}

	public void turretSpin(double speed) {
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tx = table.getEntry("tx");
		double errorValue = tx.getDouble(0);
		NetworkTableEntry tv = table.getEntry("tv");
		int targetVisibility = 0;
		if (errorValue > -0.01 && errorValue < 0.01) {
			targetVisibility = 0;
		} else {
			targetVisibility = 1;
		}
		double kp = 0.02;
		double min_speed = 0.05;
		double negativeErrorValue = -errorValue;
		double steeringAdjustment = 0.0;
		SmartDashboard.putBoolean("tv", tv.getBoolean(false));
		SmartDashboard.putNumber("tx", tx.getDouble(0));
		SmartDashboard.putNumber("Negative Error Value", negativeErrorValue);
		SmartDashboard.putNumber("Turret Encoder Position", turretEncoder.getPosition());
		SmartDashboard.putNumber("targetVisibility", targetVisibility);

		if (!manualMode && isTargetFound()) {
			if (negativeErrorValue > 2.0) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setTurretMotor(-steeringAdjustment);
			} else if (negativeErrorValue < 1.0) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setTurretMotor(steeringAdjustment);
			} else {
				setTurretMotor(0);
			}
		}
	}

	public void turretRezero() {
		turretEncoder.setPosition(0);
	}

	public void toggleManualMode() {
		if (manualMode) {
			manualMode = false;
		} else {
			manualMode = true;
		}
	}

	public void setManualMode(boolean state) {
		manualMode = state;
	}

	public boolean TurretAtRightSoftStop() {
		if(turretEncoder.getPosition() < -Constants.TURRET_RIGHT_LIMIT) {
			return true;
		}
	
		return false;
	}
	
	public boolean TurretAtLeftSoftStop() {
		if(turretEncoder.getPosition() > Constants.TURRET_LEFT_LIMIT) {
			return true;
		}
	
		return false;
	}

	public void setTurretMotor(double speed) {
		if (turretEncoder.getPosition() < -Constants.TURRET_RIGHT_LIMIT && speed > 0
				|| turretEncoder.getPosition() > Constants.TURRET_LEFT_LIMIT && speed < 0) {
			turretRotate.set(0);
		} else {
			turretRotate.set(-speed);
		}
	}
}