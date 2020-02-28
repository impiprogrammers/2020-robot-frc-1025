/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

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
		turretPID.setI(0);
		turretPID.setD(0);
		turretPID.setFF(0);
	}

	@Override
	public void periodic() {
		updateLimelightTracking();
		SmartDashboard.putBoolean("tv", tv.getBoolean(false));
		SmartDashboard.putNumber("tx", tx.getDouble(0));
		SmartDashboard.putNumber("Negative Error Value", -x);
		SmartDashboard.putNumber("Turret Encoder Position", turretEncoder.getPosition());
	}

	public void updateLimelightTracking() {
		x = tx.getDouble(0);
		y = ty.getDouble(0);
		area = ta.getDouble(0);
		v = tv.getDouble(0);
	}

	public boolean isModeAuto() {
		return (!manualMode);
	}

	public boolean isTargetFound() {
		return (v == 1);
	}

	public boolean isTargetCentered() {
		return (x > -1 && x < 1 && y >= Constants.Turret.MIN_TRACKING_HEIGHT);
	}

	public void turretSpin(double speed) {
		double kp = 0.01;
		double min_speed = 0.05;
		double negativeErrorValue = -x; //todo: rename variable
		double steeringAdjustment = 0.0;

		if (isModeAuto() && isTargetFound()) {
			if (negativeErrorValue > .5) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setMotorTeleop(-steeringAdjustment);
				//turretPID.setReference(steeringAdjustment, ControlType.kDutyCycle);
			} else if (negativeErrorValue < -.5) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setMotorTeleop(-steeringAdjustment);
			} else {
				setMotorTeleop(0);
			}
		} else {
			setMotorTeleop(speed);
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

	public boolean canTurretSpin(double speed) {
		return (!(turretAtRightSoftStop() && speed > 0 || turretAtLeftSoftStop() && speed < 0)); // might change this later?
	}

	public void setMotorAuto(double turretAngle) {
		if (turretAngle > -Constants.Turret.RIGHT_POSITION_LIMIT && turretAngle < Constants.Turret.LEFT_POSITION_LIMIT) {
			turretPID.setReference(turretAngle, ControlType.kPosition);
		} else {
			turretPID.setReference(0, ControlType.kVoltage);
		}
	}

	public void setMotorTeleop(double speed) {
		if (canTurretSpin(speed))  {
			turretPID.setReference(-speed, ControlType.kVoltage);
		} else {
			turretPID.setReference(0, ControlType.kVoltage);
		}
	}
}