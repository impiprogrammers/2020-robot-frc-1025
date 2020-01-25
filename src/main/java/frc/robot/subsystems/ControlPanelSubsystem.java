/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
		==========
		UNFINISHED
		==========
*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanelSubsystem extends SubsystemBase {
	private TalonSRX controlPanelWheel =  new TalonSRX(Constants.CONTROL_PANEL_WHEEL_PORT);
	private Solenoid controlPanelPiston = new Solenoid(Constants.CONTROL_PANEL_PISTON_MODULE, Constants.CONTROL_PANEL_PISTON_CHANNEL);
	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
	private final ColorMatch colorMatcher = new ColorMatch();
	private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


	public ControlPanelSubsystem() {
	}

	public void controlPanelPistonExtend() {
		controlPanelPiston.set(true);
	}

	public void controlPanelPistonRetract(){
		controlPanelPiston.set(false);
	}

	public void controlPanelWheelSpinFour(){
		
	}

	public void controlPanelWheelColor(){
		
	}
	@Override
	public void periodic() {
		
	}
}
