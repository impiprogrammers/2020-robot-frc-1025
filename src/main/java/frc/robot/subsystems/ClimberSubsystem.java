package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
	
	Solenoid climberExtender = new Solenoid(Constants.CLIMBER_EXTENDER_MODULE, Constants.CLIMBER_EXTENDER_CHANNEL);
	Solenoid climberLock = new Solenoid(Constants.CLIMBER_LOCK_MODULE, Constants.CLIMBER_LOCK_CHANNEL);
	TalonSRX climberWinch = new TalonSRX(Constants.CLIMBER_WINCH_PORT);
	TalonSRX shimmy = new TalonSRX(Constants.CLMBER_SHIMMY_PORT);

	public ClimberSubsystem() {

	}

	@Override
	public void periodic() {
		
	}

	public void extenderExtend() {
		climberExtender.set(true);
	}
	
	public void extenderRetract() {
		climberExtender.set(false);
	}

	public void lockToggle() {
		if (climberExtender.get()) {
			if (climberLock.get()) {
				climberLock.set(false);
			} else {
				climberLock.set(true);
			}
		}
	}

	public void winchMove(double speed) {
		if (climberExtender.get()) {
			climberWinch.set(ControlMode.PercentOutput, speed);
		}
	}

	public void shimmyMove(double speed) {
		if (climberExtender.get()) {
			shimmy.set(ControlMode.PercentOutput, speed);
		}
	}
}