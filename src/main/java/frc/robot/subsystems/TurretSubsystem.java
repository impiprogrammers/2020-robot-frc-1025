/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends SubsystemBase {

	// Motor Controller
	private CANSparkMax turretRotate = new CANSparkMax(Constants.TURRET_ROTATE_PORT, MotorType.kBrushless);

	// PID Controller
	private CANPIDController turretPID = turretRotate.getPIDController();

	// Encoder
	private CANEncoder turretEncoder = turretRotate.getEncoder();

	// Booleans
	public boolean manualMode = true;

	// Network Tables
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");

	// Subsystems
	ShooterSubsystem shooterSubsystem = RobotContainer.shooterSubsystem;

	// read values periodically
	double x = tx.getDouble(0.0);
	double y = ty.getDouble(0.0);
	double area = ta.getDouble(0.0);

	private static int rainbowFirstPixelHue;
	private AddressableLED led = new AddressableLED(0);
	private static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(150);
	private static int timer = 0;

	public TurretSubsystem() {
		turretRotate.setIdleMode(IdleMode.kBrake);

		turretRotate.setSmartCurrentLimit(20);

		turretEncoder.setPosition(0);

		led.setLength(ledBuffer.getLength());

		led.setData(ledBuffer);
		led.start();

	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void updateLimelightTracking() {
		double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
		double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
		double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

	}

	public void turretSpin(double speed) {
		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
		NetworkTableEntry tx = table.getEntry("tx");
		double errorValue = tx.getDouble(0);
		NetworkTableEntry tv = table.getEntry("tv");
		int targetVisibility = 0;
		if (errorValue > -0.01 && errorValue < 0.01) {
			targetVisibility = 0;
		} else {
			targetVisibility = 1;
		}
		double targetCentered = tx.getDouble(0);
		double kp = 0.02;
		double min_speed = 0.05;
		double negativeErrorValue = -errorValue;
		double steeringAdjustment = 0.0;
		SmartDashboard.putBoolean("tv", tv.getBoolean(false));
		SmartDashboard.putNumber("tx", tx.getDouble(0));
		SmartDashboard.putNumber("Negative Error Value", negativeErrorValue);
		SmartDashboard.putNumber("Turret Encoder Position", turretEncoder.getPosition());
		SmartDashboard.putNumber("targetVisibility", targetVisibility);

		if (manualMode) {
			setTurretMotor(speed);
			// set LEDs

			if (targetVisibility == 1) {
				if (targetCentered >= -2.5 && targetCentered <= 2.5) {
					if (shooterSubsystem.atSetpoint()) {
						setLEDsSolidGreen();
						led.setData(ledBuffer);
					} else {
						setLEDsFlashGreen();
						led.setData(ledBuffer);
					}
				} else {
					if (shooterSubsystem.atSetpoint()) {
						if (targetCentered < -2.5) {
							setLEDsSolidBlueRight();
							led.setData(ledBuffer);
						} else if (targetCentered > 2.5) {
							setLEDsSolidBlueLeft();
							led.setData(ledBuffer);
						}
					} else {
						if (targetCentered < -2.5) {
							setLEDsFlashBlueRight();
							led.setData(ledBuffer);
						} else if (targetCentered > 2.5) {
							setLEDsFlashBlueleft();
							led.setData(ledBuffer);
						}
					}
				}
			} else {
				if (targetCentered >= -2.5 && targetCentered <= 2.5) {
				} else {
					if (shooterSubsystem.atSetpoint()) {
						setLEDsSolidOrange();
						led.setData(ledBuffer);
					} else {
						setLEDsOff();
						led.setData(ledBuffer);
					}
				}
			}
		} else {
			if (negativeErrorValue > 2.0) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setTurretMotor(-steeringAdjustment);
			} else if (negativeErrorValue < 1.0) {
				steeringAdjustment = kp * Math.abs(negativeErrorValue) + min_speed;
				setTurretMotor(steeringAdjustment);

			} else {
				setTurretMotor(0);
			}

			if (targetVisibility == 1) {
				if (targetCentered >= -2.5 && targetCentered <= 2.5) {
					if (shooterSubsystem.atSetpoint()) {
						setLEDsSolidGreen();
						led.setData(ledBuffer);
					} else {
						setLEDsFlashGreen();
						led.setData(ledBuffer);
					}
				} else {
					if (shooterSubsystem.atSetpoint()) {
						setLEDsSolidYellow();
						led.setData(ledBuffer);
					} else {
						setLEDsFlashYellow();
						led.setData(ledBuffer);
					}
				}
			} else {
				if (targetCentered >= -1 && targetCentered <= 1) {
				} else {
					if (shooterSubsystem.atSetpoint()) {
						setLEDsSolidRed();
						led.setData(ledBuffer);
					} else {
						setLEDsFlashRed();
						led.setData(ledBuffer);
					}
				}
			}
		}
	}

	public void turretCenter() {
		if(turretEncoder.getPosition() < 2.5 && turretEncoder.getPosition() > -2.5) {
			setTurretMotor(0);
		} else if (turretEncoder.getPosition() < -2.5) {
			setTurretMotor(1);
		} else if (turretEncoder.getPosition() > 2.5) {
			setTurretMotor(-1);
		}
}

	
	public void toggleManualMode() {
		if (manualMode) {
			manualMode = false;
		} else {
			manualMode = true;
		}
	}

	public void setLEDsOff() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0, 0, 0);
		}
	}

	public void setLEDsSolidOrange() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 30 / 2, 255, 255);
		}
	}

	public void setLEDsFlashBlueRight() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				// ledBuffer.setHSV(i, 0, 0, 0);
				if (i < ledBuffer.getLength() / 2) {
					ledBuffer.setHSV(i, 225 / 2, 255, 255);
				} else {
					ledBuffer.setHSV(i, 0 / 2, 0, 0);
				}
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0 / 2, 0, 0);
			}
		}
		if (timer >= 50) {
			timer = 0;
		}
	}

	public void setLEDsFlashBlueleft() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < (ledBuffer.getLength()); i++) {
				// ledBuffer.setHSV(i, 0, 0, 0);
				if (i > (ledBuffer.getLength() / 2)) {
					ledBuffer.setHSV(i, 225 / 2, 255, 255);
				} else {
					ledBuffer.setHSV(i, 0 / 2, 0, 0);
				}
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0 / 2, 0, 0);
			}
		}
		if (timer >= 50) {
			timer = 0;
		}
	}

	public void setLEDsSolidBlueRight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// ledBuffer.setHSV(i, 0, 0, 0);
			if (i < ledBuffer.getLength() / 2) {
				ledBuffer.setHSV(i, 225 / 2, 255, 255);
			} else {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		}
	}

	public void setLEDsSolidBlueLeft() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// ledBuffer.setHSV(i, 0, 0, 0);
			if (i > ledBuffer.getLength() / 2) {
				ledBuffer.setHSV(i, 225 / 2, 255, 255);
			} else {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		}
	}

	public void setLEDsFlashGreen() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 115 / 2, 255, 255);
			}
		}
		if (timer >= 50) {
			timer = 0;
		}
	}

	public void setLEDsSolidGreen() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 115 / 2, 255, 255);
		}
	}

	public void setLEDsFlashRed() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0 / 2, 255, 255);
			}
		}
		if (timer >= 50) {
			timer = 0;
		}
	}

	public void setLEDsSolidRed() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0 / 2, 255, 255);
		}
	}

	public void setLEDsFlashYellow() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 42 / 2, 255, 255);
			}
		}
		if (timer >= 50) {
			timer = 0;
		}
	}

	public void setLEDsSolidYellow() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 42 / 2, 255, 255);
		}
	}

	public void setTurretMotor(double speed) {
		if (turretEncoder.getPosition() < -Constants.TURRET_RIGHT_LIMIT && speed > 0 || turretEncoder.getPosition() > Constants.TURRET_LEFT_LIMIT && speed < 0) {
			turretRotate.set(0);
		} else {
			turretRotate.set(-speed);
		}
	}
}