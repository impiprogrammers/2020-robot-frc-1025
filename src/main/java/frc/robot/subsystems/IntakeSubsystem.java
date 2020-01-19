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
           public WPI_TalonSRX intakeMaster = new WPI_TalonSRX(Constants.intakeMasterPort);
           public WPI_TalonSRX intakeSlave = new WPI_TalonSRX(Constants.intakeSlavePort);
         
           public DifferentialDrive drive = new DifferentialDrive(intakeMaster, intakeSlave);
           
           public IntakeSubsystem() {
            intakeSlave.follow(intakeMaster);
            intakeSlave.configNominalOutputReverse(100);
            
           }
           // this is manualDrive() method
           public void manualIntake(double pull, double spin) {
            drive.arcadeDrive(pull,0);
           }
           
           @Override
           public void periodic() {
             // This method will be called once per scheduler run
             setDefaultCommand(new IntakeManuallyCommand());


           }
        }