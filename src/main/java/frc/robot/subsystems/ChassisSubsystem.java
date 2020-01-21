/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.*;

public class ChassisSubsystem extends SubsystemBase {


  public WPI_TalonSRX leftFront = new WPI_TalonSRX(Constants.CHASSIS_LEFT_FRONT_MOTOR);
  public WPI_TalonSRX rightFront = new WPI_TalonSRX(Constants.CHASSIS_RIGHT_FRONT_MOTOR);
  public WPI_TalonSRX leftRear = new WPI_TalonSRX(Constants.CHASSIS_LEFT_REAR_MOTOR);
  public WPI_TalonSRX rightRear = new WPI_TalonSRX(Constants.CHASSIS_RIGHT_REAR_MOTOR);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  public DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);

  public ChassisSubsystem() {
	  leftRear.follow(leftFront);
	  rightRear.follow(rightFront);
  }
  
  public void manualDrive(double move, double turn) {
	// if(move > .5) move = .5;

	drive.arcadeDrive(move, turn);
  }

  @Override
  public void periodic() {
	// This method will be called once per scheduler run
	setDefaultCommand(new ChassisDrive());
  }
}