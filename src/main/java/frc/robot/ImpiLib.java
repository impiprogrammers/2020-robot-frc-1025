package frc.robot;

public class ImpiLib {
	public static double clamp(double max, double min, double input) {
		if (input > max) {
			return max;
		} else if (input < min) {
			return min;
		} else {
			return input;
		}
	}

	public static double signedSquare(double input) {
		return Math.abs(input) * input;
	}

	public static double deadzone(double input, double deadzone) {
		if (((input > 0) && (input < deadzone)) || ((input < 0) && (input > -deadzone))) {
			return 0.0;
		} else
			return input;
	}

}
