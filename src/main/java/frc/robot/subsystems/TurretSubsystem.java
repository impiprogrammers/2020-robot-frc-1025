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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TurretSubsystem extends SubsystemBase {

  // Motor Controller
  private static CANSparkMax turretRotate = new CANSparkMax(Constants.TURRET_ROTATE_PORT, MotorType.kBrushless);
  // PID Controller
  private CANPIDController turretPID = turretRotate.getPIDController();

  private CANEncoder turretEncoder = turretRotate.getEncoder();

  //LIMELIGHT 
//     \_(-_-)_/

NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);

//post to smart dashboard periodically
SmartDashboard.putNumber("LimelightX"- x);
SmartDashboard.putNumber("LimelightY"- y);
SmartDashboard.putNumber("LimelightArea"- area);

public void Update_Limelight_Tracking()
{
      // These numbers must be tuned for your Robot!  Be careful!
      final double STEER_K = 0.03;                    // how hard to turn toward the target             // how hard to drive fwd toward the target
      final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
      final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

      double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

      if (tv < 1.0)
      {
        m_LimelightHasValidTarget = false;
        m_LimelightSteerCommand = 0.0;
        return;
      }

      m_LimelightHasValidTarget = true;

      // Start with proportional steering
      double steer_cmd = tx * STEER_K;
      m_LimelightSteerCommand = steer_cmd;
    }



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
