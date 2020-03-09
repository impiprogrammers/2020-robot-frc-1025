/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.chassis.ChassisDriveDistance;
import frc.robot.commands.conveyor.ConveyorSetAuto;
import frc.robot.commands.shooter_feeder.ShooterFeederSetAuto;
import frc.robot.commands.turret.TurretSpinToAngle;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoInitiationLine extends SequentialCommandGroup {
  /**
   * Creates a new AutoInitiationLine.
   */
  public AutoInitiationLine(ChassisSubsystem chassisSubsystem, IntakeSubsystem intakeSubsystem,
  ConveyorSubsystem conveyorSubsystem, ShooterFeederSubsystem shooterFeederSubsystem,
  ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
    super(
    new ConveyorSetAuto(conveyorSubsystem, 0),
    new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
    new TurretSpinToAngle(turretSubsystem, 90),
    new ChassisDriveDistance(chassisSubsystem, 1.5, 0.75),
    new WaitCommand(3)
    );
  }
}
