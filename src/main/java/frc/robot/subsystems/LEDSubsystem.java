/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;


public class LEDSubsystem extends SubsystemBase {
  /**
   * Creates a new LEDSubsystem.
   */

  private AddressableLED led = new AddressableLED(0);
	private static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(150);
  private static int timer = 0;
  
  public LEDSubsystem() {
    led.setLength(ledBuffer.getLength());

		led.setData(ledBuffer);
		led.start();

  }

  public void updateLEDs() {}


  public void setLEDsOff() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0, 0, 0);
    }
    led.setData(ledBuffer);
	}

	public void setLEDsSolidOrange() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 30 / 2, 255, 255);
    }
    led.setData(ledBuffer);
	}

	public void setLEDsFlashBlueRight() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				// ledBuffer.setHSV(i, 0, 0, 0);
				if (i < ledBuffer.getLength() / 2) {
					ledBuffer.setHSV(i, 225 / 2, 255, 255);
				} else {
					ledBuffer.setHSV(i, 0 / 2, 0, 0);
				}
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0 / 2, 0, 0);
			}
		}
		if (timer >= 50) {
			timer = 0;
    }
    led.setData(ledBuffer);
	}

	public void setLEDsFlashBlueleft() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < (ledBuffer.getLength()); i++) {
				// ledBuffer.setHSV(i, 0, 0, 0);
				if (i > (ledBuffer.getLength() / 2)) {
					ledBuffer.setHSV(i, 225 / 2, 255, 255);
				} else {
					ledBuffer.setHSV(i, 0 / 2, 0, 0);
				}
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0 / 2, 0, 0);
			}
		}
		if (timer >= 50) {
			timer = 0;
    }
    led.setData(ledBuffer);
	}

	public void setLEDsSolidBlueRight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// ledBuffer.setHSV(i, 0, 0, 0);
			if (i < ledBuffer.getLength() / 2) {
				ledBuffer.setHSV(i, 225 / 2, 255, 255);
			} else {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
    }
    led.setData(ledBuffer);
	}

	public void setLEDsSolidBlueLeft() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// ledBuffer.setHSV(i, 0, 0, 0);
			if (i > ledBuffer.getLength() / 2) {
				ledBuffer.setHSV(i, 225 / 2, 255, 255);
			} else {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
    }
    led.setData(ledBuffer);
	}

	public void setLEDsFlashGreen() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 115 / 2, 255, 255);
			}
		}
		if (timer >= 50) {
			timer = 0;
    }
    led.setData(ledBuffer);
	}

	public void setLEDsSolidGreen() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 115 / 2, 255, 255);
    }
    led.setData(ledBuffer);
	}

	public void setLEDsFlashRed() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0 / 2, 255, 255);
			}
		}
		if (timer >= 50) {
			timer = 0;
    }
    led.setData(ledBuffer);
	}

	public void setLEDsSolidRed() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 0 / 2, 255, 255);
    }
    led.setData(ledBuffer);
	}

	public void setLEDsFlashYellow() {
		timer++;
		if (timer > 25) {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 0, 0, 0);
			}
		} else {
			for (var i = 0; i < ledBuffer.getLength(); i++) {
				ledBuffer.setHSV(i, 42 / 2, 255, 255);
			}
		}
		if (timer >= 50) {
			timer = 0;
    }
    led.setData(ledBuffer);
	}

	public void setLEDsSolidYellow() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, 42 / 2, 255, 255);
    }
    led.setData(ledBuffer);
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
/*
/give @p minecraft:netherite_sword {Enchantment:[{id:sharpness,lvl:32767}]}
*/