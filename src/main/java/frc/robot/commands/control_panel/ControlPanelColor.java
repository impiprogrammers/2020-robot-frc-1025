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
  private final Color Blue = ColorMatch.makeColor(Constants.ControlPanel.blue[0] , Constants.ControlPanel.blue[1] , Constants.ControlPanel.blue[2]);
  private final Color Green = ColorMatch.makeColor(Constants.ControlPanel.green[0] , Constants.ControlPanel.green[1] , Constants.ControlPanel.green[2]);
  private final Color Red = ColorMatch.makeColor(Constants.ControlPanel.red[0] , Constants.ControlPanel.red[1] , Constants.ControlPanel.red[2]);
  private final Color Yellow = ColorMatch.makeColor(Constants.ControlPanel.yellow[0] , Constants.ControlPanel.yellow[1] , Constants.ControlPanel.yellow[2]);
  String colorString;

  public ControlPanelColor(ControlPanelSubsystem controlPanelSubsystem) {
    this.controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   colorString = DriverStation.getInstance().getGameSpecificMessage();
   // colorString = "Blue";
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (colorString.length() > 0) {
      switch (colorString.charAt(0)) {
      case 'B':
          if (controlPanelSubsystem.getCurrentColorString() != "blue") {
              controlPanelSubsystem.controlPanelSpin(0.5);
          }
          break;
      case 'G':
          if (controlPanelSubsystem.getCurrentColorString() != "green") {
            controlPanelSubsystem.controlPanelSpin(0.5);
          }
          break;
      case 'R':
          if (controlPanelSubsystem.getCurrentColorString() != "red") {
            controlPanelSubsystem.controlPanelSpin(0.5);
          }
          break;
      case 'Y':
          if (controlPanelSubsystem.getCurrentColorString() != "yellow" ) {
            controlPanelSubsystem.controlPanelSpin(0.5);
          }
          break;
      default:
          // This is corrupt data
          break;
      }
  } else {
    controlPanelSubsystem.controlPanelSpin(0.0);
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.controlPanelStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (colorString.length() == 0) || (controlPanelSubsystem.getCurrentColorString().equalsIgnoreCase(colorString));
  }
}
