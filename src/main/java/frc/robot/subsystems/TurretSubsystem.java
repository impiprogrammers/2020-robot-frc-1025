/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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

  // Motor Controller
  private static CANSparkMax turretRotate = new CANSparkMax(Constants.TURRET_ROTATE_PORT, MotorType.kBrushless);
  // PID Controller
  private CANPIDController turretPID = turretRotate.getPIDController();

  private CANEncoder turretEncoder = turretRotate.getEncoder();

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

    turretRotate.setIdleMode(IdleMode.kBrake);

    turretRotate.setSmartCurrentLimit(20);

    led.setLength(ledBuffer.getLength());

    led.setData(ledBuffer);
    led.start();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void turretSpin(double speed) {
    turretRotate.set(speed);
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
      }
    }
  }

  public static void ToggleLimelightLock() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double errorValue = tx.getDouble(0);
    double Krip = -0.1;
    double min_speed = 0.05;
    double negativeErrorValue = -errorValue;
    double steeringAdjustment = 0.0;

    if (-errorValue > 1.05) {
      steeringAdjustment = Krip * negativeErrorValue - min_speed;
      turretRotate.set(-steeringAdjustment);
    }

    else if (-errorValue < 0.95) {
      steeringAdjustment = Krip * negativeErrorValue + min_speed;
      turretRotate.set(steeringAdjustment);
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

  public static void SetLEDsFlashBlueOneSide() {
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

  public static void SetLEDsSolidBlueOneSide() {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      // ledBuffer.setHSV(i, 0, 0, 0);
      if (i < ledBuffer.getLength()/2) {
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