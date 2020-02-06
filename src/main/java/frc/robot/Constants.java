/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
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
        
    
    public final class OI{
        public static final int XBOX_CONTROLLER_DRIVER = 0;
        public static final int XBOX_CONTROLLER_BUTTONS = 1;
    }
     public final class Chassis {
        public static final int CHASSIS_LEFT_FRONT_PORT = 1;
        public static final int CHASSIS_RIGHT_FRONT_PORT = 2;
        public static final int CHASSIS_LEFT_REAR_PORT = 3;
        public static final int CHASSIS_RIGHT_REAR_PORT = 4;
        public static final double K_P = 1.;
     }
    public final class Intake{
        public static final int INTAKE_EXTENDER_MODULE = 4;
        public static final int INTAKE_ROLLERS_PORT = 5;
    }
    public final class Shooter{
        public static final int SHOOTER_LEFT_PORT = 7;
        public static final int SHOOTER_RIGHT_PORT = 8;
        public static final double K_P = 1.;
    }
    public final class Conveyor{
        public static final int CONVEYOR_ROLLERS_PORT = 11;
    }
    public final class Shooter_Feeder{
        public static final int SHOOTER_FEEDER_PORT = 12;
    }
    public final class Climber{
        public static final int CLIMBER_EXTENDER_MODULE = 13;
        public static final int CLIMBER_LOCK_MODULE = 14;
        public static final int CLIMBER_WINCH_PORT = 15;
        public static final int CLMBER_SHIMMY_PORT = 16;
    }
    public final class Control_Panel{
        public static final int CONTROL_PANEL_WHEEL_PORT = 18;
        public static final int CONTROL_PANEL_PISTON_MODULE = 19;
    }
    public final class PCM{
        public static final int INTAKE_EXTENDER_CHANNEL = 0;
    
        public static final int CLIMBER_EXTENDER_CHANNEL = 1;
        public static final int CLIMBER_LOCK_CHANNEL = 2;

        public static final int CONTROL_PANEL_PISTON_CHANNEL = 3;
    }
    
    public final class PWM{
        public static final int LED_LIGHTS = 0;
        public static final int FLASH_LIGHT = 1;
    }


}

