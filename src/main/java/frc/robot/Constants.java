package frc.robot;

public final class Constants {

    public final class OI {
        public static final int DRIVER_JOYSTICK = 0;
        public static final int BUTTONS_JOYSTICK = 1;
    }

    public final class CAN {
        public static final int CHASSIS_DRIVE_MOTOR_LEFT_FRONT = 0;
        public static final int CHASSIS_DRIVE_MOTOR_LEFT_REAR = 1;
        public static final int CHASSIS_DRIVE_MOTOR_RIGHT_FRONT = 2;
        public static final int CHASSIS_DRIVE_MOTOR_RIGHT_REAR = 3;
        public static final int SHOOTER_MOTOR_LEFT = 4;
        public static final int SHOOTER_MOTOR_RIGHT = 5;
        public static final int CONVEYOR_ROLLERS_MOTOR = 6;
        public static final int INTAKE_ROLLERS_MOTOR = 7;
        public static final int SHOOTER_FEEDER_MOTOR = 8;
        public static final int TURRET_MOTOR = 9;
        public static final int CLIMBER_WINCH_MOTOR = 10;
        public static final int SHIMMY_MOTOR = 11;
        public static final int CONTROL_PANEL_WHEEL_MOTOR = 12;
    }

    public final class PCM {
        public static final int INTAKE_ARM = 0;
        public static final int CLIMBER_ARM_EXTEND = 1;
        public static final int CLIMBER_ARM_RETRACT = 2;
        public static final int CLIMBER_LOCK = 3;
        public static final int CONTROL_PANEL_ARM = 4;
    }

    public final class Chassis {
        public static final int CURRENT_LIMIT = 20;
        public static final double JOYSTICK_DEADZONE = 0.05;
    }

    public final class Climber {
        public static final double JOYSTICK_DEADZONE = 0.05;
    }

    public final class Shimmy {
        public static final double JOYSTICK_DEADZONE = 0.05;
    }

    public final class Shooter {
        public static final int CURRENT_LIMIT = 20;
    }

    public final class Turret {
        public static final int CURRENT_LIMIT = 20;
        public static final double JOYSTICK_DEADZONE = 0.05;
    }
}