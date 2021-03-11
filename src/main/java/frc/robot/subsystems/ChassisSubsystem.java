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
	private final CANSparkMax driveMotorLeftFront = new CANSparkMax(Constants.CAN.CHASSIS_LEFT_FRONT_PORT, MotorType.kBrushless);
	private final CANSparkMax driveMotorRightFront = new CANSparkMax(Constants.CAN.CHASSIS_RIGHT_FRONT_PORT, MotorType.kBrushless);
	private final CANSparkMax driveMotorLeftRear = new CANSparkMax(Constants.CAN.CHASSIS_LEFT_REAR_PORT, MotorType.kBrushless);
	private final CANSparkMax driveMotorRightRear = new CANSparkMax(Constants.CAN.CHASSIS_RIGHT_REAR_PORT, MotorType.kBrushless);

	// Speed Controller Groups
	private final SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(driveMotorLeftFront, driveMotorLeftRear);
	private final SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(driveMotorRightFront, driveMotorRightRear);

	// Encoders
	private final CANEncoder leftEncoder = driveMotorLeftFront.getEncoder();
	private final CANEncoder rightEncoder = driveMotorRightFront.getEncoder();

	// Drivetrain
	private final DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

	// Gyroscope
	private AHRS ahrs;

	// Odometry
	private DifferentialDriveOdometry odometry;

	// Kinematics
	public static final DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(Constants.Chassis.TRACK_WIDTH);

	public ChassisSubsystem() {
		restoreFactoryDefaults();
		setSmartCurrentLimit(Constants.Chassis.CURRENT_LIMIT);
		setCoastMode();

		ahrs = new AHRS(SPI.Port.kMXP);
		odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-ahrs.getAngle()));

		double conversionFactor = Constants.Chassis.WHEEL_DIAMETER * Math.PI / Constants.Chassis.GEAR_RATIO;
		leftEncoder.setPositionConversionFactor(conversionFactor);
		leftEncoder.setVelocityConversionFactor(conversionFactor / 60);
		rightEncoder.setPositionConversionFactor(conversionFactor);
		rightEncoder.setVelocityConversionFactor(conversionFactor / 60);
		resetEncoders();
		resetGyro();
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Wheel Encoders", getPosition());
		// SmartDashboard.putNumber("Left Encoder Position", leftEncoder.getPosition());
		// SmartDashboard.putNumber("Right Encoder Position", rightEncoder.getPosition());		
	    odometry.update(Rotation2d.fromDegrees(-ahrs.getAngle()), leftEncoder.getPosition(), rightEncoder.getPosition());
	}

	public void setSmartCurrentLimit(int currentLimit) {
		driveMotorLeftFront.setSmartCurrentLimit(currentLimit);
		driveMotorRightFront.setSmartCurrentLimit(currentLimit);
		driveMotorLeftRear.setSmartCurrentLimit(currentLimit);
		driveMotorRightRear.setSmartCurrentLimit(currentLimit);
	}

	public void restoreFactoryDefaults(){
		driveMotorLeftFront.restoreFactoryDefaults();
		driveMotorLeftRear.restoreFactoryDefaults();
		driveMotorRightFront.restoreFactoryDefaults();
		driveMotorRightRear.restoreFactoryDefaults();
	}

	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(-move, (turn/2)); //done on thursday, needs to be tested > "(... /2)"
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void voltageTankDrive(double leftVoltage, double rightVoltage) {
		leftMotorGroup.setVoltage(leftVoltage);
		rightMotorGroup.setVoltage(-rightVoltage);
		drive.feed();
		System.out.println("Voltage Tank Drive");
	}

	public void resetEncoders() {
		leftEncoder.setPosition(0);
		rightEncoder.setPosition(0);
	}

	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		odometry.resetPosition(pose, Rotation2d.fromDegrees(-ahrs.getAngle()));
	  }

	public void resetGyro() {
		ahrs.reset();
		ahrs.resetDisplacement();
	}

	public void setBrakeMode() {
		driveMotorLeftFront.setIdleMode(IdleMode.kBrake);
		driveMotorRightFront.setIdleMode(IdleMode.kBrake);
		driveMotorLeftRear.setIdleMode(IdleMode.kBrake);
		driveMotorRightRear.setIdleMode(IdleMode.kBrake);
	}

	public void setCoastMode() {
		driveMotorLeftFront.setIdleMode(IdleMode.kCoast);
		driveMotorRightFront.setIdleMode(IdleMode.kCoast);
		driveMotorLeftRear.setIdleMode(IdleMode.kCoast);
		driveMotorRightRear.setIdleMode(IdleMode.kCoast);
	}

	public double getPosition() { // returns position in meters
		return (Math.abs(leftEncoder.getPosition()) + Math.abs(rightEncoder.getPosition())) / 2;
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

	public void stopChassis() {
		rightMotorGroup.stopMotor();
		leftMotorGroup.stopMotor();
	}
}