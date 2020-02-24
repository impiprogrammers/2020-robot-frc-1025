/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ImpiLib2020;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorRollFailsafe extends CommandBase {
  private final ConveyorSubsystem conveyorSubsystem;
	private final DoubleSupplier joystickValue;
  public ConveyorRollFailsafe(ConveyorSubsystem conveyorSubsystem , DoubleSupplier joystickValue) {
    this.conveyorSubsystem = conveyorSubsystem;
		this.joystickValue = joystickValue;
		addRequirements(conveyorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    conveyorSubsystem.conveyorRollFailsafe(ImpiLib2020.parseJoystick(joystickValue));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
