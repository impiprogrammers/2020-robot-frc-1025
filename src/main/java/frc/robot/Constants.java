
/*----------------------------------------------------------------------------*/
/*01* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                      */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // OI
    public static final int XBOX_CONTROLLER_DRIVER = 0;
    public static final int XBOX_CONTROLLER_BUTTONS = 1;

    // CAN
    public static final int PCM_MODULE_PORT = 1;

    // Chassis
    public static final int CHASSIS_LEFT_FRONT_PORT = 2;
    public static final int CHASSIS_LEFT_REAR_PORT = 3;
    public static final int CHASSIS_RIGHT_FRONT_PORT = 4;
    public static final int CHASSIS_RIGHT_REAR_PORT = 5;

    public static final double CHASSIS_AUTO_FFS = 0.243;
    public static final double CHASSIS_AUTO_FFV = 3.86;
    public static final double CHASSIS_AUTO_FFA = 1.18;
    public static final double CHASSIS_AUTO_P = 23.3;
    public static final double CHASSIS_AUTO_RAMSETE_B = 2;
    public static final double CHASSIS_AUTO_RAMSETE_ZETA = 0.7;

    public static final double CHASSIS_TRACK_WIDTH = 0.4166967348481668;
    public static final double CHASSIS_WHEEL_DIAMETER = 0.125;
    public static final double CHASSIS_GEAR_RATIO = 12;
    public static final boolean CHASSIS_GYRO_REVERSED = false;
    
    // Intake
    public static final int INTAKE_ROLLERS_PORT = 11;

    // Turret
    public static final int TURRET_ROTATE_PORT = 7;
    public static final double TURRET_LEFT_LIMIT = 0; // turret left, robot right
    public static final double TURRET_RIGHT_LIMIT = 130; // turret right, robot left
    public static final double TURRET_MIN_TRACKING_HEIGHT = 0; // tbd
    
    // Shooter
    public static final int SHOOTER_LEFT_PORT = 8;
    public static final int SHOOTER_RIGHT_PORT = 9;
    
    // Conveyor
    public static final int CONVEYOR_ROLLERS_PORT = 10;

    // Shooter Feeder
    public static final int SHOOTER_FEEDER_PORT = 6;

    // Climber
    public static final int CLIMBER_WINCH_PORT = 12;

    // Shimmy
    public static final int CLMBER_SHIMMY_PORT = 13;

    // Control Panel
	public static final int CONTROL_PANEL_WHEEL_PORT = 14;

    // PCM
    public static final int INTAKE_EXTENDER_CHANNEL = 0;
    public static final int CONTROL_PANEL_PISTON_CHANNEL = 1;
    public static final int CLIMBER_LOCK_CHANNEL = 2;
    public static final int CLIMBER_EXTENDER_FORWARD_CHANNEL = 3;
    public static final int CLIMBER_EXTENDER_REVERSE_CHANNEL = 4;

    // Auto Settings
    public static final double AUTO_SHOOT_DURATION = 5;
    public static final double AUTO_SHOOT_SETPOINT = 4000;
    public static final double AUTO_INTAKE_MIN_DURATION = 2;
}

