package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

	private final Solenoid intakeArm = new Solenoid(Constants.PCM.CLIMBER_ARM_EXTEND, Constants.PCM.CLIMBER_ARM_RETRACT);
	private final TalonSRX intakeRollers = new TalonSRX(Constants.CAN.INTAKE_ROLLERS_MOTOR);

	public IntakeSubsystem() {
		intakeRollers.configFactoryDefault();
		setBrakeMode();
	}

	public void spin(double speed) {
		intakeRollers.set(ControlMode.PercentOutput, speed);
	}

	public void stop(){
		intakeRollers.set(ControlMode.PercentOutput, 0.0);
	}

	public void setBrakeMode() {
		intakeRollers.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		intakeRollers.setNeutralMode(NeutralMode.Coast);
	}

	public void toggleIntakeArm() {
		intakeArm.set(!intakeArm.get());
	}

	public boolean isIntakeArmExtended() {
		return intakeArm.get();
	}

	public void extendIntakeArm() {
		intakeArm.set(true);
	}

	public void retractIntakeArm() {
		intakeArm.set(false);
	}

	@Override
	public void periodic() {
		SmartDashboard.putBoolean("Intake Arm Extended", isIntakeArmExtended());
		SmartDashboard.putNumber("Intake Speed", intakeRollers.getMotorOutputPercent());
	}
}
