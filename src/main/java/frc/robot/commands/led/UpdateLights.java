package frc.robot.commands.led;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.*;

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

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
		// Change the LED to either blinking or solid
		if (shooterSubsystem.isShooterEnabled() && shooterSubsystem.isShooterReady()) {
			ledSubsystem.setSolid();
		} else {
			ledSubsystem.setBlinking();
		}

        if (turretSubsystem.isManualMode()) {
            if (turretSubsystem.canLimelightSeeTarget()) {
                if (turretSubsystem.getLimelightHorizontalOffset() > Constants.Turret.MAX_ANGLE_ERROR) {
                    ledSubsystem.setRGB(0, 0, 255); // blue
                    ledSubsystem.turnOn(false, true);
                } else if (turretSubsystem.getLimelightHorizontalOffset() < -Constants.Turret.MAX_ANGLE_ERROR) {
                    ledSubsystem.setRGB(0, 0, 255); // blue
                    ledSubsystem.turnOn(true, false);
                } else {
                    ledSubsystem.setRGB(0, 255, 0); // green
                    ledSubsystem.turnOn();
                }
            } else {
                ledSubsystem.setRGB(255, 165, 0); // orange
                ledSubsystem.turnOn();
            }
        }
    
    }

    @Override
    public void end(boolean interrupted) {
        ledSubsystem.turnOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
