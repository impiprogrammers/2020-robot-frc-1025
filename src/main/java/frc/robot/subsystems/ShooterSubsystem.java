package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSubsystem extends SubsystemBase {
	private final CANSparkMax shooterLeft = new CANSparkMax(Constants.CAN.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
	private final CANSparkMax shooterRight = new CANSparkMax(Constants.CAN.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);

	private CANPIDController pidController = shooterLeft.getPIDController();

	private CANEncoder shooterEncoder = shooterLeft.getEncoder();

	private boolean shooterEnabled = false;
	private boolean shooterReady = false;
	private double shooterSetSpeed = 0.;
	private Timer timer = new Timer();

	public ShooterSubsystem() {
		shooterRight.follow(shooterLeft, true);

		pidController.setP(Constants.Shooter.K_P);
		pidController.setI(Constants.Shooter.K_I);
		pidController.setD(Constants.Shooter.K_D);
		pidController.setFF(Constants.Shooter.K_FF);
		pidController.setOutputRange(Constants.Shooter.MIN, Constants.Shooter.MAX);

		setCoastMode();
		setSmartCurrentLimit(Constants.Shooter.CURRENT_LIMIT);
	}

	public void shoot(double speed) {
		pidController.setReference(speed, ControlType.kVelocity);
		timer.reset();
		shooterSetSpeed = speed;
		shooterEnabled = true;
		shooterReady = false;
	}

	public void stop() {
		shooterLeft.set(0.);
		shooterSetSpeed = 0.;
		shooterEnabled = false;
		shooterReady = false;
	}

	public void toggle(double speed) {
		if (shooterEnabled) {
			stop();
		} else {
			shoot(speed);
		}
	}

	public boolean isShooterReady() {
		return shooterReady;
	}

	public boolean isShooterEnabled() {
		return shooterEnabled;
	}

	public void setBrakeMode() {
		shooterLeft.setIdleMode(IdleMode.kBrake);
		shooterRight.setIdleMode(IdleMode.kBrake);
	}

	public void setCoastMode() {
		shooterLeft.setIdleMode(IdleMode.kCoast);
		shooterRight.setIdleMode(IdleMode.kCoast);
	}

	public void setSmartCurrentLimit(int currentLimit) {
		shooterLeft.setSmartCurrentLimit(currentLimit);
		shooterRight.setSmartCurrentLimit(currentLimit);
	}

	public void updateShooterReady() {
		double shooterSpeed = shooterEncoder.getVelocity();
		if (shooterEnabled) {
			if (Math.abs(shooterSpeed - shooterSetSpeed) > Constants.Shooter.ALLOWABLE_SHOOTER_ERROR) {
				timer.reset();
				shooterReady = false;
			} else {
				if (timer.get() > Constants.Shooter.SHOOTER_TIME) {
					shooterReady = true;
				} else {
					shooterReady = false;
				}
			}
		} else {
			shooterReady = false;
		}
	}

	@Override
	public void periodic() {
		updateShooterReady();

		SmartDashboard.putNumber("Shooter RPM", shooterEncoder.getVelocity());
		SmartDashboard.putNumber("Shooter Set RPM", shooterSetSpeed);
		SmartDashboard.putBoolean("Shooter Ready", shooterReady);
	}
}