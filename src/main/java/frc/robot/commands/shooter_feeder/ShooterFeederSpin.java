package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederSpin extends CommandBase {

    ShooterFeederSubsystem shooterFeeder = RobotContainer.shooterFeederSubsystem;

    public ShooterFeederSpin() {
        addRequirements(shooterFeeder);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        XboxController buttonsController = RobotContainer.buttonsController;
        shooterFeeder.spin(Math.pow(ImpiLib2020.deadzone(buttonsController.getTriggerAxis(Hand.kRight), 0.05), 2));
    }

    @Override
    public boolean isFinished() {
        return false;
    }







}