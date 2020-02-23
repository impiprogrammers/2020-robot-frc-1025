package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {

	TalonSRX conveyorRollers = new TalonSRX(Constants.CAN.CONVEYOR_ROLLERS_PORT);

	public ConveyorSubsystem() {
		conveyorRollers.set(ControlMode.PercentOutput, 0);
		conveyorRollers.configFactoryDefault();
		setBrakeMode();

	}

	public void conveyorRoll(double speed) {
		conveyorRollers.set(ControlMode.PercentOutput, speed);
	}

	public void conveyorStop(){
		conveyorRollers.set(ControlMode.PercentOutput, 0.0);
	}

	public void setBrakeMode() {
		conveyorRollers.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		conveyorRollers.setNeutralMode(NeutralMode.Coast);
	}
	@Override
	public void periodic() {
		SmartDashboard.putNumber("Conveyor Speed", conveyorRollers.getMotorOutputPercent());
	}

}