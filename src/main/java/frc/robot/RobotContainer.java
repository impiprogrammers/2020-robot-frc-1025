/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChasisSubsystem;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.ImpiLib;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static ChasisSubsystem chasisSubsystem = new ChasisSubsystem();
  public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
 * @return 
   */
   private double configureButtonBindings() {
    // Dead zones
	final double DEADZONE_CHASSIS = 0.05;
	final double DEADZONE_ELEVATOR = 0.1;
	final double DEADZONE_WRIST = 0.1;
	final double DEADZONE_CLIMBER = 0.1;

	final XboxController driverController = new XboxController(Constants.OI_DRIVER_CONTROLLER_CHANNEL);
	final XboxController buttonsController = new XboxController(Constants.OI_BUTTONS_CONTROLLER_CHANNEL);

	final JoystickButton driverButtonA = new JoystickButton(driverController, 1);
	final JoystickButton driverButtonB = new JoystickButton(driverController, 2);
	final JoystickButton driverButtonX = new JoystickButton(driverController, 3);
	final JoystickButton driverButtonY = new JoystickButton(driverController, 4);
	final JoystickButton driverButtonBack = new JoystickButton(driverController , 7);
	final JoystickButton driverButtonStart = new JoystickButton(driverController , 8);
	final JoystickButton driverButtonLeftBumper = new JoystickButton(driverController, 5);
	final JoystickButton driverButtonRightBumper = new JoystickButton(driverController, 6);

	final JoystickButton buttonsButtonA = new JoystickButton(buttonsController, 1);
	final JoystickButton buttonsButtonB = new JoystickButton(buttonsController, 2);
	final JoystickButton buttonsButtonX = new JoystickButton(buttonsController, 3);
	final JoystickButton buttonsButtonY = new JoystickButton(buttonsController, 4);
	final JoystickButton buttonsButtonBack = new JoystickButton(buttonsController , 7);
	final JoystickButton buttonsButtonStart = new JoystickButton(buttonsController , 8);
	final JoystickButton buttonsButtonLeftBumper = new JoystickButton(buttonsController, 5);
	final JoystickButton buttonsButtonRightBumper = new JoystickButton(buttonsController, 6);
    return DEADZONE_CLIMBER;
	}

	final double getDriverLeftX() {
		return ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getDriverController().getRawAxis(0)));
	}

	final double getDriverLeftY() {
		return -ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getDriverController().getRawAxis(1)));
	}

	final double getDriverRightY() {
		return -ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getDriverController().getRawAxis(5)));
	}

	final double getDriverRightX() {
		return ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getDriverController().getRawAxis(4)));
	}

	final double getTriggerAxis() {
		return ImpiLib.signedSquare(-getDriverController().getRawAxis(2)) + ImpiLib.signedSquare(getDriverController().getRawAxis(3));
	}

	final double getButtonsLeftY() {
		return -ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getButtonsController().getRawAxis(1)));
	}

	final double getButtonsRightY() {
		return -ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getButtonsController().getRawAxis(5)));
	}

	final double getButtonsLeftX() {
		return ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getButtonsController().getRawAxis(4)));
	}

	final double getButtonsRightX() {
		return ImpiLib.signedSquare(ImpiLib.clamp(1.0, -1.0, getButtonsController().getRawAxis(0)));
	}

	final double getButtonsTriggerAxis() {
		return ImpiLib.signedSquare(-getButtonsController().getRawAxis(2)) + ImpiLib.signedSquare(getButtonsController().getRawAxis(3));
	}

	public final static XboxController getDriverController() {
		return getDriverController();
	}

	public final static XboxController getButtonsController() {
		return getButtonsController();
	}


  


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
