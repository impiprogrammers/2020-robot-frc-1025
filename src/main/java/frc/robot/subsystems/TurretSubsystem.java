package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TurretSubsystem extends SubsystemBase {

	private final CANSparkMax turretMotor = new CANSparkMax(Constants.CAN.TURRET_MOTOR, MotorType.kBrushless);
	private final PIDController pidController = new PIDController(Constants.Turret.K_P, Constants.Turret.K_I, Constants.Turret.K_D);
	private final CANEncoder turretEncoder = turretMotor.getEncoder();

 	private boolean manualMode = true;

	private double limelightCanSeeTarget; // tv
	private double limelightHorizontalOffset; // tx
	private double limelightVerticalOffset; // tv
	private double limelightTargetArea; // ta
	private double limelightSkew; // ts

	public TurretSubsystem() {
		// Setup the Motor
		turretMotor.restoreFactoryDefaults();
		turretMotor.setSmartCurrentLimit(Constants.Turret.CURRENT_LIMIT);
		setBrakeMode();
		turretMotor.setSoftLimit(SoftLimitDirection.kReverse, Constants.Turret.MIN_ANGLE);
		turretMotor.setSoftLimit(SoftLimitDirection.kForward, Constants.Turret.MAX_ANGLE);

		// Setup the encoder
		turretEncoder.setPositionConversionFactor(Constants.Turret.POSITION_CONVERSION_FACTOR);
		resetEncoder();

		// Setup the PID Controller
		pidController.enableContinuousInput(Constants.Turret.MIN_ANGLE, Constants.Turret.MAX_ANGLE);
	}

	public void setManualMode() {
		manualMode = true;
	}

	public void setAutoMode() {
		manualMode = false;
	}

	public void resetEncoder() {
		turretEncoder.setPosition(0.);
	}

	public double getTurretAngle() {
		return turretEncoder.getPosition();
	}

	public void setBrakeMode() {
		turretMotor.setIdleMode(IdleMode.kBrake);
	}

	public void setCoastMode() {
		turretMotor.setIdleMode(IdleMode.kCoast);
	}

	public void updateLimelightTracking() {
		NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
		limelightCanSeeTarget = limelightTable.getEntry("tv").getDouble(0.);
		limelightHorizontalOffset = limelightTable.getEntry("tx").getDouble(0.);
		limelightVerticalOffset = limelightTable.getEntry("tv").getDouble(0.);
		limelightTargetArea = limelightTable.getEntry("ta").getDouble(0.);
		limelightSkew = limelightTable.getEntry("ts").getDouble(0.);
	}

	public void stop() {
		turretMotor.set(0.);
	}

	public boolean isManualMode() {
		return manualMode;
	}

	public boolean canLimelightSeeTarget() {
		return (limelightCanSeeTarget > 0.5); 
	}

	public double getLimelightHorizontalOffset() {
		return limelightHorizontalOffset;
	}

	public double getLimelightVerticalOffset() {
		return limelightVerticalOffset;
	}

	public double getLimelightTargetArea() {
		return limelightTargetArea;
	}

	public double getLimelightSkew() {
		return limelightSkew;
	}

	public void spin(double speed) {
		double turretAngle = getTurretAngle();
		if ((turretAngle > Constants.Turret.MAX_ANGLE) && (speed > 0.)) {
			speed = 0.;
		}
		if ((turretAngle < Constants.Turret.MIN_ANGLE) && (speed < 0.)) {
			speed = 0.;
		}
		turretMotor.set(speed);
	}

	public void spinToAngle(double angle) {
		pidController.setSetpoint(angle);
		turretMotor.set(pidController.calculate(getTurretAngle()));
	}

	public void resetController() {
		pidController.reset();
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Turret Angle", turretEncoder.getPosition());
	}
}
