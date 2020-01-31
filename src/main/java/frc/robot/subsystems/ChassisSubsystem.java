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

public class ChassisSubsystem extends SubsystemBase {

	public CANSparkMax driveMotorRight = new CANSparkMax(Constants.CHASSIS_RIGHT_PORT, MotorType.kBrushless);
	public CANSparkMax driveMotorLeft = new CANSparkMax(Constants.CHASSIS_LEFT_PORT, MotorType.kBrushless);

	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(driveMotorLeft);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(driveMotorRight);

	public DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	public ChassisSubsystem() {
		driveMotorRight.setIdleMode(IdleMode.kCoast);
		driveMotorLeft.setIdleMode(IdleMode.kCoast);

		driveMotorRight.setSmartCurrentLimit(40);
		driveMotorLeft.setSmartCurrentLimit(40);
	}

	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(move, turn);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	@Override
	public void periodic(){
		
	}
}