package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterFeederSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  TalonSRX shooterFeederRoller = new TalonSRX(Constants.shooterFeederPort);

  public ShooterFeederSubsystem() {

    shooterFeederRoller.set(ControlMode.PercentOutput, 0);

    shooterFeederRoller.configFactoryDefault();
    shooterFeederRoller.setNeutralMode(NeutralMode.Brake);


  }
  public void feedShooter() {
      shooterFeederRoller.set(ControlMode.PercentOutput, 1.0);

  }
  public void stop() {
    shooterFeederRoller.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}








































