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

	private Pose2d startingPose = new Pose2d();

	public ChassisSubsystem() {
		// Setup the NavX
		resetGyro();
		odometry = new DifferentialDriveOdometry(getAngle());

		// Setup the motor controllers
		restoreMotorFactoryDefaults();
		setCoastMode();
		setSmartCurrentLimit(Constants.Chassis.CURRENT_LIMIT);
		drive.setDeadband(Constants.OI.JOYSTICK_DEADBAND);

		// Setup the encoders
		leftEncoder.setPositionConversionFactor(Constants.Chassis.POSITION_CONVERSION_FACTOR);
		rightEncoder.setPositionConversionFactor(Constants.Chassis.POSITION_CONVERSION_FACTOR);
		leftEncoder.setVelocityConversionFactor(Constants.Chassis.VELOCITY_CONVERSION_FACTOR);
		rightEncoder.setVelocityConversionFactor(Constants.Chassis.VELOCITY_CONVERSION_FACTOR);
		resetEncoders();
	}

	public void restoreMotorFactoryDefaults() {
		driveMotorLeftFront.restoreFactoryDefaults();
		driveMotorLeftRear.restoreFactoryDefaults();
		driveMotorRightFront.restoreFactoryDefaults();
		driveMotorRightRear.restoreFactoryDefaults();
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

	public Pose2d getRobotPose() {
		return odometry.getPoseMeters().relativeTo(startingPose);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		driveMotorGroupLeft.setVoltage(leftVolts);
		driveMotorGroupRight.setVoltage(-rightVolts);
	}

	public Translation2d getDistanceToTarget() {
		return getRobotPose().getTranslation().minus(Constants.FieldPositions.TARGET_LOCATION);
	}

	public Rotation2d getAngleToTarget() {
		Translation2d distanceToTarget = getDistanceToTarget();
		Rotation2d angleToTargetFromPosition = new Rotation2d(distanceToTarget.getX(), distanceToTarget.getY());
		return angleToTargetFromPosition.minus(getRobotPose().getRotation());
	}

	@Override
	public void periodic() {
		updateOdometry();
		SmartDashboard.putNumber("Left Motor Speeds", leftEncoder.getVelocity());
		SmartDashboard.putNumber("Right Motor Speeds", rightEncoder.getVelocity());
		SmartDashboard.putNumber("Robot Position X", getRobotPose().getTranslation().getX());
		SmartDashboard.putNumber("Robot Position Y", getRobotPose().getTranslation().getY());
		SmartDashboard.putNumber("Robot Angle", getRobotPose().getRotation().getDegrees());
		SmartDashboard.putNumber("Angle To Target", getAngleToTarget().getDegrees());
	}
}