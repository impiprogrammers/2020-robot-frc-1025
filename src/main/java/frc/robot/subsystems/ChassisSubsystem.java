package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ChassisSubsystem extends SubsystemBase {

	private final CANSparkMax driveMotorLeftFront =  new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_LEFT_FRONT,  MotorType.kBrushless);
	private final CANSparkMax driveMotorLeftRear =   new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_LEFT_REAR,   MotorType.kBrushless);
	private final CANSparkMax driveMotorRightFront = new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_RIGHT_FRONT, MotorType.kBrushless);
	private final CANSparkMax driveMotorRightRear =  new CANSparkMax(Constants.CAN.CHASSIS_DRIVE_MOTOR_RIGHT_REAR,  MotorType.kBrushless);

	private final SpeedControllerGroup driveMotorGroupLeft =  new SpeedControllerGroup(driveMotorLeftFront,  driveMotorLeftRear);
	private final SpeedControllerGroup driveMotorGroupRight = new SpeedControllerGroup(driveMotorRightFront, driveMotorRightRear);

	private final DifferentialDrive drive = new DifferentialDrive(driveMotorGroupLeft, driveMotorGroupRight);

	private final CANEncoder leftEncoder = driveMotorLeftFront.getEncoder();
	private final CANEncoder rightEncoder = driveMotorRightFront.getEncoder();

	public ChassisSubsystem() {
		setCoastMode();
		setSmartCurrentLimit(Constants.Chassis.CURRENT_LIMIT);
	}
	
	public void arcadeDrive(double move, double turn) {
		drive.arcadeDrive(move, turn);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
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

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Left Motor Speeds", leftEncoder.getVelocity());
		SmartDashboard.putNumber("Right Motor Speeds", rightEncoder.getVelocity());
	}
}