/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.chassis.ChassisDrive;

public class ChassisSubsystem extends SubsystemBase {


	public CANSparkMax driveMotorLeftFront = new CANSparkMax(Constants.CHASSIS_LEFT_FRONT_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorRightFront = new CANSparkMax(Constants.CHASSIS_RIGHT_FRONT_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorLeftRear = new CANSparkMax(Constants.CHASSIS_LEFT_REAR_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorRightRear = new CANSparkMax(Constants.CHASSIS_RIGHT_REAR_PORT, MotorType.kBrushless);

	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(driveMotorLeftFront, driveMotorLeftRear);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(driveMotorRightFront, driveMotorRightRear);

	public DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	public ChassisSubsystem() {
		driveMotorLeftFront.setIdleMode(IdleMode.kCoast);
		driveMotorRightFront.setIdleMode(IdleMode.kCoast);
		driveMotorLeftRear.setIdleMode(IdleMode.kCoast);
		driveMotorRightRear.setIdleMode(IdleMode.kCoast);

		driveMotorLeftFront.setSmartCurrentLimit(20);
		driveMotorRightFront.setSmartCurrentLimit(20);
		driveMotorLeftRear.setSmartCurrentLimit(20);
		driveMotorRightRear.setSmartCurrentLimit(20);
	}
	
	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(move, turn);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}
}