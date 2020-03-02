/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.chassis.*;
import frc.robot.commands.auto.paths.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.conveyor.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.led.UpdateLights;
import frc.robot.commands.shimmy.ShimmyMove;
import frc.robot.commands.shimmy.ShimmyStop;
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
	private final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
	private final ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();
	private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	private final ShimmySubsystem shimmySubsystem = new ShimmySubsystem();
	private final TurretSubsystem turretSubsystem = new TurretSubsystem();
	private final LEDSubsystem ledSubsystem = new LEDSubsystem();
	private final ControlPanelSubsystem controlPanelSubsystem = new ControlPanelSubsystem();
		
 	// OI
	private final XboxController driverController = new XboxController(Constants.OI.XBOX_CONTROLLER_DRIVER);
	private final XboxController buttonsController = new XboxController(Constants.OI.XBOX_CONTROLLER_BUTTONS);
 
	private final JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
	private final JoystickButton driverB = new JoystickButton(driverController, XboxController.Button.kB.value);
	private final JoystickButton driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
	private final JoystickButton driverY = new JoystickButton(driverController, XboxController.Button.kY.value);
	private final JoystickButton driverSelect = new JoystickButton(driverController, XboxController.Button.kBack.value);
	private final JoystickButton driverStart = new JoystickButton(driverController, XboxController.Button.kStart.value);

	private final DoubleSupplier driverLeftJoystickX = () -> driverController.getX(Hand.kLeft);
	private final DoubleSupplier driverLeftJoystickY = () -> driverController.getY(Hand.kLeft);
	private final DoubleSupplier driverLeftTrigger = () -> driverController.getTriggerAxis(Hand.kLeft);
	private final DoubleSupplier driverRightJoystickX = () -> driverController.getX(Hand.kRight);
	private final DoubleSupplier driverRightJoystickY = () -> driverController.getY(Hand.kRight);
	private final DoubleSupplier driverRightTrigger = () -> driverController.getTriggerAxis(Hand.kRight);
	private final IntSupplier driverDpad = () -> driverController.getPOV();
 
	private final JoystickButton buttonsA = new JoystickButton(buttonsController, XboxController.Button.kA.value);
	private final JoystickButton buttonsB = new JoystickButton(buttonsController, XboxController.Button.kB.value);
	private final JoystickButton buttonsX = new JoystickButton(buttonsController, XboxController.Button.kX.value);
	private final JoystickButton buttonsY = new JoystickButton(buttonsController, XboxController.Button.kY.value);
	private final JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperRight.value);
	private final JoystickButton buttonsSelect = new JoystickButton(buttonsController, XboxController.Button.kBack.value);
	private final JoystickButton buttonsStart = new JoystickButton(buttonsController, XboxController.Button.kStart.value);

	private final DoubleSupplier buttonsLeftJoystickX = () -> buttonsController.getX(Hand.kLeft);
	private final DoubleSupplier buttonsLeftJoystickY = () -> buttonsController.getY(Hand.kLeft);
	private final DoubleSupplier buttonsLeftTrigger = () -> buttonsController.getTriggerAxis(Hand.kLeft);
	private final DoubleSupplier buttonsRightJoystickX = () -> buttonsController.getX(Hand.kRight);
	private final DoubleSupplier buttonsRightJoystickY = () -> buttonsController.getY(Hand.kRight);
	private final DoubleSupplier buttonsRightTrigger = () -> buttonsController.getTriggerAxis(Hand.kRight);
	private final IntSupplier buttonsDpad = () -> buttonsController.getPOV();
	
	// Commands
	private final ChassisDrive chassisDrive = new ChassisDrive(chassisSubsystem, driverLeftJoystickY, driverRightJoystickX);

	private final IntakeArmToggle intakeExtenderToggle = new IntakeArmToggle(intakeSubsystem);
	private final IntakeArmExtend intakeArmExtend = new IntakeArmExtend(intakeSubsystem);

	private final IntakeArmRetract intakeArmRetract = new IntakeArmRetract(intakeSubsystem);
	private final IntakeRollersRoll intakeRollersRoll = new IntakeRollersRoll(intakeSubsystem, buttonsRightTrigger, buttonsLeftTrigger);

	private final ShooterShoot shooterShoot = new ShooterShoot(shooterSubsystem, 5700);
	private final ShooterStop shooterStop = new ShooterStop(shooterSubsystem);
	private final ShooterToggle shooterToggle = new ShooterToggle(shooterSubsystem, 4060);
	private final ConveyorRoll conveyorRoll = new ConveyorRoll(conveyorSubsystem, buttonsRightJoystickY);
	private final ConveyorRollFailsafe conveyorRollFailsafe = new ConveyorRollFailsafe(conveyorSubsystem, buttonsRightJoystickY);
	private final ConveyorStop conveyorStop = new ConveyorStop(conveyorSubsystem);
	private final ShooterFeederSpin shooterFeederSpin = new ShooterFeederSpin(shooterFeederSubsystem, buttonsDpad);
	private final ShooterFeederStop shooterFeederStop = new ShooterFeederStop(shooterFeederSubsystem);
	
	private final ClimberArmExtend climberArmExtend = new ClimberArmExtend(climberSubsystem);
	private final ClimberArmRetract climberArmRetract = new ClimberArmRetract(climberSubsystem);
	private final ClimberLockExtend climberLockExtend = new ClimberLockExtend(climberSubsystem);
	private final ClimberLockRetract climberLockRetract = new ClimberLockRetract(climberSubsystem);
	private final ClimberLockToggle climberLockToggle = new ClimberLockToggle(climberSubsystem);
	private final ClimberWinchMove climberWinchMove = new ClimberWinchMove(climberSubsystem, driverRightTrigger, driverLeftTrigger);

	private final ShimmyMove shimmyMove = new ShimmyMove(shimmySubsystem, driverDpad);
	private final ShimmyStop shimmyStop = new ShimmyStop(shimmySubsystem);

	private final TurretSpin turretSpin = new TurretSpin(turretSubsystem, buttonsLeftJoystickX);
	private final TurretRezero turretRezero = new TurretRezero(turretSubsystem);
	private final TurretTeleop turretTeleop = new TurretTeleop(turretSubsystem, buttonsLeftJoystickX);
	private final TurretToggleManualMode turretToggleManualMode = new TurretToggleManualMode(turretSubsystem);
	private final TurretSetManualMode turretSetManualMode = new TurretSetManualMode(turretSubsystem, true);
	private final TurretSetManualMode turretSetAutoMode = new TurretSetManualMode(turretSubsystem, false);
	private final TurretTrackTarget turretTrackTarget = new TurretTrackTarget(turretSubsystem);
	private final TurretTurnToTarget turretTurnToTarget = new TurretTurnToTarget(turretSubsystem);

	private final ControlPanelArmExtend controlPanelArmExtend = new ControlPanelArmExtend(controlPanelSubsystem);
	private final ControlPanelArmRetract controlPanelArmRetract = new ControlPanelArmRetract(controlPanelSubsystem);
	private final ControlPanelColor controlPanelWheelColor = new ControlPanelColor(controlPanelSubsystem);
	private final ControlPanelSpinFour controlPanelWheelSpinFour = new ControlPanelSpinFour(controlPanelSubsystem);

	private final UpdateLights updateLights = new UpdateLights(ledSubsystem, shooterSubsystem, turretSubsystem);

	// Global Variables
	public static boolean climberMode = false;

 	public RobotContainer() {
		chassisSubsystem.setDefaultCommand(chassisDrive);
		climberSubsystem.setDefaultCommand(climberWinchMove);
		turretSubsystem.setDefaultCommand(turretTeleop);
		conveyorSubsystem.setDefaultCommand(conveyorRoll);
		intakeSubsystem.setDefaultCommand(intakeRollersRoll);
		shooterFeederSubsystem.setDefaultCommand(shooterFeederSpin);
		shimmySubsystem.setDefaultCommand(shimmyMove);
		//ledSubsystem.setDefaultCommand(updateLights);
		
		
		configureButtonBindings();

		// Configure Auto Chooser
		try {
			autoChooser.setDefaultOption("Left 3", new AutoLeft3(chassisSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
			autoChooser.addOption("Center 3", new AutoCenter3(chassisSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
			autoChooser.addOption("Right 0", new AutoRight0(chassisSubsystem));
			autoChooser.addOption("Right 3", new AutoRight3(chassisSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
			autoChooser.addOption("Left 8", new AutoLeft8(chassisSubsystem, intakeSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
			autoChooser.addOption("Center 8", new AutoCenter8(chassisSubsystem, intakeSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
			autoChooser.addOption("Right 10 (Trench)", new AutoRightTrench10(chassisSubsystem, intakeSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
			autoChooser.addOption("Right 10 (Shield)", new AutoRightShield10(chassisSubsystem, intakeSubsystem, conveyorSubsystem, shooterFeederSubsystem, shooterSubsystem));
		} catch(IOException exception) {
			DriverStation.reportError("Autonomous Path JSON Not Found", true);
		}

		SmartDashboard.putData("Autonomous Path (Station, Ball Count)", autoChooser);

		// Add Buttons to SmartDashboard
		SmartDashboard.putData("Rezero Turret", turretRezero);
	}

  	/**
 	* Use this method to define your button->command mappings.  Buttons can be created by
 	* instantiating a {@link GenericHID} or one of its subclasses ({@link
 	* edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
  	* {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
  	*/
 	private void configureButtonBindings() { 
		driverA.whenPressed(climberLockExtend);
		driverB.whenPressed(climberLockRetract);
		driverSelect.whenPressed(climberArmExtend);
		driverStart.whenPressed(climberArmRetract);

		buttonsX.whenPressed(turretSetManualMode);
		buttonsY.whenPressed(turretSetAutoMode);
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
		// return autoChooser.getSelected();
		try {
			return new AutoLeft8b(chassisSubsystem, intakeSubsystem, conveyorSubsystem, shooterFeederSubsystem,
					shooterSubsystem, turretSubsystem);
		} catch (Exception e) {
			DriverStation.reportError("Auto Command Error: " + e.getMessage(), true);
			return null;
		}
		
	}
}