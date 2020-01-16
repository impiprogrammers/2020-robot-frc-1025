    package frc.robot.subsystems;

    import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    import edu.wpi.first.wpilibj.SpeedController;
    import edu.wpi.first.wpilibj.SpeedControllerGroup;
    import edu.wpi.first.wpilibj.drive.DifferentialDrive;
    import edu.wpi.first.wpilibj2.command.SubsystemBase;
    import frc.robot.Constants;
    import frc.robot.RobotContainer;
    import frc.robot.commands.*;

        public class IntakeSubsystem extends SubsystemBase {
          /**
            * Creates a new ChasisSubsystem.
            */
           public WPI_TalonSRX intakeArm = new WPI_TalonSRX(Constants.intakeArmPort);
           public WPI_TalonSRX intakePull = new WPI_TalonSRX(Constants.intakePullPort);
         
           public IntakeSubsystem() {
           }
           // this is manualDrive() method
           public void manualDrive(double move, double turn) {
           }
         
           @Override
           public void periodic() {
             // This method will be called once per scheduler run
             setDefaultCommand(new DriveManuallyCommand());
           }
        }