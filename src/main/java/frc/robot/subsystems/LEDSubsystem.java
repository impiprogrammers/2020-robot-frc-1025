package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDSubsystem extends SubsystemBase {

    private final AddressableLED led = new AddressableLED(Constants.PWM.LED);
    private final AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(Constants.LED.NUMBER_LEDS);

    private int rgb[] = {0, 0, 0};
    private boolean blinking = false;
    private boolean blinkStatus = true;
    private boolean leftOn = false;
    private boolean rightOn = false;

    private Timer timer = new Timer();

    public LEDSubsystem() {
        led.setLength(ledBuffer.getLength());
        led.setData(ledBuffer);
        led.start();
    }

    public void setBlinking() {
        if (!blinking) {
            blinking = true;
            blinkStatus = true;
            timer.reset();
        }
    }

    public void setSolid() {
        blinking = false;
    }

    public boolean getBlinking() {
        return blinking;
    }

    public void turnOff() {
        leftOn = false;
        rightOn = false;
    }

    public void turnOn() {
        leftOn = true;
        rightOn = true;
    }

    public void turnOn(boolean left, boolean right) {
        leftOn = left;
        rightOn = right;
    }

    public void setRGB(int red, int green, int blue) {
        rgb[0] = red;
        rgb[1] = green;
        rgb[2] = blue;
    }

    @Override
    public void periodic() {
        // Handle lights on left side
        if ((leftOn) && ((!blinking) || (blinking && blinkStatus))) {
            for (var ii=0; ii< Math.floor(ledBuffer.getLength() / 2.); ii++) {
                ledBuffer.setRGB(ii, rgb[0], rgb[1], rgb[2]);
            }
        } else {
            for (var ii=0; ii<Math.floor(ledBuffer.getLength() / 2.); ii++) {
                ledBuffer.setRGB(ii, 0, 0, 0);
            }
        }

        // Handle lights on right side
        if ((rightOn) && ((!blinking) || (blinking && blinkStatus))) {
            for (var ii=(int) (ledBuffer.getLength() / 2.); ii<ledBuffer.getLength(); ii++) {
                ledBuffer.setRGB(ii, rgb[0], rgb[1], rgb[2]);
            }
        } else {
            for (var ii=(int) (ledBuffer.getLength() / 2.); ii<ledBuffer.getLength(); ii++) {
                ledBuffer.setRGB(ii, 0, 0, 0);
            }
        }
        led.setData(ledBuffer);

        // Turn on or off lights if they are supposed to be blinking
        if (blinking) {
            if (timer.get() >= Constants.LED.BLINK_TIME) {
                timer.reset();
                blinkStatus = !blinkStatus;
            }
        }
    }
}
