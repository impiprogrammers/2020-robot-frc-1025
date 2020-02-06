package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Climber;
import frc.robot.Constants.PCM;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.climber.ClimberLoop;

public class ClimberSubsystem extends SubsystemBase {
	
	Solenoid climberExtender = new Solenoid(Constants.Climber.CLIMBER_EXTENDER_MODULE, Constants.PCM.CLIMBER_EXTENDER_CHANNEL);
	Solenoid climberLock = new Solenoid(Constants.Climber.CLIMBER_LOCK_MODULE, Constants.PCM.CLIMBER_LOCK_CHANNEL);
	TalonSRX climberWinch = new TalonSRX(Constants.Climber.CLIMBER_WINCH_PORT);
	TalonSRX shimmy = new TalonSRX(Constants.Climber.CLMBER_SHIMMY_PORT);

	public ClimberSubsystem() {
		setDefaultCommand(new ClimberLoop());
	}

	public void extenderExtend() {
		climberExtender.set(true);
		RobotContainer.climberMode = true;
	}
	
	public void extenderRetract() {
		climberExtender.set(false);
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

	public void shimmyMove(double speed) {
		if (RobotContainer.climberMode) {
			shimmy.set(ControlMode.PercentOutput, speed);
		}
	}

	public void stop() {
		climberWinch.set(ControlMode.PercentOutput, 0);
		shimmy.set(ControlMode.PercentOutput, 0);
	}

	// todo: discuss whether or not stop() method should also disable the joysticks and/or disable climber mode + lock the lock
}