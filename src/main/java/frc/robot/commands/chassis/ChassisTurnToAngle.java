/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.chassis;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisTurnToAngle extends CommandBase {
  private final ChassisSubsystem chassisSubsystem;
  private double angle;
  private double speed;
  private final double allowableError = 5.;

  /**
   * Creates a new ChassisTurnToAngle.
   */
  public ChassisTurnToAngle(ChassisSubsystem chassisSubsystem, double angle, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.angle = angle;
    this.chassisSubsystem = chassisSubsystem;
    addRequirements(chassisSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    chassisSubsystem.resetGyro();
    chassisSubsystem.setBrakeMode();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (angle > 0.) {
      chassisSubsystem.arcadeDrive(0, -speed);
    } else if (angle < 0.) {
      chassisSubsystem.arcadeDrive(0, speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassisSubsystem.stopChassis();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if ((Math.abs(angle) >= Math.abs(chassisSubsystem.getAngle() - allowableError)
        && (Math.abs(angle) < Math.abs(chassisSubsystem.getAngle()) + allowableError))) {
      return true;
    } else {
      return false;
    }
  }

}
