/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends SubsystemBase {

	// Motor Controller
	private CANSparkMax turretMotor = new CANSparkMax(Constants.CAN.TURRET_MOTOR_PORT, MotorType.kBrushless);

	// PID Controller
	private CANPIDController turretPID = turretMotor.getPIDController();

	// Encoder
	private CANEncoder turretEncoder = turretMotor.getEncoder();

	// Booleans
	private boolean manualMode = true;

	// Network Tables
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	NetworkTableEntry tv = table.getEntry("tv");

	// read values periodically
	private double x = tx.getDouble(0);
	private double y = ty.getDouble(0);
	private double area = ta.getDouble(0);
	private double v = tv.getDouble(0);

	public TurretSubsystem() {
		turretMotor.setIdleMode(IdleMode.kBrake);

		turretMotor.setSmartCurrentLimit(20);

		turretEncoder.setPosition(0);
		

		turretPID.setP(0.01);
		turretPID.setI(0.000);
		turretPID.setD(0);
		turretPID.setFF(0);
	}

	@Override
	public void periodic() {
		updateLimelightTracking();
	}

	public void updateLimelightTracking() {
		x = tx.getDouble(0);
		y = ty.getDouble(0);
		area = ta.getDouble(0);
		v = tv.getDouble(0);
	}

	public boolean isTargetFound() {
		return ((x > 0.01 || x < -0.01) && y >= Constants.Turret.MIN_TRACKING_HEIGHT);
	}

	public void turretSpin(double speed) {
		int targetVisibility = 0;
		double kp = 0.01;
		double min_speed = 0.05;
		double negativeErrorValue = -x;
		double steeringAdjustment = 0.0;
		SmartDashboard.putBoolean("tv", tv.getBoolean(false));
		SmartDashboard.putNumber("tx", tx.getDouble(0));
		SmartDashboard.putNumber("Negative Error Value", negativeErrorValue);
		SmartDashboard.putNumber("Turret Encoder Position", turretEncoder.getPosition());
		SmartDashboard.putNumber("targetVisibility", targetVisibility);

		if (isModeAuto()) {
			if (negativeErrorValue > .5) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setTurretMotor(-steeringAdjustment);
			    //turretPID.setReference(steeringAdjustment, ControlType.kDutyCycle);
			} else if (negativeErrorValue < -.5) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setTurretMotor(-steeringAdjustment);
			} else {
				setTurretMotor(0);
			}
		} else {
			setTurretMotor(speed);
		}
	}

	public double getXOffset() {
		return x;
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

	public boolean turretAtRightSoftStop() {
		if(turretEncoder.getPosition() < -Constants.Turret.RIGHT_POSITION_LIMIT) {
			return true;
		}
	
		return false;
	}
	
	public boolean turretAtLeftSoftStop() {
		if(turretEncoder.getPosition() > Constants.Turret.LEFT_POSITION_LIMIT) {
			return true;
		}
	
		return false;
	}

	public void turnToPosiiton(double turretAngle) {
		turretPID.setReference(turretAngle, ControlType.kPosition);
	}

	public void setTurretMotor(double speed) {
		if (turretEncoder.getPosition() < -Constants.Turret.RIGHT_POSITION_LIMIT && speed > 0
				|| turretEncoder.getPosition() > Constants.Turret.LEFT_POSITION_LIMIT && speed < 0) {
			turretMotor.set(0);
		} else {
			turretMotor.set(-speed);
		}
	}

	public boolean isModeAuto() {
		return (!manualMode && isTargetFound());
	}
}