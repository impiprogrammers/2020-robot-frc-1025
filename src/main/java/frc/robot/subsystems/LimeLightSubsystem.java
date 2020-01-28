package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.*;
import frc.robot.commands.shooter.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightSubsystem extends SubsystemBase{
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

}
