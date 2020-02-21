/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.chassis.*;
import frc.robot.commands.chassis.auto.paths.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.conveyor.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter_feeder.*;
import frc.robot.commands.turret.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.control_panel.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	SendableChooser<Command> autoChooser = new SendableChooser<>();

	// Subsystems
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
	public static final ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static final ControlPanelSubsystem controlPanelSubsystem = new ControlPanelSubsystem();
	
	// Commands
	private final ChassisDrive chassisDrive = new ChassisDrive();

	private final IntakeArmToggle intakeExtenderToggle = new IntakeArmToggle();
	private final IntakeRollersRoll intakeRollersRoll = new IntakeRollersRoll();

	private final ShooterShoot shooterShoot = new ShooterShoot(5700);
	private final ShooterStop shooterStop = new ShooterStop();
	private final ShooterToggle shooterToggle = new ShooterToggle(4500);

	private final ConveyorRoll conveyorRoll = new ConveyorRoll();

	private final ShooterFeederSpin shooterFeederSpin = new ShooterFeederSpin();
	private final ShooterFeederStop shooterFeederStop = new ShooterFeederStop();
	
	private final ClimberArmExtend climberArmExtend = new ClimberArmExtend();
	private final ClimberArmRetract climberArmRetract = new ClimberArmRetract();
	private final ClimberLockToggle climberLockToggle = new ClimberLockToggle();
	private final ClimberLoop climberLoop = new ClimberLoop();

	private final TurretSpin turretSpin = new TurretSpin();
	private final TurretCenter turretCenter = new TurretCenter();
	private final TurretToggleManualMode turretToggleManualMode = new TurretToggleManualMode();

	private final ControlPanelArmExtend controlPanelArmExtend = new ControlPanelArmExtend();
	private final ControlPanelArmRetract controlPanelArmRetract = new ControlPanelArmRetract();
	private final ControlPanelWheelColor controlPanelWheelColor = new ControlPanelWheelColor();
	private final ControlPanelWheelSpinFour controlPanelWheelSpinFour = new ControlPanelWheelSpinFour();
	
	
 	// OI
	public static final XboxController driverController = new XboxController(Constants.XBOX_CONTROLLER_DRIVER);
	public static final XboxController buttonsController = new XboxController(Constants.XBOX_CONTROLLER_BUTTONS);

	private final JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
	private final JoystickButton driverB = new JoystickButton(driverController, XboxController.Button.kB.value);
	private final JoystickButton driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
	private final JoystickButton driverY = new JoystickButton(driverController, XboxController.Button.kY.value);
	private final JoystickButton driverSelect = new JoystickButton(driverController, XboxController.Button.kBack.value);
	private final JoystickButton driverStart = new JoystickButton(driverController, XboxController.Button.kStart.value);

	private final JoystickButton buttonsA = new JoystickButton(buttonsController, XboxController.Button.kA.value);
	private final JoystickButton buttonsB = new JoystickButton(buttonsController, XboxController.Button.kB.value);
	private final JoystickButton buttonsX = new JoystickButton(buttonsController, XboxController.Button.kX.value);
	private final JoystickButton buttonsY = new JoystickButton(buttonsController, XboxController.Button.kY.value);
	private final JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperRight.value);
	private final JoystickButton buttonsSelect = new JoystickButton(buttonsController, XboxController.Button.kBack.value);
	private final JoystickButton buttonsStart = new JoystickButton(buttonsController, XboxController.Button.kStart.value);
	

	// Global Variables
	public static boolean climberMode = false;

 	public RobotContainer() {
		chassisSubsystem.setDefaultCommand(chassisDrive);
		//climberSubsystem.setDefaultCommand(climberLoop);
		turretSubsystem.setDefaultCommand(turretSpin);
		conveyorSubsystem.setDefaultCommand(conveyorRoll);
		intakeSubsystem.setDefaultCommand(intakeRollersRoll);
		shooterFeederSubsystem.setDefaultCommand(shooterFeederSpin);
		
		
		configureButtonBindings();

		// Configure Auto Chooser
		try {
			autoChooser.setDefaultOption("Left 3", new AutoLeft3());
			autoChooser.addOption("Center 3", new AutoCenter3());
			autoChooser.addOption("Right 0", new AutoRight0());
			autoChooser.addOption("Right 3", new AutoRight3());
			autoChooser.addOption("Left 8", new AutoLeft8());
			autoChooser.addOption("Center 8", new AutoCenter8());
			autoChooser.addOption("Right 10 (Trench)", new AutoRightTrench10());
			autoChooser.addOption("Right 10 (Shield)", new AutoRightShield10());
		} catch(IOException exception) {
			DriverStation.reportError("Autonomous Path JSON Not Found", true);
		}

		SmartDashboard.putData("Autonomous Path (Station, Ball Count)", autoChooser);
	}

  	/**
 	* Use this method to define your button->command mappings.  Buttons can be created by
 	* instantiating a {@link GenericHID} or one of its subclasses ({@link
 	* edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
  	* {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
  	*/
 	private void configureButtonBindings() { 
		//driverA.whenPressed(climberLockToggle);
		//driverSelect.whenPressed(climberArmExtend);
		//driverStart.whenPressed(climberArmRetract);

		buttonsX.whenPressed(turretToggleManualMode);
		// buttonsY.whenPressed(turretCenter);
		buttonsLeftBumper.whenPressed(intakeExtenderToggle);
		buttonsRightBumper.whenPressed(shooterToggle);
		buttonsA.whenPressed(controlPanelWheelSpinFour);
		buttonsB.whenPressed(controlPanelWheelColor);
		buttonsSelect.whenPressed(controlPanelArmExtend);
		buttonsStart.whenPressed(controlPanelArmRetract);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		return autoChooser.getSelected();
	}
}