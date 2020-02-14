package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.kauailabs.navx.frc.AHRS;

public class ChassisSubsystem extends SubsystemBase {

	private final AHRS navX = new AHRS();

	private final CANSparkMax driveMotorLeftFront = new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_LEFT_FRONT, MotorType.kBrushless);
	private final CANSparkMax driveMotorLeftRear = new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_LEFT_REAR,	MotorType.kBrushless);
	private final CANSparkMax driveMotorRightFront = new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_RIGHT_FRONT,	MotorType.kBrushless);
	private final CANSparkMax driveMotorRightRear = new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_RIGHT_REAR, MotorType.kBrushless);

	private final SpeedControllerGroup driveMotorGroupLeft = new SpeedControllerGroup(driveMotorLeftFront, driveMotorLeftRear);
	private final SpeedControllerGroup driveMotorGroupRight = new SpeedControllerGroup(driveMotorRightFront, driveMotorRightRear);

	private final DifferentialDrive drive = new DifferentialDrive(driveMotorGroupLeft, driveMotorGroupRight);

	private final CANEncoder leftEncoder = driveMotorLeftFront.getEncoder();
	private final CANEncoder rightEncoder = driveMotorRightFront.getEncoder();

	private final PIDController leftPIDController = new PIDController(Constants.Chassis.K_P, Constants.Chassis.K_I,	Constants.Chassis.K_D);
	private final PIDController rightPIDController = new PIDController(Constants.Chassis.K_P, Constants.Chassis.K_I, Constants.Chassis.K_D);

	private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.Chassis.TRACK_WIDTH);
	private final DifferentialDriveOdometry odometry;

	private Rotation2d startingRotation;
	private Pose2d startingPose;

	public ChassisSubsystem() {
		odometry = new DifferentialDriveOdometry(getAngle());

		setCoastMode();
		setSmartCurrentLimit(Constants.Chassis.CURRENT_LIMIT);
		resetGyro();

		double conversionFactor = Math.PI * Constants.Chassis.WHEEL_DIAMETER
				* (Constants.Chassis.STAGE_1_PINION_TEETH / Constants.Chassis.STAGE_1_GEAR_TEETH)
				* (Constants.Chassis.STAGE_2_PINION_TEETH / Constants.Chassis.STAGE_2_GEAR_TEETH);
		leftEncoder.setPositionConversionFactor(conversionFactor);
		rightEncoder.setPositionConversionFactor(conversionFactor);

		conversionFactor = conversionFactor / 60.;
		leftEncoder.setVelocityConversionFactor(conversionFactor);
		rightEncoder.setVelocityConversionFactor(conversionFactor);
		resetEncoders();
	}

	public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
		double leftOutput = leftPIDController.calculate(leftEncoder.getVelocity(), speeds.leftMetersPerSecond);
		double rightOutput = rightPIDController.calculate(rightEncoder.getVelocity(), speeds.rightMetersPerSecond);
		driveMotorGroupLeft.set(leftOutput);
		driveMotorGroupRight.set(rightOutput);
	}

	public void driveBySpeed(double forwardSpeed, double rotationSpeed) {
		var wheelSpeeds = kinematics.toWheelSpeeds(new ChassisSpeeds(forwardSpeed, 0.0, rotationSpeed));
		setSpeeds(wheelSpeeds);
	}

	public Rotation2d getAngle() {
		return Rotation2d.fromDegrees(navX.getAngle());
	}

	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(move, turn);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void resetEncoders() {
		leftEncoder.setPosition(0.);
		rightEncoder.setPosition(0.);
	}

	public void resetGyro() {
		navX.reset();
	}

	public void setBrakeMode() {
		driveMotorLeftFront.setIdleMode(IdleMode.kBrake);
		driveMotorLeftRear.setIdleMode(IdleMode.kBrake);
		driveMotorRightFront.setIdleMode(IdleMode.kBrake);
		driveMotorRightRear.setIdleMode(IdleMode.kBrake);
	}

	public void setCoastMode() {
		driveMotorLeftFront.setIdleMode(IdleMode.kCoast);
		driveMotorLeftRear.setIdleMode(IdleMode.kCoast);
		driveMotorRightFront.setIdleMode(IdleMode.kCoast);
		driveMotorRightRear.setIdleMode(IdleMode.kCoast);
	}

	public void setSmartCurrentLimit(int currentLimit) {
		driveMotorLeftFront.setSmartCurrentLimit(currentLimit);
		driveMotorRightFront.setSmartCurrentLimit(currentLimit);
		driveMotorLeftRear.setSmartCurrentLimit(currentLimit);
		driveMotorRightRear.setSmartCurrentLimit(currentLimit);
	}

	public void stop() {
		drive.arcadeDrive(0., 0.);
	}

	public void updateOdometry() {
		odometry.update(getAngle(), leftEncoder.getPosition(), rightEncoder.getPosition());
		odometry.getPoseMeters();
	}

	public double getLeftEncoderPosition() {
		return leftEncoder.getPosition();
	}

	public double getRightEncoderPosition() {
		return rightEncoder.getPosition();
	}

	public void resetOdometry(Pose2d position, Rotation2d angle) {
		resetEncoders();
		odometry.resetPosition(position, angle);
	}

	public void resetRobotPose(Pose2d position) {
		startingPose = position;
	}

	public void resetRobotAngle(Rotation2d rotation) {
		startingRotation = rotation;
	}

	public Pose2d getRobotPosition() {
		return startingPose.relativeTo(odometry.getPoseMeters());
	}

	public Rotation2d getRobotAngle() {
		return startingRotation.plus(getAngle());
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		driveMotorGroupLeft.setVoltage(leftVolts);
		driveMotorGroupRight.setVoltage(-rightVolts);
	}
	
	@Override
	public void periodic() {
		updateOdometry();
		SmartDashboard.putNumber("Left Motor Speeds", leftEncoder.getVelocity());
		SmartDashboard.putNumber("Right Motor Speeds", rightEncoder.getVelocity());
	}
}