package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ConveyorRoll;

public class ConveyorSubsystem extends SubsystemBase {

	TalonSRX conveyorRollers = new TalonSRX(Constants.CONVEYOR_ROLLERS_PORT);

	public ConveyorSubsystem() {
		setDefaultCommand(new ConveyorRoll());
	}

	@Override
	public void periodic() {
		
	}

	public void conveyorRoll(double speed) {
		conveyorRollers.set(ControlMode.PercentOutput, speed);
	}

}