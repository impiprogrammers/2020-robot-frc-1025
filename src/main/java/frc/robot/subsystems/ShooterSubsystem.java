package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
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

	private CANEncoder leftEncoder = leftMotor.getEncoder();
	private CANEncoder rightEncoder = rightMotor.getEncoder();

	// Doubles
	private double p0 = 5e-5;
	private double i0 = 1e-6;
	private double d0 = 0;
	private double iz0 = 0;
	private double ff0 = 0;
	private double min0 = -1;
	private double max0 = 1;
	private double maxRPM = 5700;

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
		PIDsetup();
	
	}
	public void PIDsetup(){
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
			rightPID.setP(p);
			leftPID.setP(p);
		}
		if (i != i0) {
			i0 = i;
			rightPID.setI(i);
			leftPID.setI(i);
		}
		if (d != d0) {
			d0 = d;
			rightPID.setD(d);
			leftPID.setD(d);
		}
		if (iz != iz0) {
			iz0 = iz;
			rightPID.setIZone(iz);
			leftPID.setIZone(iz);
		}
		if (ff != ff0) {
			ff0 = ff;
			rightPID.setFF(ff);
			leftPID.setFF(ff);
		}
		if (min != min0) {
			min0 = min;
			rightPID.setOutputRange(min, max);
			leftPID.setOutputRange(min, max);
		}
		if (max != max0) {
			max0 = max;
			rightPID.setOutputRange(max, min);
			leftPID.setOutputRange(max, min);
		}
	}

	public void shoot(double setpoint) {
		leftPID.setReference(-setpoint, ControlType.kVelocity);
		rightPID.setReference(setpoint, ControlType.kVelocity);
		// leftMotor.pidWrite(-.25);
		// rightMotor.pidWrite(-.25);
		
		SmartDashboard.putNumber("Setpoint", setpoint);
		SmartDashboard.putNumber("Velocity", rightEncoder.getVelocity());
		
	}

	public void stop() {
		leftPID.setReference(0, ControlType.kVelocity);
		rightPID.setReference(0, ControlType.kVelocity);
	}
}
