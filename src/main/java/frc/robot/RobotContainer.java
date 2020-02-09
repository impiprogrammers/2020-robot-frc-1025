/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.chassis.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.conveyor.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.shooter_feeder.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	SendableChooser<String> autoChooser = new SendableChooser<>();

	// Subsystems
	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final PIDShooterSubsystem shooterSubsystem = new PIDShooterSubsystem();
	public static final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();
	public static final ShooterFeederSubsystem shooterFeederSubsystem = new ShooterFeederSubsystem();
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();

	// Commands
	private final ChassisDrive chassisDrive = new ChassisDrive();
	private final ChassisAutoTurn chassisAutoTurn = new ChassisAutoTurn();

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

	private final JoystickButton buttonsLeftBumper = new JoystickButton(buttonsController,
			XboxController.Button.kBumperLeft.value);
	private final JoystickButton buttonsRightBumper = new JoystickButton(buttonsController,
			XboxController.Button.kBumperRight.value);

	// Global Variables
	public static boolean climberMode = false;

	public RobotContainer() {
		chassisSubsystem.setDefaultCommand(chassisDrive);
		configureButtonBindings();

		// Configure Auto Chooser
		autoChooser.setDefaultOption("Straight Line", "");

		SmartDashboard.putData("Autonomous Path", autoChooser);
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		driverA.whenPressed(climberLockToggle);
		driverX.whenPressed(chassisAutoTurn);
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
		SimpleMotorFeedforward ff = new SimpleMotorFeedforward(Constants.CHASSIS_AUTO_FFS, Constants.CHASSIS_AUTO_FFV,
				Constants.CHASSIS_AUTO_FFA);

		String trajectoryJSON = autoChooser.getSelected();
		try {
			Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
			Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

			RamseteCommand ramseteCommand = new RamseteCommand(trajectory, chassisSubsystem::getPose,
					new RamseteController(Constants.CHASSIS_AUTO_RAMSETE_B, Constants.CHASSIS_AUTO_RAMSETE_ZETA), ff,
					chassisSubsystem.driveKinematics, chassisSubsystem::getWheelSpeeds,
					new PIDController(Constants.CHASSIS_AUTO_P, 0, 0),
					new PIDController(Constants.CHASSIS_AUTO_P, 0, 0), chassisSubsystem::voltageTankDrive,
					chassisSubsystem);

			return ramseteCommand.andThen(() -> chassisSubsystem.voltageTankDrive(0, 0));
		} catch (IOException ex) {
			DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
			return null;
		}
	}
}