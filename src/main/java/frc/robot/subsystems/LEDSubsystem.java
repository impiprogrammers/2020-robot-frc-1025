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
  
  public LEDSubsystem() {
    led.setLength(ledBuffer.getLength());

		led.setData(ledBuffer);
		led.start();
  }

  public void setLEDBuffer() {
	  led.setData(ledBuffer);
  }

  public void setLEDsTop(int h, int s, int v) {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		if ((i >= 1 && i <= 32) || (i >= 79 && i <= 111)) {
			ledBuffer.setHSV(i, h, s, v);
		}
	}
  }
  
  public void setLEDsBottom(int h, int s, int v) {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		if ((i >= 40 && i <= 72) || (i >= 119 && i <= 150)) {
			ledBuffer.setHSV(i, h, s, v);
		}
	}
  }
 
  public void setLEDsTopRight(int h, int s, int v) {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		if ((i >= 1 && i <= 14) || (i >= 79 && i <= 93)) {
			ledBuffer.setHSV(i, h, s, v);
		}
	}
	setLEDsTopLeft(0, 0, 0);
  }
  
  public void setLEDsTopLeft(int h, int s, int v) {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		if ((i >= 15 && i <= 32) || (i >= 94 && i <= 111)) {
			ledBuffer.setHSV(i, h, s, v);
		}
	}
	setLEDsTopRight(0, 0, 0);
  }

  public void setLEDsBottomRight(int h, int s, int v) {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		if ((i >= 57 && i <= 72) || (i >= 136 && i <= 150)) {
			ledBuffer.setHSV(i, h, s, v);
		}
	}
	setLEDsBottomLeft(0, 0, 0);
  }

  public void setLEDsBottomLeft(int h, int s, int v) {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		if ((i >= 40 && i <= 56) || (i >= 119 && i <= 135)) {
			ledBuffer.setHSV(i, h, s, v);
		}
	}
	setLEDsBottomRight(0, 0, 0);
  }

  public void setLEDsOff() {
	for (var i = 0; i < ledBuffer.getLength(); i++) {
		ledBuffer.setHSV(i, 0, 0, 0);
    }
  }
}