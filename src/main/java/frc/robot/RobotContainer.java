/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.shooter.ShooterShoot;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	// Subsystems
	private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	
	// Commands
	private final ShooterShoot shooterShoot1 = new ShooterShoot(100);
	private final ShooterShoot shooterShoot2 = new ShooterShoot(5000);
	private final ShooterShoot shooterShoot3 = new ShooterShoot(5700);
	private final ShooterStop shooterStop = new ShooterStop();

 	// OI
	private final XboxController controller = new XboxController(Constants.XBOX_CONTROLLER);
	
	private final JoystickButton buttonA = new JoystickButton(controller, XboxController.Button.kA.value);
	private final JoystickButton buttonB = new JoystickButton(controller, XboxController.Button.kB.value);
	private final JoystickButton buttonX = new JoystickButton(controller, XboxController.Button.kX.value);
	private final JoystickButton buttonY = new JoystickButton(controller, XboxController.Button.kY.value);

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
		buttonA.whenPressed(shooterShoot1);
		buttonX.whenPressed(shooterShoot2);
		buttonY.whenPressed(shooterShoot3);
		buttonB.whenPressed(shooterStop);
  	}


 	/**
  	* Use this to pass the autonomous command to the main {@link Robot} class.
  	*
   	* @return the command to run in autonomous
	  */
  	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return new ShooterStop();
	}
}
