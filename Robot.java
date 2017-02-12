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
	
	//Driver options chooser
	//TODO: Verify driver order
	final String defaultDriver = "Parker";
	final String driverTwo = "Taylor";
	final String driverThree = "Michael";
	String driverSelected;
	SendableChooser<String> driverSelect = new SendableChooser<>();
	
	//Setting drivetrain motor controllers to PWM Ports 
	int FrontLeft=0;
	int FrontRight=1;
	int RearLeft=2;
	int RearRight=3;
	
	//Setting the winch motor controller to a PWM port
	int winchController = 4;
	
	//Setting the shooter motor controller and the ball release motor controller to a PWM port
	int shooterController = 5;
	int ballReleaseController = 6;
	
	//Setting Xbox controllers to USB ports
	int pilotUSBPort = 0;
	int copilotUSBPort = 1;
	
	//Creating speed controllers for the drivetrain & assigning new instance of the corresponding motor controllers 
	//initializing with which PWM channel to use
	SpeedController FLController = new Talon(FrontLeft);
	SpeedController FRController = new Victor(FrontRight);
	SpeedController RLController = new Talon(RearLeft);
	SpeedController RRController = new Talon(RearRight);
	
	//Creating a winch & initializing with the PWM port set by winchController
	Winch robotWinch = new Winch(winchController);
	
	//Creating a shooter & initializing with the PWM ports set by shooterController & ballReleaseController
	Shooter robotShoooter = new Shooter(shooterController,ballReleaseController);
	
	//Creating a new RobotDrive & initializing with the speed controllers
	RobotDrive myRobotDrive = new RobotDrive(FLController,FRController,RLController,RRController);
	
	//Creating a timer for elapsed time
	Timer myTimer = new Timer();
	
	//Initializing controllers to the correct USB ports
	XboxController pilotController = new XboxController(pilotUSBPort);
	XboxController copilotController = new XboxController(copilotUSBPort);
	
	//Deadzone threshold for controller
	//Prevents unintentional drifting 
	double threshold = 0.1;
	
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		//Creates driver selection on dashboard
		driverSelect.addDefault("Parker", defaultDriver);
		driverSelect.addObject("Taylor", driverTwo);
		driverSelect.addObject("Michael", driverThree);
		SmartDashboard.putData("Driver Selection", driverSelect);
		
		
		myRobotDrive.setInvertedMotor(MotorType.kFrontLeft,true);
		myRobotDrive.setInvertedMotor(MotorType.kRearLeft,true);
		
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
		this.myTimer.reset();
		this.myTimer.start();
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
			this.myTimer.get();
			if (myTimer.get() <= 5.00){
				myRobotDrive.mecanumDrive_Cartesian(0, 0.5, 0, 0);
			}
			else {
				myRobotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
			}
				
			break;
		}
	}

	
	@Override
	public void teleopInit(){
		driverSelected = driverSelect.getSelected();
		System.out.println("Driver selected: " + driverSelected);
	}
	
	
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double XLIn = this.pilotController.getX(Hand.kLeft);
		double YLIn = this.pilotController.getY(Hand.kLeft);
		double LtIn = this.pilotController.getTriggerAxis(Hand.kLeft);
		double RtIn = this.pilotController.getTriggerAxis(Hand.kRight);
		double XLIn2 = this.pilotController.getX(Hand.kRight);
		double TrigDiff = (RtIn-LtIn);
		double Rotation = 0.00;
		if (XLIn < threshold && XLIn > -threshold){
			XLIn = 0;
		}
		if (YLIn < threshold && YLIn > -threshold){
			YLIn = 0;
		}
		if (XLIn2 < threshold && XLIn2 > -threshold){
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
		myRobotDrive.mecanumDrive_Cartesian(XLIn, YLIn, Rotation, 0);
		double BLtIn = this.copilotController.getTriggerAxis(Hand.kLeft);
//		double BRtIn = this.copilotController.getTriggerAxis(Hand.kRight);
		boolean BRbIn = this.copilotController.getBumper(Hand.kRight);
		this.robotWinch.setWinchSpeed(BLtIn);
		this.robotShoooter.turnShooterOn(BRbIn);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

