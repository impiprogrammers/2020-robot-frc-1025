
/*----------------------------------------------------------------------------*/
/*01* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                      */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    // CAN Port IDs
    public final class CAN {
        public static final int PCM_MODULE_PORT = 1;

        // Chassis
        public static final int CHASSIS_LEFT_FRONT_PORT = 2;
        public static final int CHASSIS_LEFT_REAR_PORT = 3;
        public static final int CHASSIS_RIGHT_FRONT_PORT = 4;
        public static final int CHASSIS_RIGHT_REAR_PORT = 5;

        // Intake
        public static final int INTAKE_ROLLERS_PORT = 11;

        // Turret
        public static final int TURRET_MOTOR_PORT = 7;

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
    }

    // PCM Port IDs
    public final class PCM {
        // Intake
        public static final int INTAKE_EXTENDER_CHANNEL = 0;

        // Control Panel
        public static final int CONTROL_PANEL_PISTON_CHANNEL = 1;

        // Climber
        public static final int CLIMBER_LOCK_CHANNEL = 2;
        public static final int CLIMBER_EXTENDER_FORWARD_CHANNEL = 3;
        public static final int CLIMBER_EXTENDER_REVERSE_CHANNEL = 4;
    }

    // USB Port IDs
    public final class OI {
        public static final int XBOX_CONTROLLER_DRIVER = 0;
        public static final int XBOX_CONTROLLER_BUTTONS = 1;
    }

    // Sensor Constants
    public final class Sensor{
        public static final int TUNNEL_SENSOR_PORT = 0;
    }

    // Chassis Constants (No IDs)
    public final class Chassis {
        public static final double CURRENT_LIMIT = 40;
        public static final double AUTO_FFS = 0.2;
        public static final double AUTO_FFV = 2.3;
        public static final double AUTO_FFA = 0.537;
        public static final double AUTO_P = 17.5;
        public static final double AUTO_RAMSETE_B = 2;
        public static final double AUTO_RAMSETE_ZETA = 0.7;

        public static final double TRACK_WIDTH = 0.6699771947874105;
        public static final double WHEEL_DIAMETER = 0.1524;
        public static final double GEAR_RATIO = 8.68;
        public static final boolean GYRO_REVERSED = false;
        public static final double AUTO_PERCENT_OUTPUT = 0.5;
    }

    // Intake Constants (No IDs)
    public final class Intake {
        public static final double AUTO_MIN_INTAKE_DURATION = 2;
    }

    // Conveyor Constants (No IDs)
    public final class Conveyor {
        public static final double AUTO_SPEED = .5;
		public static final double TUNNEL_JAMMED_VOLTAGE = 2;
		public static final double TUNNEL_TIMER_LIMIT = 2500;
    }

    // Shooter Feeder Constants (No IDs)
    public final class ShooterFeeder {
        public static final double AUTO_SPEED = 1;
    }

    // Shooter Constants (No IDs)
    public final class Shooter {
        public static final int CURRENT_LIMIT = 40;
        public static final double SHOOT_P = 0.0001;
        public static final double SHOOT_I = 0.0000001;
        public static final double SHOOT_D = 0;
        public static final double SHOOT_FF = 0.000105;
        public static final double SHOOT_OUTPUT_MIN = -1;
        public static final double SHOOT_OUTPUT_MAX = 1;
        public static final double CHARGE_DURATION = 1;
        public static final double AUTO_SHOOT_DURATION = 5;
        public static final double AUTO_SETPOINT = 4200;
        public static final double TELEOP_SETPOINT = 4500;
    }

    // Turret Constants (No IDs)
    public final class Turret {
        public static final int CURRENT_LIMIT = 20;
        public static final double LEFT_POSITION_LIMIT = 0; // turret left, robot right
        public static final double RIGHT_POSITION_LIMIT = 130; // turret right, robot left
        public static final double MIN_TRACKING_HEIGHT = -20; // tbd
        public static final double AUTO_PERCENT_OUTPUT = 0.5;
    }

}
