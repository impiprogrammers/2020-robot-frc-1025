/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.chassis.ChassisDrive;
import frc.robot.commands.intake.IntakeExtenderToggle;
import frc.robot.commands.intake.IntakeRollersRoll;
import frc.robot.subsystems.ChassisSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	// Subsystems
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	
	// Commands
	private final ChassisDrive chassisDrive = new ChassisDrive();

	IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	
	// Commands

	IntakeExtenderToggle intakeExtenderToggle = new IntakeExtenderToggle();
	IntakeRollersRoll intakeRollersRoll = new IntakeRollersRoll();

	// OI
	
	public static XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
	public static XboxController buttonsController = new XboxController(Constants.BUTTONS_CONTROLLER_PORT);

	JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperLeft.value);
	
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
		 buttonsLeftBumper.whenPressed(intakeExtenderToggle);
	  }

 	/**
  	* Use this to pass the autonomous command to the main {@link Robot} class.
  	*
   	* @return the command to run in autonomous
	  */
  	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return new ChassisDrive();
	}
}
