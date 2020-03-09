/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.paths;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.auto.AutoIntakeOff;
import frc.robot.commands.auto.AutoIntakeOn;
import frc.robot.commands.chassis.ChassisDriveDistance;
import frc.robot.commands.chassis.ChassisTurnToAngle;
import frc.robot.commands.conveyor.ConveyorRollCosineAuto;
import frc.robot.commands.conveyor.ConveyorSetAuto;
import frc.robot.commands.intake.IntakeRollersSetAuto;
import frc.robot.commands.shooter.ShooterSetAuto;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter_feeder.ShooterFeederSetAuto;
import frc.robot.commands.turret.TurretSpinToAngle;
import frc.robot.commands.turret.TurretTrackTarget;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoTrench2 extends SequentialCommandGroup {
  /**
   * Creates a new AutoTrench2.
   */
  public AutoTrench2(ChassisSubsystem chassisSubsystem, IntakeSubsystem intakeSubsystem,
  ConveyorSubsystem conveyorSubsystem, ShooterFeederSubsystem shooterFeederSubsystem,
  ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
    new ConveyorSetAuto(conveyorSubsystem, 0),
    new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
    new AutoIntakeOn(intakeSubsystem),
    new ShooterSetAuto(shooterSubsystem, 3500),
    new TurretSpinToAngle(turretSubsystem, 70),
    new ChassisDriveDistance(chassisSubsystem, 2.7432, 0.75), // 108 inches
    new ChassisDriveDistance(chassisSubsystem, 0.9,-.7),
    new ParallelRaceGroup(
      new TurretTrackTarget(turretSubsystem),
      new SequentialCommandGroup(
        new WaitCommand(0.5),
		///////////////new TurretTurnToTarget(turretSubsystem),
		
        new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
    	new ConveyorSetAuto(conveyorSubsystem, .7),
		new WaitCommand(4),
	//	new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
	//	new ConveyorRollCosineAuto(conveyorSubsystem, 0.75, 0.25, .5, 1.5),
        // new ShooterStop(shooterSubsystem)
        new ShooterFeederSetAuto(shooterFeederSubsystem, 0),

                  // Part 2
            new ConveyorSetAuto(conveyorSubsystem, .5),
            // new TurretSetManualMode(turretSubsystem, true),
             new ShooterSetAuto(shooterSubsystem, 3500),
            // new TurretSetManualMode(turretSubsystem, true),
              // new TurretSpinToAngle(turretSubsystem, 70),
                  new ChassisDriveDistance(chassisSubsystem, 2.55, .6), // 72 inches
                  //
                  new WaitCommand(.1),
                  new ChassisDriveDistance(chassisSubsystem, 2.45 , -.75),
                 // new ChassisTurnToAngle(chassisSubsystem, 10 , .4),
				  new WaitCommand(.1),
				  new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
				  new ConveyorSetAuto(conveyorSubsystem, 1),
				  new WaitCommand(2.5)
                 // new ConveyorRollCosineAuto(conveyorSubsystem, 0.75, 0.25, 2, 8)
      )
      

     //     ),
    //       new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
    //       new ConveyorSetAuto(conveyorSubsystem, 0),
    //       new ChassisDriveDistance(chassisSubsystem, 1.3, .7),
    // // new AutoIntakeOff(intakeSubsystem),
    //       // new TurretSetManualMode(turretSubsystem, false),
    //       new ConveyorSetAuto(conveyorSubsystem, 1),
    //       new ShooterSetAuto(shooterSubsystem, turretSubsystem.calcRPM()),
    //       new ChassisDriveDistance(chassisSubsystem, 2.8, -0.7),
    //       new WaitCommand(0.2),
    // new ParallelRaceGroup(
    //   new TurretTrackTarget(turretSubsystem),
    //   new SequentialCommandGroup(
    //     // new TurretTurnToTarget(turretSubsystem),
    //     // new IntakeRollersSetAuto(intakeSubsystem, 0.6),
    //     new ShooterFeederSetAuto(shooterFeederSubsystem, 1),
    //     // new ConveyorSetAuto(conveyorSubsystem, 0.8), 
    //     new WaitCommand(3),
    //     new ConveyorSetAuto(conveyorSubsystem, 0),
    //     new ShooterFeederSetAuto(shooterFeederSubsystem, 0),
    //     new ShooterStop(shooterSubsystem), new AutoIntakeOff(intakeSubsystem)
      ),
      new ParallelRaceGroup(
      new IntakeRollersSetAuto(intakeSubsystem , 1.),
      new ChassisDriveDistance(chassisSubsystem , 3 , .7),
      new WaitCommand(6)
      )
    );
 // );
}
}