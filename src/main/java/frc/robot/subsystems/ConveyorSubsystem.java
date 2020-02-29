package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.ImpiLib2020;

public class ConveyorSubsystem extends SubsystemBase {

	TalonSRX conveyorRollers = new TalonSRX(Constants.CAN.CONVEYOR_ROLLERS_PORT);

	AnalogInput tunnelSensor = new AnalogInput(Constants.Sensor.TUNNEL_SENSOR_PORT);

	double trackSensorValue;
	private Timer tunnelTimer = new Timer();

	public ConveyorSubsystem() {
		conveyorRollers.set(ControlMode.PercentOutput, 0);
		conveyorRollers.configFactoryDefault();
		setBrakeMode();
		trackSensorValue = tunnelSensor.getVoltage();
		tunnelTimer.reset();

	}

	public void conveyorRoll(double speed) {
		conveyorRollers.set(ControlMode.PercentOutput, speed);
	}
	
	public void conveyorRollFailsafe(double speed){
		if(isTunnelJammed()) {
			conveyorRollers.set(ControlMode.PercentOutput, ImpiLib2020.clamp(speed, -1, 0));
		} else {
			conveyorRollers.set(ControlMode.PercentOutput, speed);
		}

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

	public boolean isTunnelJammed(){
		if(tunnelSensor.getVoltage() > Constants.Conveyor.TUNNEL_JAMMED_VOLTAGE) {
			if(tunnelTimer.get() > Constants.Conveyor.TUNNEL_TIMER_LIMIT){
				return true;
			} else {
				return false;
			}
		} else {
			tunnelTimer.reset();
			return false;
		}
	}
	@Override
	public void periodic() {
		SmartDashboard.putNumber("Conveyor Speed", conveyorRollers.getMotorOutputPercent());
	}

}