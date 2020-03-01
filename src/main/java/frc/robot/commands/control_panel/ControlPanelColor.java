/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_panel;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanelColor extends CommandBase {
  private final ControlPanelSubsystem controlPanelSubsystem;
  private final Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  String colorString;

  public ControlPanelColor(ControlPanelSubsystem controlPanelSubsystem) {
    this.controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    colorString = DriverStation.getInstance().getGameSpecificMessage();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (colorString.length() > 0) {
      switch (colorString.charAt(0)) {
      case 'B':
          if (controlPanelSubsystem.getCurrentColor() != BlueTarget) {
              controlPanelSubsystem.controlPanelManual(1.0);
          }
          break;
      case 'G':
          if (controlPanelSubsystem.getCurrentColor() != GreenTarget) {
            controlPanelSubsystem.controlPanelManual(1.0);
          }
          break;
      case 'R':
          if (controlPanelSubsystem.getCurrentColor() != RedTarget) {
            controlPanelSubsystem.controlPanelManual(1.0);
          }
          break;
      case 'Y':
          if (controlPanelSubsystem.getCurrentColor() != YellowTarget) {
            controlPanelSubsystem.controlPanelManual(1.0);
          }
          break;
      default:
          // This is corrupt data
          break;
      }
  } else {
    controlPanelSubsystem.controlPanelManual(0.0);
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (colorString.length() == 0) || (controlPanelSubsystem.getCurrentColor() == controlPanelSubsystem.getFMSColor());
  }
}
