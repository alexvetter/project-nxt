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
	
	final static DifferentialPilot pilot = new DifferentialPilot(3.4f, 17.75f, Motor.A, Motor.C, false);
    public static boolean isTurn = false;
    public static boolean ultrasonicDisable = false;

	public static void driveBackward(int time)
	{

		pilot.setTravelSpeed(15);
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
		pilot.setTravelSpeed(15);
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
        
		pilot.setTravelSpeed(15);
		pilot.forward();
    	
	}
	
	public static void driveStop()
	{
        
        pilot.stop();
	}
	
	public static void driveRight()
	{
		pilot.setRotateSpeed(50);
		Main.turnCount--;
		pilot.rotate(90);
        Drive.driveForward();
		checkTurnCount();
		//Motor.C.rotate(950);
		
    	
	}
	
	public static void driveLeft()
	{
		pilot.setRotateSpeed(50);
		Main.turnCount++;
		pilot.rotate(-90);
    	driveForward();
		checkTurnCount();
		//Motor.A.rotate(950);
		
    	
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
        
		
		if(distance > 40) {
        	Drive.wait(600);
        	driveStop();
        	isTurn = true;
        	driveLeft();
        	
        }
        
        if(isTurn)
        {
        	while(Main.ultrasonicSensor.getDistance() > 30)
        		Drive.wait(100);
        	
        	isTurn = false;
        }        
        
		wait(300);
    	
	}
	
	public static void checkTurnCount()
	{
      if(Main.turnCount == 0){
    	  Main.startUltrasonic = false;
    	  ultrasonicDisable = true;
    	  System.out.println("turnCount is null");
      }
      else {
    	  
    	  while(Main.ultrasonicSensor.getDistance() > 30)
    		  wait(100); 
    	  
    	  Main.startUltrasonic = true; 
      }	
	}
	

}
