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

    public static double clampedDeadzone(double value, double deadzone, double min, double max) {
        return clamp(deadzone(value, deadzone), min, max);
    }
}