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
import frc.robot.commands.turret.TurretRotate;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TurretSubsystem extends SubsystemBase {

  // Motor Controller
  private static CANSparkMax turretRotate = new CANSparkMax(Constants.TURRET_ROTATE_PORT, MotorType.kBrushless);

  // PID Controller
  private CANPIDController turretPID = turretRotate.getPIDController();

  private CANEncoder turretEncoder = turretRotate.getEncoder();

  /**
   * Creates a new TurretSubsystem.
   */
  public TurretSubsystem() {

    turretRotate.setIdleMode(IdleMode.kBrake);

    turretRotate.setSmartCurrentLimit(20);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void turretSpin(double speed) {
    turretRotate.set(speed);
}
}
