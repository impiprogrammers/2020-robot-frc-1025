/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class UpdateLights extends CommandBase {

	private final LEDSubsystem ledSubsystem;
	private final ShooterSubsystem shooterSubsystem;
	private final TurretSubsystem turretSubsystem;

	private final Timer timer = new Timer();

	public UpdateLights(LEDSubsystem ledSubsystem, ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
		this.ledSubsystem = ledSubsystem;
		this.shooterSubsystem = shooterSubsystem;
		this.turretSubsystem = turretSubsystem;
		addRequirements(ledSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {

		double xOffset = turretSubsystem.getXOffset();

		if (turretSubsystem.isModeAuto()) {
			if (turretSubsystem.isTargetFound()) {
				if (turretSubsystem.isTargetCentered()) {
					ledSubsystem.setLEDsBottom(115 / 2, 255, 255); // green
				} else {
					if (xOffset < 0) {
						if (turretSubsystem.turretAtLeftSoftStop()) {
							ledSubsystem.setLEDsBottomLeft(255, 255, 255); // magenta
							ledSubsystem.setLEDsBottomRight(0, 0, 0);
						} else {
							ledSubsystem.setLEDsBottom(42 / 2, 255, 255); // yellow
						}
					} else if (xOffset > 0) {
						if (turretSubsystem.turretAtRightSoftStop()) {
							ledSubsystem.setLEDsBottomRight(255, 255, 255); // magenta
							ledSubsystem.setLEDsBottomLeft(0, 0, 0);
						} else {
							ledSubsystem.setLEDsBottom(42 / 2, 255, 255); // yellow
						}
					}
				}
			} else {
				ledSubsystem.setLEDsBottom(0 / 2, 255, 255); // red
			}
		} else {
			// set Bottom LEDs (Manual)
			if (turretSubsystem.isTargetFound()) {
				if (turretSubsystem.isTargetCentered()) {
					ledSubsystem.setLEDsBottom(115 / 2, 255, 255); // green
				} else {
					if (xOffset < 0) {
						if (turretSubsystem.turretAtLeftSoftStop()) {
							ledSubsystem.setLEDsBottomLeft(30 / 2, 255, 255); // orange
							ledSubsystem.setLEDsBottomRight(0, 0, 0);
						} else {
							ledSubsystem.setLEDsBottomLeft(42 / 2, 255, 255); // yellow
							ledSubsystem.setLEDsBottomRight(0, 0, 0);
						}
					} else if (xOffset > 0) {
						if (turretSubsystem.turretAtRightSoftStop()) {
							ledSubsystem.setLEDsBottomRight(30 / 2, 255, 255); // orange
							ledSubsystem.setLEDsBottomLeft(0, 0, 0);
						} else {
							ledSubsystem.setLEDsBottomRight(42 / 2, 255, 255); // yellow
							ledSubsystem.setLEDsBottomLeft(0, 0, 0);
						}
					}
				}
			} else {
				ledSubsystem.setLEDsBottom(0, 255, 255); // red
			}
		}

		// set Top LEDs
		double shooterVelocity = Math.abs(shooterSubsystem.getShooterVelocity());

		if (shooterVelocity > 100) {
			if (shooterVelocity >= 4000) {
				ledSubsystem.setLEDsTop(115 / 2, 255, 255); // green
			} else {
				ledSubsystem.setLEDsTop(42 / 2, 255, 255); // yellow
			}
		} else {
			ledSubsystem.setLEDsTop(0, 0, 0); // off
		}

		if (timer.get() > 0.1) {
			// ledSubsystem.setLEDsTop(115 / 2, 255, 255); // green
			ledSubsystem.setLEDBuffer();
			timer.reset();
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		ledSubsystem.setLEDsOff();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
