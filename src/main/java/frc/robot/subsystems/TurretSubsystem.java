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
import frc.robot.commands.turret.TurretSpin;
import frc.robot.commands.turret.ToggleLimelightLock;

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

public void Update_Limelight_Tracking()
{
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

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void turretSpin(double speed) {
    turretRotate.set(speed);
}


  public static void ToggleLimelightLock(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double errorValue = tx.getDouble(0);
    double Krip = -0.1;
    double min_speed = 0.05;
    double negativeErrorValue = -errorValue;
    double steeringAdjustment = 0.0;

    if ( -errorValue > 1.05 ) {
      steeringAdjustment = Krip*negativeErrorValue - min_speed;
      turretRotate.set(-steeringAdjustment);
    }

    else if (-errorValue < 0.95) {
        steeringAdjustment = Krip*negativeErrorValue + min_speed;
        turretRotate.set(steeringAdjustment);
    }


  }
}
     //   \_(0o0)_/ bruh you got the whole squad laughing
     /*______________________________________________*\
     /* bruh k feller                                                                        *\
     /* you got the whol squad laughing                                       *\
     /*______________________________________________*\
