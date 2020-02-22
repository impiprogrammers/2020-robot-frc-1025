package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ClimberSubsystem extends SubsystemBase {
	
	DoubleSolenoid climberArm = new DoubleSolenoid(Constants.PCM_MODULE_PORT, Constants.CLIMBER_EXTENDER_FORWARD_CHANNEL, Constants.CLIMBER_EXTENDER_REVERSE_CHANNEL);
	Solenoid climberLock = new Solenoid(Constants.PCM_MODULE_PORT, Constants.CLIMBER_LOCK_CHANNEL);
	TalonSRX climberWinch = new TalonSRX(Constants.CLIMBER_WINCH_PORT);

	public ClimberSubsystem() {
		climberArm.set(Value.kForward);
	}

	public void extenderExtend() {
		climberArm.set(Value.kReverse);
		RobotContainer.climberMode = true;
	}
	
	public void extenderRetract() {
		climberArm.set(Value.kForward);
		RobotContainer.climberMode = false;
	}

	public void lockToggle() {
		if (RobotContainer.climberMode) {
			if (climberLock.get()) {
				climberLock.set(false);
			} else {
				climberLock.set(true);
			}
		}
	}

	public void winchMove(double speed) {
		if (RobotContainer.climberMode) {
			climberWinch.set(ControlMode.PercentOutput, speed);
		}
	}

	public void stop() {
		climberWinch.set(ControlMode.PercentOutput, 0);
	}

	// todo: discuss whether or not stop() method should also disable the joysticks and/or disable climber mode + lock the lock
}