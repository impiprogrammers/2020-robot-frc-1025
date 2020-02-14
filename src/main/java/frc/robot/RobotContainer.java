package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
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
import frc.robot.commands.led.UpdateLights;
import frc.robot.commands.shimmy.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.shooter_feeder.*;
import frc.robot.commands.turret.*;

@SuppressWarnings("unused")

public class RobotContainer {

	// Subsystems
	private final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	private final ControlPanelSubsystem controlPanelSubsystem = new ControlPanelSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final LEDSubsystem ledSubsystem = new LEDSubsystem();
	private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
	private final ShimmySubsystem shimmySubsystem = new ShimmySubsystem();
	private final ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	private final TurretSubsystem turretSubsystem = new TurretSubsystem();
	
	// Autonomous Commands

	// Chassis Commands
	private final StopChassis stopChassis = new StopChassis(chassisSubsystem);

	// Climber Commands
	private final ExtendClimberArm extendClimberArm = new ExtendClimberArm(climberSubsystem);
	private final RetractClimberArm retractClimberArm = new RetractClimberArm(climberSubsystem);
	private final ToggleClimberArm toggleClimberArm = new ToggleClimberArm(climberSubsystem);
	private final LockClimber lockClimber = new LockClimber(climberSubsystem);
	private final UnlockClimber unlockClimber = new UnlockClimber(climberSubsystem);
	private final ToggleClimberLock toggleClimberLock = new ToggleClimberLock(climberSubsystem);
	private final StopWinch stopWinch = new StopWinch(climberSubsystem);

	// Control Panel Commands
	private final ExtendControlPanelArm extendControlPanelArm = new ExtendControlPanelArm(controlPanelSubsystem);
	private final RetractControlPanelArm retractControlPanelArm = new RetractControlPanelArm(controlPanelSubsystem);
	private final ToggleControlPanelArm toggleControlPanelArm = new ToggleControlPanelArm(controlPanelSubsystem);
	private final StopControlPanelWheel stopControlPanelWheel = new StopControlPanelWheel(controlPanelSubsystem);

	// Conveyor Commands
	private final StopConveyorRollers stopConveyorRollers = new StopConveyorRollers(conveyorSubsystem);

	// Intake Commands
	private final ExtendIntakeArm extendIntakeArm = new ExtendIntakeArm(intakeSubsystem);
	private final RetractIntakeArm retractIntakeArm = new RetractIntakeArm(intakeSubsystem);
	private final ToggleIntakeArm toggleIntakeArm = new ToggleIntakeArm(intakeSubsystem);
	private final StopIntakeRollers stopIntakeRollers = new StopIntakeRollers(intakeSubsystem);

	// Shimmy Commands
	private final StopShimmy stopShimmy = new StopShimmy(shimmySubsystem);

	// Shooter Commands
	private final StopShooter stopShooter = new StopShooter(shooterSubsystem);

	// Shooter Feeder Commands
	private StopShooterFeeder shooterFeederStop = new StopShooterFeeder(shooterFeederSubsystem);

	// Turret Commands
	private SetManualTurretMode setManualTurretMode = new SetManualTurretMode(turretSubsystem);
	private SetAutoTurretMode setAutoTurretMode = new SetAutoTurretMode(turretSubsystem);
	
 	// OI
	private final XboxController driverController = new XboxController(Constants.OI.DRIVER_JOYSTICK);
	private final XboxController buttonsController = new XboxController(Constants.OI.BUTTONS_JOYSTICK);

	private final JoystickButton driverA =           new JoystickButton(driverController, XboxController.Button.kA.value);
	private final JoystickButton driverB =           new JoystickButton(driverController, XboxController.Button.kB.value);
	private final JoystickButton driverX =           new JoystickButton(driverController, XboxController.Button.kX.value);
	private final JoystickButton driverY =           new JoystickButton(driverController, XboxController.Button.kY.value);
	private final JoystickButton driverSelect =      new JoystickButton(driverController, XboxController.Button.kBack.value);
	private final JoystickButton driverStart =       new JoystickButton(driverController, XboxController.Button.kStart.value);
	private final JoystickButton driverLeftBumper =  new JoystickButton(driverController, XboxController.Button.kBumperLeft.value);
	private final JoystickButton driverRightBumper = new JoystickButton(driverController, XboxController.Button.kBumperRight.value);
	private final JoystickButton driverStickLeft =   new JoystickButton(driverController, XboxController.Button.kStickLeft.value);
	private final JoystickButton driverStickRight =  new JoystickButton(driverController, XboxController.Button.kStickRight.value);

	private final JoystickButton buttonsA =           new JoystickButton(buttonsController, XboxController.Button.kA.value);
	private final JoystickButton buttonsB =           new JoystickButton(buttonsController, XboxController.Button.kB.value);
	private final JoystickButton buttonsX =           new JoystickButton(buttonsController, XboxController.Button.kX.value);
	private final JoystickButton buttonsY =           new JoystickButton(buttonsController, XboxController.Button.kY.value);
	private final JoystickButton buttonsSelect =      new JoystickButton(buttonsController, XboxController.Button.kBack.value);
	private final JoystickButton buttonsStart =       new JoystickButton(buttonsController, XboxController.Button.kStart.value);
	private final JoystickButton buttonsLeftBumper =  new JoystickButton(buttonsController, XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController, XboxController.Button.kBumperRight.value);
	private final JoystickButton buttonsStickLeft =   new JoystickButton(buttonsController, XboxController.Button.kStickLeft.value);
	private final JoystickButton buttonsStickRight =  new JoystickButton(buttonsController, XboxController.Button.kStickRight.value);
	
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
			new WinchJoystick(climberSubsystem,
				()->buttonsController.getTriggerAxis(GenericHID.Hand.kLeft),
				()->buttonsController.getTriggerAxis(GenericHID.Hand.kRight)));

		intakeSubsystem.setDefaultCommand(
			new IntakeRollersJoystick(intakeSubsystem,
				()->buttonsController.getTriggerAxis(GenericHID.Hand.kLeft)));

		conveyorSubsystem.setDefaultCommand(
			new ConveyorRollersJoystick(conveyorSubsystem,
				()->buttonsController.getY(GenericHID.Hand.kRight)));

		turretSubsystem.setDefaultCommand(
				new TurretJoystick(turretSubsystem,
					()->buttonsController.getX(GenericHID.Hand.kLeft)));

		ledSubsystem.setDefaultCommand(
				new UpdateLights(ledSubsystem, shooterSubsystem, turretSubsystem));
	}
	 
 	private void configureButtonBindings() { 
		driverA.whenPressed(toggleClimberLock);
		driverX.whenHeld(new SpinShimmy(shimmySubsystem, 1.));
		driverY.whenHeld(new SpinShimmy(shimmySubsystem, -1.));
		driverSelect.whenPressed(extendClimberArm);
		driverStart.whenPressed(retractClimberArm);

		buttonsSelect.whenPressed(extendControlPanelArm);
		buttonsStart.whenPressed(retractControlPanelArm);
		buttonsLeftBumper.whenPressed(toggleIntakeArm);
		buttonsRightBumper.toggleWhenPressed(new ShooterShoot(shooterSubsystem, Constants.Shooter.SHOOTER_SPEED));
	}

  	public Command getAutonomousCommand() {
		return null;
	}
}
