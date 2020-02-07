package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.autonomous.*;
import frc.robot.commands.chassis.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.control_panel.*;
import frc.robot.commands.conveyor.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.shooter_feeder.*;
import frc.robot.commands.turret.*;

public class RobotContainer {

	// Subsystems
	private final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	private final ControlPanelSubsystem controlPanelSubsystem = new ControlPanelSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
	private final ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();
	private final TurretSubsystem turretSubsystem = new TurretSubsystem();
	
	// Autonomous Commands

	// Chassis Commands

	// Climber Commands
	private final ExtendClimberArm extendClimberArm = new ExtendClimberArm(climberSubsystem);
	private final RetractClimberArm retractClimberArm = new RetractClimberArm(climberSubsystem);
	private final ToggleClimberArm toggleClimberArm = new ToggleClimberArm(climberSubsystem);
	private final LockClimber lockClimberArm = new LockClimber(climberSubsystem);
	private final UnlockClimber unlockClimberArm = new UnlockClimber(climberSubsystem);
	private final ToggleClimberLock toggleClimberLock = new ToggleClimberLock(climberSubsystem);

	// Control Panel Commands
	private final ExtendControlPanelArm extendControlPanelArm = new ExtendControlPanelArm(controlPanelSubsystem);
	private final RetractControlPanelArm retractControlPanelArm = new RetractControlPanelArm(controlPanelSubsystem);
	private final ToggleControlPanelArm toggleControlPanelArm = new ToggleControlPanelArm(controlPanelSubsystem);

	// Conveyor Commands

	// Intake Commands
	private final ExtendIntakeArm extendIntakeArm = new ExtendIntakeArm(intakeSubsystem);
	private final RetractIntakeArm retractIntakeArm = new RetractIntakeArm(intakeSubsystem);
	private final ToggleIntakeArm toggleIntakeArm = new ToggleIntakeArm(intakeSubsystem);
/*
	// Shooter Commands
	private final ShooterShoot shooterShoot = new ShooterShoot(5700);
	private final ShooterStop shooterStop = new ShooterStop();
	private final ShooterToggle shooterToggle = new ShooterToggle(5700);
	
	// Shooter Feeder Commands
	private ShooterFeederSpin shooterFeederSpin = new ShooterFeederSpin();
	private ShooterFeederStop shooterFeederStop = new ShooterFeederStop();
*/
	// Turret Commands
	private final TurretSpin turretSpin = new TurretSpin();
	private final ToggleLimelightLock toggleLimelightLock = new ToggleLimelightLock();
	
 	// OI
	private final XboxController driverController = new XboxController(Constants.OI.DRIVER_JOYSTICK);
	private final XboxController buttonsController = new XboxController(Constants.OI.BUTTONS_JOYSTICK);

	private final JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
	private final JoystickButton driverB = new JoystickButton(driverController, XboxController.Button.kB.value);
	private final JoystickButton driverX = new JoystickButton(driverController, XboxController.Button.kX.value);
	private final JoystickButton driverY = new JoystickButton(driverController, XboxController.Button.kY.value);
	private final JoystickButton driverSelect = new JoystickButton(driverController, XboxController.Button.kBack.value);
	private final JoystickButton driverStart = new JoystickButton(driverController, XboxController.Button.kStart.value);

	private final JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperRight.value);
	private final JoystickButton buttonsSelect = new JoystickButton(buttonsController, XboxController.Button.kBack.value);
	private final JoystickButton buttonsStart = new JoystickButton(buttonsController, XboxController.Button.kStart.value);
	
 	public RobotContainer() {
		setDefaultCommands();
		configureButtonBindings();
	}

	private void setDefaultCommands() {
		chassisSubsystem.setDefaultCommand(
			new ChassisJoystickArcade(chassisSubsystem,
				()->driverController.getY(GenericHID.Hand.kLeft),
				()->driverController.getX(GenericHID.Hand.kRight)));

		climberSubsystem.setDefaultCommand(
			new WinchJoystickTriggers(climberSubsystem,
				()->buttonsController.getTriggerAxis(GenericHID.Hand.kLeft),
				()->buttonsController.getTriggerAxis(GenericHID.Hand.kRight)));

		intakeSubsystem.setDefaultCommand(
			new IntakeRollersJoystickAxis(intakeSubsystem,
				()->buttonsController.getTriggerAxis(GenericHID.Hand.kLeft)));

		conveyorSubsystem.setDefaultCommand(
			new ConveyorRollersJoystickAxis(conveyorSubsystem,
				()->buttonsController.getY(GenericHID.Hand.kRight)));
	}
	 
 	private void configureButtonBindings() { 
		driverA.whenPressed(toggleClimberLock);
		driverSelect.whenPressed(extendClimberArm);
		driverStart.whenPressed(retractClimberArm);

		buttonsSelect.whenPressed(extendControlPanelArm);
		buttonsStart.whenPressed(retractControlPanelArm);
		//buttonsLeftBumper.whenPressed(extendIntakeArm);
		//buttonsRightBumper.whenPressed(shooterToggle);
	}

  	public Command getAutonomousCommand() {
		return null;
	}
}
