package org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	SpeedController ShooterController;
	public Shooter(int channel) {
		// TODO Auto-generated constructor stub
		ShooterController = new Talon(channel);
	}
	public void setShooterSpeed(double ShooterSpeed){
		this.ShooterController.set(ShooterSpeed);
	}
}
