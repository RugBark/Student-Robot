package org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;


public class Winch {
	SpeedController winchController;
	public Winch(int channel) {
		// TODO Auto-generated constructor stub
		winchController = new Talon(channel);
	}
	/**
	 * @param winchSpeed
	 */
	public void setWinchSpeed(double winchSpeed){
		this.winchController.set(winchSpeed);
		
	}
}
