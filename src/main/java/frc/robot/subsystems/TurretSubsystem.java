package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.turret.TurretSpin;
import frc.robot.commands.turret.ToggleLimelightLock;
import frc.robot.subsystems.ShooterSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class TurretSubsystem extends SubsystemBase {

	private final CANSparkMax turretMotor = new CANSparkMax(Constants.CAN.TURRET_MOTOR, MotorType.kBrushless);
	private final CANPIDController turretPID = turretMotor.getPIDController();
	private final CANEncoder turretEncoder = turretMotor.getEncoder();

	// LIMELIGHT
	// \_(-_-)_/

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");

	// read values periodically
	double x = tx.getDouble(0.0);
	double y = ty.getDouble(0.0);
	double area = ta.getDouble(0.0);

	private static int rainbowFirstPixelHue;
	private AddressableLED led = new AddressableLED(9);
	private static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(60);
	private static int timer = 0;

	public void Update_Limelight_Tracking() {
		double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
		double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
		double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

	}

	/**
	 * Creates a new TurretSubsystem.
	 */
	public TurretSubsystem() {
		turretMotor.setIdleMode(IdleMode.kBrake);

		turretMotor.setSmartCurrentLimit(20);

		led.setLength(ledBuffer.getLength());

		led.setData(ledBuffer);
		led.start();

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public static void TurretSpin(double speed) {
		turretMotor.set(speed);
		// set LEDs 
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tv = table.getEntry("tv");
		boolean targetVisibility = tv.getBoolean(false);
		NetworkTableEntry tx = table.getEntry("tx");
		double targetCentered = tx.getDouble(0);

		if(targetVisibility) {
		if(targetCentered >= -1 && targetCentered <= 1) {
			if(RobotContainer.shooterSubsystem.getController().atSetpoint()) {
			SetLEDsSolidGreen();
			} else {
			SetLEDsFlashGreen();
			}
		} else {
			if(RobotContainer.shooterSubsystem.getController().atSetpoint()) {
				//SetLEDsSolidBlueLeft() || SetLEDsSolidBlueRight();
			} else {
				//SetLEDsFlashBlueleft() || SetLEDsFlashBlueRight();
			}
		}
		} else {
			if(targetCentered >= -1 && targetCentered <= 1) {
			} else {
				if(RobotContainer.shooterSubsystem.getController().atSetpoint()) {
				SetLEDsSolidOrange();
				} else {
				SetLEDsOff();
				}
			}
		}
	}

	public static void ToggleLimelightLock() {
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tx = table.getEntry("tx");
		double errorValue = tx.getDouble(0);
		NetworkTableEntry tv = table.getEntry("tv");
		boolean targetVisibility = tv.getBoolean(false);
		double targetCentered = tx.getDouble(0);
		double Krip = -0.1;
		double min_speed = 0.05;
		double negativeErrorValue = -errorValue;
		double steeringAdjustment = 0.0;

		if (-errorValue > 1.05) {
		steeringAdjustment = Krip * negativeErrorValue - min_speed;
		turretMotor.set(-steeringAdjustment);
		}

		else if (-errorValue < 0.95) {
		steeringAdjustment = Krip * negativeErrorValue + min_speed;
		turretMotor.set(steeringAdjustment);
		}

		if(targetVisibility) {
		if(targetCentered >= -1 && targetCentered <= 1) {
			if(RobotContainer.shooterSubsystem.getController().atSetpoint()) {
			SetLEDsSolidGreen();
			} else {
			SetLEDsFlashGreen();
			}
		} else {
			if(RobotContainer.shooterSubsystem.getController().atSetpoint()) {
				//SetLEDsSolidBlueLeft() || SetLEDsSolidBlueRight();
			} else {
				//SetLEDsFlashBlueleft() || SetLEDsFlashBlueRight();
			}
		}
		} else {
			if(targetCentered >= -1 && targetCentered <= 1) {
			} else {
				if(RobotContainer.shooterSubsystem.getController().atSetpoint()) {
				SetLEDsSolidOrange();
				} else {
				SetLEDsOff();
				}
			}
		}
	}

	public static void SetLEDsOff() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		ledBuffer.setHSV(i, 0, 0, 0);
		}
	}

	public static void SetLEDsSolidOrange() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		ledBuffer.setHSV(i, 30/2, 255, 255);
		}
	}

	public static void SetLEDsFlashBlueRight() {
		timer++;
		if(timer > 100 ) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// ledBuffer.setHSV(i, 0, 0, 0);
			if (i < ledBuffer.getLength()/2) {
			ledBuffer.setHSV(i, 225/2, 255, 255);
			} else {
			ledBuffer.setHSV(i, 0/2, 0, 0);
			}
		}
		} else {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0/2, 0, 0);
		}
		}
		if (timer >= 200){
		timer = 0;
		}
	}

	public static void SetLEDsFlashBlueleft() {
		timer++;
		if(timer > 100 ) {
		for (var i = 0; i < (ledBuffer.getLength()); i++) {
			// ledBuffer.setHSV(i, 0, 0, 0);
			if (i < (ledBuffer.getLength()/2) + 30) {
			ledBuffer.setHSV(i, 225/2, 255, 255);
			} else {
			ledBuffer.setHSV(i, 0/2, 0, 0);
			}
		}
		} else {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0/2, 0, 0);
		}
		}
		if (timer >= 200){
		timer = 0;
		}
	}

	public static void SetLEDsSolidBlueRight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		// ledBuffer.setHSV(i, 0, 0, 0);
		if (i < ledBuffer.getLength()/2) {
			ledBuffer.setHSV(i, 225/2, 255, 255);
		} else {
			ledBuffer.setHSV(i, 0, 0, 0);
		}
		}
	}

	public static void SetLEDsSolidBlueLeft() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		// ledBuffer.setHSV(i, 0, 0, 0);
		if (i < ledBuffer.getLength()/2 + 30) {
			ledBuffer.setHSV(i, 225/2, 255, 255);
		} else {
			ledBuffer.setHSV(i, 0, 0, 0);
		}
		}
	}

	public static void SetLEDsFlashGreen() {
		timer++;
		if(timer > 100 ) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 115/2, 255, 255);
		}
		}
		if (timer >= 200){
		timer = 0;
		}
	}

	public static void SetLEDsSolidGreen() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		ledBuffer.setHSV(i, 115/2, 255, 255);
		}
	}

	public static void SetLEDsFlashRed() {
		timer++;
		if(timer > 100 ) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0/2, 255, 255);
		}
		}
		if (timer >= 200){
		timer = 0;
		}
	}

	public static void SetLEDsSolidRed() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		ledBuffer.setHSV(i, 0/2, 255, 255);
		}
	}

	public static void SetLEDsFlashYellow() {
		timer++;
		if(timer > 100 ) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0, 0, 0);
		} 
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 42/2, 255, 255);
			}
		} 
		if (timer >= 200){
			timer = 0;
		}
	}

	public static void SetLEDsSolidYellow() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
		ledBuffer.setHSV(i, 42/2, 255, 255);
		}
	}
}
