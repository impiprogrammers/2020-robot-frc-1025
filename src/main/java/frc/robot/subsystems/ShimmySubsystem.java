package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;

public class ShimmySubsystem extends SubsystemBase {
	private final TalonSRX shimmy = new TalonSRX(Constants.CAN.SHIMMY_MOTOR);

	public ShimmySubsystem() {
		shimmy.configFactoryDefault();
		setBrakeMode();
	}

	public void spin(double speed) {
		shimmy.set(ControlMode.PercentOutput, speed);
	}

	public void stop() {
		shimmy.set(ControlMode.PercentOutput, 0.);
	}

	public void setBrakeMode() {
		shimmy.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		shimmy.setNeutralMode(NeutralMode.Coast);
	}
}