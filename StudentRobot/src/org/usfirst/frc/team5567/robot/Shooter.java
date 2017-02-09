package org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	SpeedController ShooterController;
	public Shooter(int channel1,int channel2) {
		// TODO Auto-generated constructor stub
		ShooterController = new Talon(channel1);
		//TODO: Assign controller\channel to ball release
	}
//	public void setShooterSpeed(double ShooterSpeed){
//		this.ShooterController.set(ShooterSpeed);
//	}
	public void turnShooterOn(boolean ShooterOn){
		if(ShooterOn == true){
			this.ShooterController.set(1.00);
		}
		else{
			this.ShooterController.set(0.00);
		}
	}
	public void releaseBall(boolean BallRelease){
		if(BallRelease == true){
			//TODO: Put code to release one ball here
		}
	}
}
