package org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;


public class Winch {
	SpeedController WinchController;
	public Winch(int channel) {
		// TODO Auto-generated constructor stub
		WinchController = new Talon(channel);
	}
	public void setWinchSpeed(double winchSpeed){
		this.WinchController.set(winchSpeed);
		
	}
}
