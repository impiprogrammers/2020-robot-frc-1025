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
import com.revrobotics.ColorSensorV3.RawColor;
import com.revrobotics.ColorMatchResult;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelSubsystem extends SubsystemBase {

    // Motor Controllers
    TalonSRX controlPanelWheel = new TalonSRX(Constants.CONTROL_PANEL_WHEEL_PORT);

    // Solenoids
    Solenoid controlPanelArm = new Solenoid(Constants.PCM_MODULE_PORT, Constants.CONTROL_PANEL_PISTON_CHANNEL);

    // i2c Port
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    // Color Sensor
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    int colorTracker = 0;
    boolean currentlyRed;
    Color detectedColor;
    ColorMatchResult match;
    String colorString;
    double turn;

    public ControlPanelSubsystem() {
        controlPanelWheel.configFactoryDefault();
        setBrakeMode();
        turn = 0.75;
    }

    @Override
    public void periodic() {
        colorString = DriverStation.getInstance().getGameSpecificMessage();
        detectedColor = colorSensor.getColor();
        match = colorMatcher.matchClosestColor(detectedColor);
        SmartDashboard.putString("Color Sensor Detected Color (0-255)",
                detectedColor.red * 255 + ", " + detectedColor.green * 255 + ", " + detectedColor.blue * 255);
    }

    public void controlPanelArmExtend() {
        controlPanelArm.set(true);
    }

    public void controlPanelArmRetract() {
        controlPanelArm.set(false);
    }

    public void controlPanelWheelSpinFour() {
        if (colorTracker < 8) {
            controlPanelWheel.set(ControlMode.PercentOutput, 1.0);
            if (match.color == kRedTarget) {
                if (!currentlyRed) {
                    colorTracker += 1;
                    currentlyRed = true;
                }
            } else if (currentlyRed) {
                currentlyRed = false;
            }

        } else {
            controlPanelWheel.set(ControlMode.PercentOutput, 0.0);
        }

    }

    public void controlPanelWheelColor() {
        if (colorString.length() > 0) {
            switch (colorString.charAt(0)) {
            case 'B':
                while (match.color != kBlueTarget) {
                    controlPanelWheel.set(ControlMode.PercentOutput, turn);
                }
                colorString = "Blue";
                break;
            case 'G':
                while (match.color != kGreenTarget) {
                    controlPanelWheel.set(ControlMode.PercentOutput, turn);
                }
                colorString = "Green";
                break;
            case 'R':
                while (match.color != kRedTarget) {
                    controlPanelWheel.set(ControlMode.PercentOutput, turn);
                }
                colorString = "Red";
                break;
            case 'Y':
                while (match.color != kYellowTarget) {
                    controlPanelWheel.set(ControlMode.PercentOutput, turn);
                }
                colorString = "Yellow";
                break;
            default:
                // This is corrupt data
                break;
            }
        } else {
            controlPanelWheel.set(ControlMode.PercentOutput, 0.0);
        }

    }
     public void controlPanelManual(double speed){
         controlPanelWheel.set(ControlMode.PercentOutput, speed);
     }

     public void controlPanelArmToggle(){
        if (controlPanelArm.get()) {
            
			controlPanelArm.set(false);
		} else {
			controlPanelArm.set(true);
		}
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