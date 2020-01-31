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
import frc.robot.subsystems.*;
import frc.robot.commands.chassis.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	// Subsystems
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();

	// Commands
	private final ChassisDrive chassisDrive = new ChassisDrive(chassisSubsystem);

	// OI
	public static final XboxController driverController = new XboxController(Constants.XBOX_CONTROLLER_DRIVER);
	public static final XboxController buttonsController = new XboxController(Constants.XBOX_CONTROLLER_BUTTONS);

	private final JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
	private final JoystickButton driverB = new JoystickButton(driverController, XboxController.Button.kB.value);
	private final JoystickButton driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
	private final JoystickButton driverY = new JoystickButton(driverController, XboxController.Button.kY.value);
	private final JoystickButton driverSelect = new JoystickButton(driverController, XboxController.Button.kBack.value);
	private final JoystickButton driverStart = new JoystickButton(driverController, XboxController.Button.kStart.value);

	private final JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController,
			XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController,
			XboxController.Button.kBumperRight.value);

	// Global Variables
	public static boolean climberMode = false;

	public RobotContainer() {
		chassisSubsystem.setDefaultCommand(chassisDrive);
		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		// driverA.whenPressed(climberLockToggle);
		// driverSelect.whenPressed(climberExtenderExtend);
		// driverStart.whenPressed(climberExtenderRetract);

		// buttonsLeftBumper.whenPressed(intakeExtenderToggle);
		// buttonsRightBumper.whenPressed(shooterToggle);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return null;

	}
}
