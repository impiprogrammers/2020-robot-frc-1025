package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.conveyor.ConveyorRoll;

public class ConveyorSubsystem extends SubsystemBase {

	TalonSRX conveyorRollers = new TalonSRX(Constants.CONVEYOR_ROLLERS_PORT);

	public ConveyorSubsystem() {
		conveyorRollers.set(ControlMode.PercentOutput, 0);
		conveyorRollers.configFactoryDefault();
		conveyorRollers.setNeutralMode(NeutralMode.Brake);
		setDefaultCommand(new ConveyorRoll());
	}

	public void conveyorRoll(double speed) {
		conveyorRollers.set(ControlMode.PercentOutput, speed);
	}

	public void conveyorStop(){
		conveyorRollers.set(ControlMode.PercentOutput, 0.0);
	}

}