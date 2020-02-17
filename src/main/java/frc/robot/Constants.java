package frc.robot;

import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;

public final class Constants {

    public final static class OI {
        public static final int DRIVER_JOYSTICK = 0;
        public static final int BUTTONS_JOYSTICK = 1;
        public static final double JOYSTICK_DEADBAND = 0.05;
    }

    public final static class CAN {
        public static final int PCM = 1;
        public static final int CHASSIS_DRIVE_MOTOR_LEFT_FRONT = 2;
        public static final int CHASSIS_DRIVE_MOTOR_LEFT_REAR = 3;
        public static final int CHASSIS_DRIVE_MOTOR_RIGHT_FRONT = 4;
        public static final int CHASSIS_DRIVE_MOTOR_RIGHT_REAR = 5;
        public static final int INTAKE_ROLLERS_MOTOR = 6;
        public static final int TURRET_MOTOR = 7;
        public static final int SHOOTER_MOTOR_LEFT = 8;
        public static final int SHOOTER_MOTOR_RIGHT = 9;
        public static final int CONVEYOR_ROLLERS_MOTOR = 10;
        public static final int SHOOTER_FEEDER_MOTOR = 11;
        public static final int CLIMBER_WINCH_MOTOR = 12;
        public static final int SHIMMY_MOTOR = 13;
        public static final int CONTROL_PANEL_WHEEL_MOTOR = 14;
    }

    public final static class PCM {
        public static final int CLIMBER_LOCK = 0;
        public static final int INTAKE_ARM = 1;
        public static final int CONTROL_PANEL_ARM = 2;
        public static final int CLIMBER_ARM_EXTEND = 3;
        public static final int CLIMBER_ARM_RETRACT = 4;
    }

    public final static class PWM {
        public static final int LED = 0;
    }

    public final static class Chassis {
        public static final int CURRENT_LIMIT = 20;

        public static final double TRACK_WIDTH = 0.67; // 26.375 inches in meters
        public static final double WHEEL_DIAMETER = 0.1524; // 6 inches in meters
        public static final double STAGE_1_PINION_TEETH = 12.;
        public static final double STAGE_1_GEAR_TEETH = 50.;
        public static final double STAGE_2_PINION_TEETH = 24.;
        public static final double STAGE_2_GEAR_TEETH = 50.;
		public static final double POSITION_CONVERSION_FACTOR = Math.PI * WHEEL_DIAMETER *
				(STAGE_1_PINION_TEETH / STAGE_1_GEAR_TEETH) *
				(STAGE_2_PINION_TEETH / STAGE_2_GEAR_TEETH);
        public static final double VELOCITY_CONVERSION_FACTOR = POSITION_CONVERSION_FACTOR / 60.;

        public static final double K_P = 1.;
        public static final double K_I = 1.;
        public static final double K_D = 1.;
    }

    public final static class Climber {
        public static final int CURRENT_LIMIT = 20;
    }

    public final static class ControlPanel {
        public static final int CURRENT_LIMIT = 20;
        public static final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public static final Color GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public static final Color RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
        public static final Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113);
    }

    public final static class Conveyor {
        public static final int CURRENT_LIMIT = 20;
    }

    public final static class Intake {
        public static final int CURRENT_LIMIT = 20;
    }

    public final static class LED {
        public static final int NUMBER_LEDS = 150;
        public static final double BLINK_TIME = 0.5;
    }

    public final static class Shimmy {
        public static final int CURRENT_LIMIT = 20;
    }

    public final static class Shooter {
        public static final int CURRENT_LIMIT = 20;
        public static final double K_P = 6.e-5;
        public static final double K_I = 1.e-6;
        public static final double K_D = 1.e-6;
        public static final double K_FF = 1.5e-5;
        public static final double MAX = 1.;
        public static final double MIN = -1.;
        public static final double ALLOWABLE_SHOOTER_ERROR = 50.;
        public static final double SHOOTER_TIME = 0.5;
        public static final double SHOOTER_SPEED = 4000.;
    }

    public final static class ShooterFeeder {
        public static final int CURRENT_LIMIT = 20;
    }

    public final static class Turret {
        public static final int CURRENT_LIMIT = 20;
        public static final double POSITION_CONVERSION_FACTOR = 360.*(18./120.)*(1./100.);
        public static final float MAX_ANGLE = 90.f;
        public static final float MIN_ANGLE = -90.f;
        public static final double MAX_ANGLE_ERROR = 1.;
        public static final double K_P = 1.;
        public static final double K_I = 1.;
        public static final double K_D = 1.;
    }

    public final static class FieldPositions {
        public static final Translation2d TARGET_LOCATION = new Translation2d(0., -2.416);
        public static final Translation2d FIELD_SIZE = new Translation2d(15.98295, -8.21055);
        public static final Pose2d LOADING_LOCATION = new Pose2d(new Translation2d(15.37335, -2.547), new Rotation2d(-1., 0.));
    }
}