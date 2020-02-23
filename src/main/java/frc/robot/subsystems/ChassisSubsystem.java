/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ChassisSubsystem extends SubsystemBase {

	// Motor Controllers
	private CANSparkMax driveMotorLeftFront = new CANSparkMax(Constants.CAN.CHASSIS_LEFT_FRONT_PORT, MotorType.kBrushless);
	private CANSparkMax driveMotorRightFront = new CANSparkMax(Constants.CAN.CHASSIS_RIGHT_FRONT_PORT,
			MotorType.kBrushless);
	private CANSparkMax driveMotorLeftRear = new CANSparkMax(Constants.CAN.CHASSIS_LEFT_REAR_PORT, MotorType.kBrushless);
	private CANSparkMax driveMotorRightRear = new CANSparkMax(Constants.CAN.CHASSIS_RIGHT_REAR_PORT, MotorType.kBrushless);

	// Speed Controller Groups
	private SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(driveMotorLeftFront, driveMotorLeftRear);
	private SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(driveMotorRightFront, driveMotorRightRear);

	// Encoders
	private CANEncoder leftEncoder = driveMotorLeftFront.getEncoder();
	private CANEncoder rightEncoder = driveMotorRightFront.getEncoder();

	// Drivetrain
	private DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	// Gyroscope
	private AHRS ahrs;

	// Odometry
	DifferentialDriveOdometry odometry;

	// Kinematics
	public DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(Constants.Chassis.TRACK_WIDTH);

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
			odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getAngle()));
		} catch (RuntimeException exception) {
			DriverStation.reportError("Error instantiating navX-MXP: " + exception.getMessage(), true);
		}

		SmartDashboard.putNumber("Target Angle", 0);

		double conversionFactor = Constants.Chassis.WHEEL_DIAMETER * Math.PI / Constants.Chassis.GEAR_RATIO;
		leftEncoder.setPositionConversionFactor(conversionFactor);
		leftEncoder.setVelocityConversionFactor(conversionFactor); // should the conversion factor be the same as for postion?
		rightEncoder.setPositionConversionFactor(conversionFactor / 60);
		rightEncoder.setVelocityConversionFactor(conversionFactor / 60); // should the conversion factor be the same as for postion?
	}

	@Override
	public void periodic() {
		odometry.update(Rotation2d.fromDegrees(getAngle()), leftEncoder.getPosition(), rightEncoder.getPosition());
	}
	
	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(move, turn);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void voltageTankDrive(double leftVoltage, double rightVoltage) {
		leftMotorGroup.setVoltage(leftVoltage);
		rightMotorGroup.setVoltage(-rightVoltage);
		drive.feed();
	}

	public double getAngle() {
		double angle = Math.IEEEremainder(ahrs.getAngle(), 360);
		if (Constants.Chassis.GYRO_REVERSED) {
			angle *= -1;
		}
		return angle;
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
	}

	public Pose2d getPose() {
		return odometry.getPoseMeters();
	}
}