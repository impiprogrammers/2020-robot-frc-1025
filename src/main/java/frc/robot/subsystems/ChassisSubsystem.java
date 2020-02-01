/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ChassisSubsystem extends SubsystemBase {

	// Motor Controllers
	public CANSparkMax driveMotorLeftFront = new CANSparkMax(Constants.CHASSIS_LEFT_FRONT_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorRightFront = new CANSparkMax(Constants.CHASSIS_RIGHT_FRONT_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorLeftRear = new CANSparkMax(Constants.CHASSIS_LEFT_REAR_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorRightRear = new CANSparkMax(Constants.CHASSIS_RIGHT_REAR_PORT, MotorType.kBrushless);

	// Speed Controller Groups
	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup( driveMotorLeftRear);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(driveMotorRightFront);

	// Drivetrain Objects
	public DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	// Gyroscope Objects
	AHRS ahrs;

	public ChassisSubsystem() {
		driveMotorLeftFront.setIdleMode(IdleMode.kCoast);
		driveMotorRightFront.setIdleMode(IdleMode.kCoast);
		driveMotorLeftRear.setIdleMode(IdleMode.kCoast);
		driveMotorRightRear.setIdleMode(IdleMode.kCoast);

		driveMotorLeftFront.setSmartCurrentLimit(40);
		driveMotorRightFront.setSmartCurrentLimit(40);
		driveMotorLeftRear.setSmartCurrentLimit(40);
		driveMotorRightRear.setSmartCurrentLimit(40);

		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException exception) {
			DriverStation.reportError("Error instantiating navX-MXP: " + exception.getMessage(), true);
		}

		SmartDashboard.putNumber("Target Angle", 0);
	}

	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(move, turn);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public double getAngle() {
		return ahrs.getAngle();
	}
}