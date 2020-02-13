package frc.robot;

public class ImpiLib2020 {

    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static double signedSquare(double value) {
        return Math.abs(value) * value;
    }

    public static double deadzone(double value, double deadzone) {
        if (value > -deadzone && value < deadzone) {
            return 0;
        }
        return value;
    }

    public static double parseJoystick(double value) {
        return clamp(signedSquare(deadzone(value, 0.05)), -1, 1);
    }

    public static double parseJoystick(double value, double deadzone) {
        return clamp(signedSquare(deadzone(value, deadzone)), -1, 1);
    }

    public static double parseTrigger(double value) {
        return clamp(signedSquare(deadzone(value, 0.05)), 0, 1);
    }

    public static double parseTrigger(double value, double deadzone) {
        return clamp(signedSquare(deadzone(value, deadzone)), 0, 1);
    }

}