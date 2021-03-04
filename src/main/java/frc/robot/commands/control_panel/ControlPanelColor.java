/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_panel;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelSubsystem;

public class ControlPanelColor extends CommandBase {
  private final ControlPanelSubsystem controlPanelSubsystem;
  
  String colorString;
  private Color detectedColor;
  private ColorMatchResult match;

  public ControlPanelColor(ControlPanelSubsystem controlPanelSubsystem) {
    this.controlPanelSubsystem = controlPanelSubsystem;
    addRequirements(controlPanelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   colorString = DriverStation.getInstance().getGameSpecificMessage();
   detectedColor = controlPanelSubsystem.getColorSensor().getColor();
   match = controlPanelSubsystem.getColorMatch().matchClosestColor(detectedColor);
   controlPanelSubsystem.controlPanelSpin(0.5);
        // SmartDashboard.putString("Color Sensor Detected Color (0-255)", detectedColor.red * 255 + ", " + detectedColor.green * 255 + ", " + detectedColor.blue * 255);
        // SmartDashboard.putString("Color Sensor Detected Color (0-1)", detectedColor.red + ", " + detectedColor.green + ", " + detectedColor.blue);
        // SmartDashboard.putString("Current Color String" , controlPanelSubsystem.getCurrentColorString(match.color)); 
   // colorString = "Blue";
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    detectedColor = controlPanelSubsystem.getColorSensor().getColor();
    match = controlPanelSubsystem.getColorMatch().matchClosestColor(detectedColor);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controlPanelSubsystem.controlPanelStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (colorString.length() == 0) || (controlPanelSubsystem.getOffsetColorString(match.color).equalsIgnoreCase(colorString));
  }
}
