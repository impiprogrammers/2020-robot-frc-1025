/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.climber.ClimberExtenderExtend;
import frc.robot.commands.climber.ClimberExtenderRetract;
import frc.robot.commands.climber.ClimberLockToggle;
import frc.robot.commands.climber.ClimberShimmyMove;
import frc.robot.commands.climber.ClimberWinchMove;
import frc.robot.subsystems.ClimberSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	// Subsystems

	ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	
	// Commands

	ClimberExtenderExtend climberExtenderExtend = new ClimberExtenderExtend();
	ClimberExtenderRetract climberExtenderRetract = new ClimberExtenderRetract();
	ClimberLockToggle climberLockToggle = new ClimberLockToggle();
	ClimberWinchMove climberWinchMove = new ClimberWinchMove();
	ClimberShimmyMove climberShimmyMove = new ClimberShimmyMove();

	// OI
	public static XboxController driverController = new XboxController(Constants.XBOX_CONTROLLER_DRIVER);
	public static XboxController buttonsController = new XboxController(Constants.XBOX_CONTROLLER_BUTTONS);

	JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);

	JoystickButton driverSelect = new JoystickButton(driverController, XboxController.Button.kBack.value);
	JoystickButton driverStart = new JoystickButton(driverController, XboxController.Button.kStart.value);

 	public RobotContainer() {
		configureButtonBindings();
 	}

  	/**
 	* Use this method to define your button->command mappings.  Buttons can be created by
 	* instantiating a {@link GenericHID} or one of its subclasses ({@link
 	* edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
  	* {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
  	*/
 	private void configureButtonBindings() {
		driverA.whenPressed(climberLockToggle);
		driverSelect.whenPressed(climberExtenderExtend);
		driverStart.whenPressed(climberExtenderRetract);
	}

 	/**
  	* Use this to pass the autonomous command to the main {@link Robot} class.
  	*
   	* @return the command to run in autonomous
	  */
  	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return new ClimberShimmyMove();
	}
}
