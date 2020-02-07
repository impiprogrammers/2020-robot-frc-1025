package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
	
	private final DoubleSolenoid climberArm = new DoubleSolenoid(Constants.PCM.CLIMBER_ARM_EXTEND, Constants.PCM.CLIMBER_ARM_RETRACT);
	private final Solenoid climberLock = new Solenoid(Constants.PCM.CLIMBER_LOCK);
	private final TalonSRX climberWinch = new TalonSRX(Constants.CAN.CLIMBER_WINCH_MOTOR);

	private boolean climberExtended = false;

	public ClimberSubsystem() {
		setBrakeMode();
	}

	public void extendClimberArm() {
		climberArm.set(DoubleSolenoid.Value.kForward);
		climberExtended = true;
	}
	
	public void retractClimberArm() {
		climberArm.set(DoubleSolenoid.Value.kReverse);
		climberExtended = false;
	}

	public void toggleClimberArm() {
		if (isClimberArmExtended()) {
			retractClimberArm();
		} else {
			extendClimberArm();
		}
	}

	public boolean isClimberArmExtended() {
		return climberExtended;
	}

	public void lockClimber() {
		climberLock.set(false);
	}

	public void unlockClimber() {
		if (isClimberArmExtended()) {
			climberLock.set(true);
		}
	}

	public boolean isClimberLocked() {
		return climberLock.get();
	}

	public void toggleClimberLock() {
		if (isClimberLocked()) {
			unlockClimber();
		} else {
			lockClimber();
		}
	}

	public void winch(double speed) {
		if (isClimberArmExtended() && !isClimberLocked()) {
			climberWinch.set(ControlMode.PercentOutput, speed);
		}
	}

	public void stop() {
		climberWinch.set(ControlMode.PercentOutput, 0.);
	}

	public void setBrakeMode() {
		climberWinch.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		climberWinch.setNeutralMode(NeutralMode.Coast);
	}
}