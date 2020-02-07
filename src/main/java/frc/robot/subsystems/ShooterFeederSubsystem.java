package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterFeederSubsystem extends SubsystemBase {

	TalonSRX shooterFeederRoller = new TalonSRX(Constants.CAN.SHOOTER_FEEDER_MOTOR);

	public ShooterFeederSubsystem() {
		shooterFeederRoller.configFactoryDefault();
		setBrakeMode();
	}

	public void spin(double speed) {
		shooterFeederRoller.set(ControlMode.PercentOutput, speed);
	}

	public void stop() {
		shooterFeederRoller.set(ControlMode.PercentOutput, 0.0);
	}

	public void setBrakeMode() {
		shooterFeederRoller.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		shooterFeederRoller.setNeutralMode(NeutralMode.Coast);
	}
}