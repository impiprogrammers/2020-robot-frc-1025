/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter_feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class ShooterFeederStop extends CommandBase {
  private final ShooterFeederSubsystem m_shooterFeeder = new ShooterFeederSubsystem();

  public ShooterFeederStop() {
    addRequirements(m_shooterFeeder);
  }
 
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_shooterFeeder.stop();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
