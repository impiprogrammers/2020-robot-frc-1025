package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {

	private final TalonSRX conveyorRollers = new TalonSRX(Constants.CAN.CONVEYOR_ROLLERS_MOTOR);

	public ConveyorSubsystem() {
		conveyorRollers.configFactoryDefault();
		setBrakeMode();
	}

	public void spin(double speed) {
		conveyorRollers.set(ControlMode.PercentOutput, speed);
	}

	public void stop(){
		conveyorRollers.set(ControlMode.PercentOutput, 0.0);
	}

	public void setBrakeMode() {
		conveyorRollers.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		conveyorRollers.setNeutralMode(NeutralMode.Coast);
	}
}