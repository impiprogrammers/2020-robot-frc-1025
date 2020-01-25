/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.intake.IntakeRollersRoll;

public class IntakeSubsystem extends SubsystemBase {

	Solenoid intakeExtender = new Solenoid(Constants.INTAKE_EXTENDER_MODULE, Constants.INTAKE_EXTENDER_CHANNEL);
	TalonSRX intakeRollers = new TalonSRX(Constants.INTAKE_ROLLERS_PORT);

	public IntakeSubsystem() {
		setDefaultCommand(new IntakeRollersRoll());
	}

	public void intakeToggle() {
		if (intakeExtender.get()) {
			intakeExtender.set(false);
		} else {
			intakeExtender.set(true);
		}
	}

	public void rollersRoll(double speed) {
		intakeRollers.set(ControlMode.PercentOutput, speed);
	}

}
