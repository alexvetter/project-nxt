package PledgeAlgo;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Drive {
	
	final static DifferentialPilot pilot = new DifferentialPilot(3.0f, 14.7f, Motor.A, Motor.C, false);
    public static boolean isTurn = false;

	public static void driveBackward(int time)
	{

		pilot.setTravelSpeed(30);
		pilot.backward();
    	
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        pilot.stop();
	}
	
	public static void driveForward(int time)
	{
		pilot.setTravelSpeed(30);
		pilot.forward();

		
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
        pilot.stop();
	}
	
	public static void driveForward()
	{
        
		pilot.setTravelSpeed(30);
		pilot.forward();
    	
	}
	
	public static void driveStop()
	{
        
        pilot.stop();
	}
	
	public static void driveRight()
	{
		pilot.setRotateSpeed(100);
		Main.driveCount--;
		//Motor.C.rotate(950);
		pilot.rotate(85);
    	
	}
	
	public static void driveLeft()
	{
		pilot.setRotateSpeed(100);
		Main.driveCount++;
		//Motor.A.rotate(950);
		pilot.rotate(-85);
    	
	}
	
	public static void wait(int time)
	{
        try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	
	public static void checkDistance(int distance)
	{
        if(distance > 30) {
        	isTurn = true;
        	driveLeft();
        	driveForward();
        	
        }
        
        if(isTurn)
        {
        	while(Main.ultrasonicSensor.getDistance() > 30)
        		Drive.wait(100);
        	
        	isTurn = false;
        }        
        
		wait(100);
    	
	}
	

}
