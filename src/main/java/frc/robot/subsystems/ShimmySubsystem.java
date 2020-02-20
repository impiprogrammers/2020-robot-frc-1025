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

public class ShimmySubsystem extends SubsystemBase {

  TalonSRX climberShimmy = new TalonSRX(Constants.CLMBER_SHIMMY_PORT);
  
  public ShimmySubsystem() {
  }

  public void ShimmyMove(double speed) {
    climberShimmy.set(ControlMode.PercentOutput, speed);
  }

  public void ShimmyStop() {
    climberShimmy.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
