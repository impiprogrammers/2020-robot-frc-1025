/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.ColorSensorV3;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelSubsystem extends SubsystemBase {

    // Motor Controllers
    private final TalonSRX controlPanelWheel = new TalonSRX(Constants.CAN.CONTROL_PANEL_WHEEL_PORT);

    // Solenoids
    private final Solenoid controlPanelArm = new Solenoid(Constants.CAN.PCM_MODULE_PORT, Constants.PCM.CONTROL_PANEL_PISTON_CHANNEL);

    // i2c Port
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    // Color Sensor
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch colorMatcher = new ColorMatch();
    private final Color red = ColorMatch.makeColor(Constants.ControlPanel.red[0] , Constants.ControlPanel.red[1] , Constants.ControlPanel.red[2]);;
    private final Color blue = ColorMatch.makeColor(Constants.ControlPanel.blue[0] , Constants.ControlPanel.blue[1] , Constants.ControlPanel.blue[2]);
    private final Color green = ColorMatch.makeColor(Constants.ControlPanel.green[0] , Constants.ControlPanel.green[1] , Constants.ControlPanel.green[2]);
    private final Color yellow = ColorMatch.makeColor(Constants.ControlPanel.yellow[0] , Constants.ControlPanel.yellow[1] , Constants.ControlPanel.yellow[2]);;
   
    private String currentColorString;

    public ControlPanelSubsystem() {
        controlPanelWheel.configFactoryDefault();
        setBrakeMode();
        colorMatcher.addColorMatch(blue);
        colorMatcher.addColorMatch(green);
        colorMatcher.addColorMatch(red);
        colorMatcher.addColorMatch(yellow);
    }

    @Override
    public void periodic() {
         
    }

    public String getCurrentColorString(Color color) {
        if (color == blue) {
            currentColorString = "blue";
          } else if (color == red) {
            currentColorString = "red";
          } else if (color == green) {
            currentColorString = "green";
          } else if (color == yellow) {
            currentColorString = "yellow";
          } else {
            currentColorString = "unknown";
          }
          return currentColorString;
    }

    public String getOffsetColorString(Color color) {
        if (color == blue) {
            currentColorString = "red";
          } else if (color == red) {
            currentColorString = "blue";
          } else if (color == green) {
            currentColorString = "yellow";
          } else if (color == yellow) {
            currentColorString = "green";
          } else {
            currentColorString = "unknown";
          }
          return currentColorString;
    }

    public ColorSensorV3 getColorSensor(){
        return colorSensor;

    }

    public ColorMatch getColorMatch(){
        return colorMatcher;
    }

    public void controlPanelArmExtend() {
        controlPanelArm.set(true);
    }

    public void controlPanelArmRetract() {
        controlPanelArm.set(false);
    }

     public void controlPanelSpin(double speed){
         controlPanelWheel.set(ControlMode.PercentOutput, speed);
     }

     public void controlPanelArmToggle(){
        controlPanelArm.set(!controlPanelArm.get());
     }
     

     public void setBrakeMode(){
         controlPanelWheel.setNeutralMode(NeutralMode.Brake);
     }

     public void setCoastMode(){
         controlPanelWheel.setNeutralMode(NeutralMode.Coast);
     }
    

    public void controlPanelStop() {
      controlPanelWheel.set(ControlMode.PercentOutput, 0.0);
    }


}

// revisit this later(SmartDash)
/*
 * if (match.color == kBlueTarget) { colorString = "Blue"; } else if
 * (match.color == kRedTarget) { colorString = "Red"; } else if (match.color ==
 * kGreenTarget) { colorString = "Green"; } else if (match.color ==
 * kYellowTarget) { colorString = "Yellow"; } else { colorString = "Unknown"; }
 */