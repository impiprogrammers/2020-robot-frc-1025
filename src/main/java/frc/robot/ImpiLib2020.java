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
    
    // Useful in most cases. Doesn't have to always be used.
    public static double parseJoystick(double value, double deadzone) {
        return signedSquare(clamp(deadzone(value, 0.05), -1, 1));
    }

    public static double parseTrigger(double value, double deadzone) {
        return signedSquare(clamp(deadzone(value, 0.05), 0, 1));
    }

}