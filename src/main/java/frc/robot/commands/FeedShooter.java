package frc.robot.commands;
//BRUH!!!!!
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class FeedShooter extends CommandBase{

    private final ShooterFeederSubsystem m_shooterFeeder;

    public FeedShooter(ShooterFeederSubsystem shooterFeeder) {
        m_shooterFeeder = shooterFeeder;
        addRequirements(m_shooterFeeder);
    }

    @Override
    public void initialize() {
        m_shooterFeeder.feedShooter();
    }

    @Override
    public void execute() {
        m_shooterFeeder.feedShooter();
    }

    @Override
    public boolean isFinished() {
        return false;
    }







}