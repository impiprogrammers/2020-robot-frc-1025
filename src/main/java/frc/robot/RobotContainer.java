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
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter.ShooterToggle;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.commands.shooter_feeder.ShooterFeederSpin;
import frc.robot.commands.shooter_feeder.ShooterFeederStop;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.commands.climber.ClimberExtenderExtend;
import frc.robot.commands.climber.ClimberExtenderRetract;
import frc.robot.commands.climber.ClimberLockToggle;
import frc.robot.commands.climber.ClimberShimmyMove;
import frc.robot.commands.climber.ClimberWinchMove;
import frc.robot.commands.conveyor.ConveyorRoll;
import frc.robot.subsystems.ClimberSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	// Subsystems
	private final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
	
	ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();

	ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	
	// Commands
	private final ChassisDrive chassisDrive = new ChassisDrive();

	private final IntakeExtenderToggle intakeExtenderToggle = new IntakeExtenderToggle();
	private final IntakeRollersRoll intakeRollersRoll = new IntakeRollersRoll();

	private final ShooterShoot shooterShoot = new ShooterShoot(5700);
	private final ShooterStop shooterStop = new ShooterStop();
	private final ShooterToggle shooterToggle = new ShooterToggle(5700);

	private final ConveyorRoll conveyorRoll = new ConveyorRoll();

	private ShooterFeederSpin shooterFeederSpin = new ShooterFeederSpin();
	private ShooterFeederStop shooterFeederStop = new ShooterFeederStop();
	
	private final ClimberExtenderExtend climberExtenderExtend = new ClimberExtenderExtend();
	private final ClimberExtenderRetract climberExtenderRetract = new ClimberExtenderRetract();
	private final ClimberLockToggle climberLockToggle = new ClimberLockToggle();
	private final ClimberWinchMove climberWinchMove = new ClimberWinchMove();
	private final ClimberShimmyMove climberShimmyMove = new ClimberShimmyMove();

 	// OI
	public static final XboxController driverController = new XboxController(Constants.XBOX_CONTROLLER_DRIVER);
	public static final XboxController buttonsController = new XboxController(Constants.XBOX_CONTROLLER_BUTTONS);

	private final JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
	private final JoystickButton driverB = new JoystickButton(driverController, XboxController.Button.kB.value);
	private final JoystickButton driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
	private final JoystickButton driverY = new JoystickButton(driverController, XboxController.Button.kY.value);
	private final JoystickButton driverSelect = new JoystickButton(driverController, XboxController.Button.kBack.value);
	private final JoystickButton driverStart = new JoystickButton(driverController, XboxController.Button.kStart.value);

	private final JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperRight.value);
	

	// Global Variables
	public static boolean climberMode = false;

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

		buttonsLeftBumper.whenPressed(intakeExtenderToggle);
		buttonsRightBumper.whenPressed(shooterToggle);
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
