package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederSpin extends CommandBase {

    ShooterFeederSubsystem shooterFeederSubsystem = RobotContainer.shooterFeederSubsystem;
    XboxController buttonsController = RobotContainer.buttonsController;

    public ShooterFeederSpin() {
        addRequirements(shooterFeederSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        shooterFeederSubsystem.spin(ImpiLib2020.parseTrigger(buttonsController.getTriggerAxis(Hand.kRight), 0.05));
    }

    @Override
    public boolean isFinished() {
        return false;
    }







}