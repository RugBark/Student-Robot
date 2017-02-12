package org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Matt
 *
 */
public class Shooter {
	SpeedController shooterController;

	/**
	 * Constructor that initializes Shooter with a 
	 * new Talon named shooterController
	 * @param channel1 PWM channel for shooter motor controller
	 * @param channel2 PWM channel for ball release (currently unused)
	 */
	public Shooter(int channel1,int channel2) {
		// TODO Auto-generated constructor stub
		shooterController = new Talon(channel1);
		//TODO: Assign controller\channel to ball release
	}
	
	public void turnShooterOn(boolean ShooterOn){
		if(ShooterOn == true){
			this.shooterController.set(1.00);
		}
		else{
			this.shooterController.set(0.00);
		}
	}
	
	public void releaseBall(boolean BallRelease){
		if(BallRelease == true){
			//TODO: Put code to release one ball here
		}
	}
}
