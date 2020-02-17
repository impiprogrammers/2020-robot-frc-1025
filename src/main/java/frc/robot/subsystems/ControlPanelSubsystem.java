package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class ControlPanelSubsystem extends SubsystemBase {
	private final TalonSRX controlPanelWheel =  new TalonSRX(Constants.CAN.CONTROL_PANEL_WHEEL_MOTOR);
	private final Solenoid controlPanelArm = new Solenoid(Constants.PCM.CONTROL_PANEL_ARM);

	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
	private final ColorMatch colorMatcher = new ColorMatch();

	public ControlPanelSubsystem() {
		controlPanelWheel.configFactoryDefault();
		setBrakeMode();
	}

	public void extendControlPanelArm() {
		controlPanelArm.set(true);
	}

	public void retractControlPanelArm() {
		controlPanelArm.set(false);
	}

	public void toggleControlPanelArm() {
		controlPanelArm.set(!controlPanelArm.get());
	}

	public boolean isControlPanelArmExtended() {
		return controlPanelArm.get();
	}

	public void controlPanelWheelSpinFour(){
		
	}

	public void controlPanelWheelColor(){
	}

	public void spin(double speed) {
		controlPanelWheel.set(ControlMode.PercentOutput, speed);
	}

	public void stop() {
		controlPanelWheel.set(ControlMode.PercentOutput, 0.);
	}

	public void setBrakeMode() {
		controlPanelWheel.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		controlPanelWheel.setNeutralMode(NeutralMode.Coast);
	}

	@Override
	public void periodic() {
		SmartDashboard.putBoolean("Control Panel Arm Extended", isControlPanelArmExtended());
		SmartDashboard.putNumber("Control Panel Wheel Speed", controlPanelWheel.getMotorOutputPercent());
	}
}
