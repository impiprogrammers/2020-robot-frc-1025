package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

	// Motor Controllers
	private CANSparkMax leftMotor = new CANSparkMax(Constants.SHOOTER_LEFT_MOTOR, MotorType.kBrushless);
	private CANSparkMax rightMotor = new CANSparkMax(Constants.SHOOTER_RIGHT_MOTOR, MotorType.kBrushless);

	// PID Controllers
	private CANPIDController leftPID = leftMotor.getPIDController();
	private CANPIDController rightPID = rightMotor.getPIDController();

	// Doubles
	private double p0 = 0.1;
	private double i0 = 1e-4;
	private double d0 = 1;
	private double iz0 = 0;
	private double ff0 = 0;
	private double min0 = -1;
	private double max0 = 1;
	private double maxRPM = 2000;

	public ShooterSubsystem() {
		// Set PID Values
		leftPID.setP(p0);
		leftPID.setI(i0);
		leftPID.setD(d0);
		leftPID.setIZone(iz0);
		leftPID.setFF(ff0);
		leftPID.setOutputRange(min0, max0);

		rightPID.setP(p0);
		rightPID.setI(i0);
		rightPID.setD(d0);
		rightPID.setIZone(iz0);
		rightPID.setFF(ff0);
		rightPID.setOutputRange(min0, max0);

		// Put PID Values on SmartDashboard
		SmartDashboard.putNumber("P Gain", p0);
		SmartDashboard.putNumber("I Gain", i0);
		SmartDashboard.putNumber("D Gain", d0);
		SmartDashboard.putNumber("I Zone", iz0);
		SmartDashboard.putNumber("Feed Forward", ff0);
		SmartDashboard.putNumber("Min Output", min0);
		SmartDashboard.putNumber("Max Output", max0);


	}

	@Override
	public void periodic() {
		// Get PID Values from SmartDashboard
		double p = SmartDashboard.getNumber("P Gain", p0);
		double i = SmartDashboard.getNumber("I Gain", i0);
		double d = SmartDashboard.getNumber("D Gain", d0);
		double iz = SmartDashboard.getNumber("I Zone", iz0);
		double ff = SmartDashboard.getNumber("Feed Forward", ff0);
		double min = SmartDashboard.getNumber("Min Output", min0);
		double max = SmartDashboard.getNumber("Max Output", max0);

		// Reassign PID Values if Changed
		if (p != p0) {
			p0 = p;
			rightPID.setP(p0);
			leftPID.setP(p0);
		}
		if (i != i0) {
			i0 = i;
			rightPID.setI(i0);
			leftPID.setI(i0);
		}
		if (d != d0) {
			d0 = d;
			rightPID.setD(d0);
			leftPID.setD(d0);
		}
		if (iz != iz0) {
			iz0 = iz;
			rightPID.setIZone(iz0);
			leftPID.setIZone(iz0);
		}
		if (ff != ff0) {
			ff0 = ff;
			rightPID.setFF(ff0);
			leftPID.setFF(ff0);
		}
		if (min != min0) {
			min0 = min;
			rightPID.setOutputRange(min0, max0);
			leftPID.setOutputRange(min0, max0);
		}
		if (max != max0) {
			max0 = max;
			rightPID.setOutputRange(max0, min0);
			leftPID.setOutputRange(max0, min0);
		}
	}

	public void shoot() {
		double setpoint = maxRPM;
		
		leftPID.setReference(setpoint, ControlType.kVelocity);
		rightPID.setReference(setpoint, ControlType.kVelocity);
		SmartDashboard.putNumber("Setpoint", setpoint);
	}

	public void stop() {
		leftPID.setReference(0, ControlType.kVelocity);
		rightPID.setReference(0, ControlType.kVelocity);
		SmartDashboard.putNumber("Setpoint", 0);
	}
}
