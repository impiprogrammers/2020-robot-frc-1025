/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.led;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
		double xOffset = turretSubsystem.getXOffset();
		int shooterStatus = 0;
		double shooterVelocity = Math.abs(shooterSubsystem.getShooterVelocity());
		SmartDashboard.putNumber("Shooter Velocity", shooterVelocity);
		if (shooterVelocity > 100) {
			shooterStatus = 1;
		} else {
			shooterStatus = 0;
		}

		if (turretSubsystem.isAutoReady()) {

			// set LEDs Bottom (Auto)
			if (turretSubsystem.isTargetCentered()) {
				if (xOffset >= 1 && xOffset <= 2) {
					ledSubsystem.setLEDsBottom(115 / 2, 255, 255); // green
				} else {
					if (xOffset < 1) {
						if (turretSubsystem.turretAtLeftSoftStop()) {
							ledSubsystem.setLEDsBottomRight(30 / 2, 255, 255); // orange
						} else {
							ledSubsystem.setLEDsBottom(42 / 2, 255, 255); // yellow
						}
					} else if (xOffset > 2) {
						if (turretSubsystem.turretAtRightSoftStop()) {
							ledSubsystem.setLEDsBottomLeft(30 / 2, 255, 255); // orange
						} else {
							ledSubsystem.setLEDsBottom(42 / 2, 255, 255); // yellow
						}
					}
				}
			} else {
				ledSubsystem.setLEDsBottom(0 / 2, 255, 255); // red
			}

			
		} else {
			
			// set LEDs

			// set Bottom LEDs (Manual)
			if (turretSubsystem.isTargetCentered()) {
				if (xOffset >= 1 && xOffset <= 2) {
					ledSubsystem.setLEDsBottom(115 / 2, 255, 255); // green
				} else {
					if (xOffset < 1) {
						if (turretSubsystem.turretAtLeftSoftStop()) {
							ledSubsystem.setLEDsBottomRight(30 / 2, 255, 255); // orange
						} else {
							ledSubsystem.setLEDsBottomRight(42 / 2, 255, 255); // yellow
						}
					} else if (xOffset > 2) {
						if (turretSubsystem.turretAtRightSoftStop()) {
							ledSubsystem.setLEDsBottomLeft(30 / 2, 255, 255); // orange
						} else {
							ledSubsystem.setLEDsBottomLeft(42 / 2, 255, 255); // yellow
						}
					}
				}
			} else {
				ledSubsystem.setLEDsBottom(0, 0, 0); // off
			}
		}

		// set Top LEDs
		if (shooterStatus == 1) {
			if (shooterVelocity >= 4500) {
				ledSubsystem.setLEDsTop(115 / 2, 255, 255); // green
			} else {
				ledSubsystem.setLEDsTop(42 / 2, 255, 255); // yellow
			}
		} else {
			ledSubsystem.setLEDsTop(0 / 2, 0, 0); // off
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
