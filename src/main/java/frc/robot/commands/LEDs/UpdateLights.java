/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.LEDs;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class UpdateLights extends CommandBase {

  private final LEDSubsystem ledSubsystem;
  private final ShooterSubsystem shooterSubsystem;
  private final TurretSubsystem turretSubsystem;
  
  public UpdateLights(LEDSubsystem ledSubsystem, ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
    this.ledSubsystem = ledSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    this.turretSubsystem = turretSubsystem;
    
    addRequirements(ledSubsystem);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  double errorValue = tx.getDouble(0);
  NetworkTableEntry tv = table.getEntry("tv");
  int targetVisibility = 0;
  if (errorValue > -0.01 && errorValue < 0.01) {
    targetVisibility = 0;
  } else {
    targetVisibility = 1;
  }
  double targetCentered = tx.getDouble(0);
    if (turretSubsystem.manualMode) {
    
      // set LEDs
  
      if (targetVisibility == 1) {
        if (targetCentered >= -2.5 && targetCentered <= 2.5) {
          if (shooterSubsystem.atSetpoint()) {
            ledSubsystem.setLEDsSolidGreen();
          } else {
            ledSubsystem.setLEDsFlashGreen();
          }
        } else {
          if (shooterSubsystem.atSetpoint()) {
            if (targetCentered < -2.5) {
              ledSubsystem.setLEDsSolidBlueRight();
            } else if (targetCentered > 2.5) {
              ledSubsystem.setLEDsSolidBlueLeft();
            }
          } else {
            if (targetCentered < -2.5) {
              ledSubsystem.setLEDsFlashBlueRight();
            } else if (targetCentered > 2.5) {
              ledSubsystem.setLEDsFlashBlueleft();
            }
          }
        }
      } else {
        if (targetCentered >= -2.5 && targetCentered <= 2.5) {
        } else {
          if (shooterSubsystem.atSetpoint()) {
            ledSubsystem.setLEDsSolidOrange();
          } else {
            ledSubsystem.setLEDsOff();
          }
        }
      }
    } else {
  
      if (targetVisibility == 1) {
        if (targetCentered >= -2.5 && targetCentered <= 2.5) {
          if (shooterSubsystem.atSetpoint()) {
            ledSubsystem.setLEDsSolidGreen();
          } else {
            ledSubsystem.setLEDsFlashGreen();
          }
        } else {
          if (shooterSubsystem.atSetpoint()) {
            ledSubsystem.setLEDsSolidYellow();
          } else {
            ledSubsystem.setLEDsFlashYellow();
          }
        }
      } else {
        if (targetCentered >= -1 && targetCentered <= 1) {
        } else {
          if (shooterSubsystem.atSetpoint()) {
            ledSubsystem.setLEDsSolidRed();
          } else {
            ledSubsystem.setLEDsFlashRed();
          }
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
