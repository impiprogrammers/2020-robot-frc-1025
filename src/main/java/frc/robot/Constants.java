package frc.robot;

public final class Constants {

    public final class OI {
        public static final int DRIVER_JOYSTICK = 0;
        public static final int BUTTONS_JOYSTICK = 1;
        public static final double JOYSTICK_DEADZONE = 0.05;
    }

    public final class CAN {
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

    public final class PCM {
        public static final int CLIMBER_LOCK = 0;
        public static final int INTAKE_ARM = 1;
        public static final int CONTROL_PANEL_ARM = 2;
        public static final int CLIMBER_ARM_EXTEND = 3;
        public static final int CLIMBER_ARM_RETRACT = 4;
    }

    public final class PWM {
        public static final int LED = 0;
    }

    public final class Chassis {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class Climber {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class ControlPanel {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class Conveyor {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class Intake {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class LED {
        public static final int NUMBER_LEDS = 150;
        public static final double BLINK_TIME = 0.5;
    }

    public final class Shimmy {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class Shooter {
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

    public final class ShooterFeeder {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class Turret {
        public static final int CURRENT_LIMIT = 20;
        public static final double CONVERSION_FACTOR = 360.*(18./120.)*(1./100.);
        public static final double MAX_ANGLE = 90.;
        public static final double MIN_ANGLE = -90.;
        public static final double MAX_ANGLE_ERROR = 1.;
    }
}