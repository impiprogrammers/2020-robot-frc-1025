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

public class IntakeSubsystem extends SubsystemBase {

	Solenoid intakeArm = new Solenoid(Constants.PCM_MODULE_PORT, Constants.INTAKE_EXTENDER_CHANNEL);
	TalonSRX intakeRollers = new TalonSRX(Constants.INTAKE_ROLLERS_PORT);

	public IntakeSubsystem() {
	}

	public void intakeToggle() {
		if (intakeArm.get()) {
			intakeArm.set(false);
		} else {
			intakeArm.set(true);
		}
	}

	public void intakeArmExtend() {
		intakeArm.set(true);
	}

	public void intakeArmRetract() {
		intakeArm.set(false);
	}

	public void rollersRoll(double speed) {
		intakeRollers.set(ControlMode.PercentOutput, speed);
	}

}