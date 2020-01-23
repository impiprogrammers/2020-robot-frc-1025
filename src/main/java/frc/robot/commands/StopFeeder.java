/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterFeederSubsystem;

public class StopFeeder extends CommandBase {
  private final ShooterFeederSubsystem m_shooterFeeder;
 
  public StopFeeder(ShooterFeederSubsystem shooterFeeder) {
    m_shooterFeeder = shooterFeeder;
    addRequirements(m_shooterFeeder);
  }
 
  @Override
  public void initialize() {
    m_shooterFeeder.stopFeeder();
  }

  @Override
  public void execute() {
    m_shooterFeeder.stopFeeder();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
