/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

	// Motor Controllers
	TalonSRX elevatorMotor = new TalonSRX(Constants.ELEVATOR_MOTOR_PORT);

	public ElevatorSubsystem() {

	}

	public void elevatorMove(double speed) {
		elevatorMotor.set(ControlMode.PercentOutput, speed);
	}
}
