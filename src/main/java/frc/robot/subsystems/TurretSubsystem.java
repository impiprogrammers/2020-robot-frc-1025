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
import com.revrobotics.CANSparkMax.SoftLimitDirection;
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
		turretMotor.restoreFactoryDefaults();
		turretMotor.setIdleMode(IdleMode.kBrake);
		turretMotor.setSmartCurrentLimit(Constants.Turret.CURRENT_LIMIT);

		turretMotor.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Turret.RIGHT_POSITION_LIMIT);
		turretMotor.setSoftLimit(SoftLimitDirection.kForward, Constants.Turret.LEFT_POSITION_LIMIT);
		turretMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
		turretMotor.enableSoftLimit(SoftLimitDirection.kForward, true);

		turretEncoder.setPosition(0);

		turretPID.setP(0.06);
		//turretPID.setI(0.00002675);
		//turretPID.setP(0.1);
		turretPID.setI(0);
		turretPID.setD(0.0);
		turretPID.setFF(0);
		
		table.getEntry("pipeline").setNumber(3);
	}

	@Override
	public void periodic() {
		updateLimelightTracking();
		SmartDashboard.putBoolean("tv", tv.getBoolean(false));
		SmartDashboard.putNumber("tx", tx.getDouble(0));
		SmartDashboard.putNumber("ta", ta.getDouble(0));
		SmartDashboard.putNumber("Negative Error Value", -x);
		SmartDashboard.putNumber("Turret Encoder Position", turretEncoder.getPosition());
		SmartDashboard.putBoolean("Is Target Centered", isTargetCentered());
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

	public boolean isModeManual() {
		return (manualMode);
	}

	public void setManualMode(boolean state) {
		manualMode = state;
	} 

	public void setManualMode() {
		manualMode = true;
	}

	public void setAutoMode() {
		manualMode = false;
	}

	public void toggleMode() {
		if(manualMode) {
			manualMode = false;
		} else {
			manualMode = true;
		}
	}

	public boolean isTargetFound() {
		return (v == 1);
	}

	public boolean isTargetCentered() {
		if (x > -Constants.Turret.TARGET_CENTER_RANGE && x < Constants.Turret.TARGET_CENTER_RANGE && y >= Constants.Turret.MIN_TRACKING_HEIGHT) {
			// if(x > -5 && x < 5){
			return true;
		} else {
			return false;
		}
	}

	public void rotateToTarget() {
		turretPID.setReference(turretEncoder.getPosition() - getXOffset(), ControlType.kPosition);
		
	}

	public void rotateToAngle(double encoderPosition) {
		turretPID.setReference(-encoderPosition, ControlType.kPosition);
	}

	public void turretSpin(double speed) {
		turretPID.setReference(-speed, ControlType.kDutyCycle);
	}

	public double getXOffset() {
		return x;
	}

	public double getArea() {
		return area;
	}

	public void zeroTurret() {
		turretEncoder.setPosition(0);
	}

	public void getTurretPosition() {
		turretEncoder.getPosition();
	}

	public boolean turretAtRightSoftStop() {
		if(turretEncoder.getPosition() < -Constants.Turret.RIGHT_POSITION_LIMIT+1) {
			return true;
		}
		return false;
	}
	
	public boolean turretAtLeftSoftStop() {
		if(turretEncoder.getPosition() > Constants.Turret.LEFT_POSITION_LIMIT-1) {
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
			turretPID.setReference(0, ControlType.kDutyCycle);
		}
	}

	public void setMotorTeleop(double speed) {
		if (canTurretSpin(speed))  {
			turretPID.setReference(-speed, ControlType.kDutyCycle);
		} else {
			turretPID.setReference(0, ControlType.kDutyCycle);
		}
	}
	
	public void stopTurretMotor(){
		turretPID.setReference(0, ControlType.kCurrent);
	}
	
	public void turnOnLimelightLED() {
		table.getEntry("ledMode").setNumber(3);
	}

	public void turnOffLimelightLED() {
		table.getEntry("ledMode").setNumber(1);
	}

	
	public double calcRPM(){
		if(isTargetFound() == true){
		if(area >= 1){
			return 3300;
		}
		else if(area >=.65 && area<1){
			return 3700;
		}
		else if(area <.65 && area>=.5){
			return 4100;
		}
		else if(area<.5){
			return 4400;
		}
		else{
			return 3750;
		}
	}
	else{
		return 3750;
	}
	}

}