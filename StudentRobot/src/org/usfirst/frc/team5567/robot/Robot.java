package org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	int FrontLeft=0;
	int FrontRight=1;
	int RearLeft=2;
	int RearRight=3;
	SpeedController FLController = new Talon(FrontLeft);
	SpeedController FRController = new Victor(FrontRight);
	SpeedController RLController = new Talon(RearLeft);
	SpeedController RRController = new Talon(RearRight);
	Winch robotWinch = new Winch(4);
	Shooter robotShoooter = new Shooter(5,6);
	RobotDrive Team = new RobotDrive(FLController,FRController,RLController,RRController);
	Timer Alpha = new Timer();
	XboxController CBeta = new XboxController(0);
	XboxController CBravo = new XboxController(1);
	double Delta = 0.1;
	
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		Team.setInvertedMotor(MotorType.kFrontLeft,true);
		Team.setInvertedMotor(MotorType.kRearLeft,true);
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		this.Alpha.reset();
		this.Alpha.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			this.Alpha.get();
			if (Alpha.get() <= 5.00){
				Team.mecanumDrive_Cartesian(0, 0.5, 0, 0);
			}
			else {
				Team.mecanumDrive_Cartesian(0, 0, 0, 0);
			}
				
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double XLIn = this.CBeta.getX(Hand.kLeft);
		double YLIn = this.CBeta.getY(Hand.kLeft);
		double LtIn = this.CBeta.getTriggerAxis(Hand.kLeft);
		double RtIn = this.CBeta.getTriggerAxis(Hand.kRight);
		double XLIn2 = this.CBeta.getX(Hand.kRight);
		double TrigDiff = (RtIn-LtIn);
		double Rotation = 0.00;
		if (XLIn < Delta && XLIn > -Delta){
			XLIn = 0;
		}
		if (YLIn < Delta && YLIn > -Delta){
			YLIn = 0;
		}
		if (XLIn2 < Delta && XLIn2 > -Delta){
			XLIn2 = 0;
		}
// TODO: Rotation selection--Port options to dashboard?
// **Could use button to change rotation input		
		if (Math.abs(XLIn2) > Math.abs(TrigDiff)){
			Rotation = XLIn2;
		}
		else {
			Rotation = TrigDiff;
		}
		Team.mecanumDrive_Cartesian(XLIn, YLIn, Rotation, 0);
		double BLtIn = this.CBravo.getTriggerAxis(Hand.kLeft);
//		double BRtIn = this.CBravo.getTriggerAxis(Hand.kRight);
		boolean BRbIn = this.CBravo.getBumper(Hand.kRight);
		this.robotWinch.setWinchSpeed(BLtIn);
		this.robotShoooter.turnShooterOn(BRbIn);
//		if(BRbIn == true){
//			this.robotShoooter.setShooterSpeed(1.00);
//		}
//		else{
//			this.robotShoooter.setShooterSpeed(0.00);
//		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

