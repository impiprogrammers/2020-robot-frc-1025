package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterFeederSubsystem extends SubsystemBase {
	/**
	 * Creates a new ExampleSubsystem.
	 */
	TalonSRX shooterFeederRoller = new TalonSRX(Constants.SHOOTER_FEEDER_PORT);

	public ShooterFeederSubsystem() {

		shooterFeederRoller.set(ControlMode.PercentOutput, 0);

		shooterFeederRoller.configFactoryDefault();
		shooterFeederRoller.setNeutralMode(NeutralMode.Brake);

	}

	public void spin(double speed) {
		shooterFeederRoller.set(ControlMode.PercentOutput, speed);
	}

	public void stop() {
		shooterFeederRoller.set(ControlMode.PercentOutput, 0.0);
	}
}