package frc.robot.commands.shooter_feeder;

import java.util.function.IntSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederSpin extends CommandBase {

    private final ShooterFeederSubsystem shooterFeederSubsystem;
    private final IntSupplier povAngle;

    public ShooterFeederSpin(ShooterFeederSubsystem shooterFeederSubsystem, IntSupplier povAngle) {
        this.shooterFeederSubsystem = shooterFeederSubsystem;
        this.povAngle = povAngle;
        addRequirements(shooterFeederSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (povAngle.getAsInt() == 0) {
            shooterFeederSubsystem.spin(1);
        } else if (povAngle.getAsInt() == 180) {
            shooterFeederSubsystem.spin(-1);
        } else {
            shooterFeederSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}