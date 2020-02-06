package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.climber.ClimberLoop;

public class ClimberSubsystem extends SubsystemBase {
	
	Solenoid climberArm = new Solenoid(Constants.CLIMBER_EXTENDER_MODULE, Constants.CLIMBER_EXTENDER_CHANNEL);
	Solenoid climberLock = new Solenoid(Constants.CLIMBER_LOCK_MODULE, Constants.CLIMBER_LOCK_CHANNEL);
	TalonSRX climberWinch = new TalonSRX(Constants.CLIMBER_WINCH_PORT);
	TalonSRX climberShimmy = new TalonSRX(Constants.CLMBER_climberShimmy_PORT);

	public ClimberSubsystem() {
		setDefaultCommand(new ClimberLoop());
	}

	public void extenderExtend() {
		climberArm.set(true);
		RobotContainer.climberMode = true;
	}
	
	public void extenderRetract() {
		climberArm.set(false);
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

	public void climberShimmyMove(double speed) {
		if (RobotContainer.climberMode) {
			climberShimmy.set(ControlMode.PercentOutput, speed);
		}
	}

	public void stop() {
		climberWinch.set(ControlMode.PercentOutput, 0);
		climberShimmy.set(ControlMode.PercentOutput, 0);
	}

	// todo: discuss whether or not stop() method should also disable the joysticks and/or disable climber mode + lock the lock
}