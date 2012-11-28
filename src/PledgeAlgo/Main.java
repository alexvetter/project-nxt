package PledgeAlgo;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class Main {
	
	
	static boolean isStop = false;
	static boolean isExit = false;
	static boolean startUltrasonic = false;

	static int turnCount = 0;
	public static TouchSensor touchSensor1 = new TouchSensor(SensorPort.S1);
	public static TouchSensor touchSensor2 = new TouchSensor(SensorPort.S2);
	public static UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(SensorPort.S3);
	
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	System.out.println("start");
    	
        SensorPort.S1.addSensorPortListener(new SensorPortListener()
        {
	        public void stateChanged(SensorPort aSource, int aOldValue,int aNewValue)
	        {

		        	if(aNewValue < 1000 && aOldValue != 0 && isStop == false)
		        	{
		        		isStop = true;
		        		Drive.ultrasonicDisable = false;
	                    Drive.driveStop();  
		                Drive.driveBackward(250);
		                Drive.driveRight();
		                Drive.driveForward();
		                //startUltrasonic = true;
		                isStop = false;
		        	}	
	            	
	           
	        			

	        }
        });
        
        SensorPort.S2.addSensorPortListener(new SensorPortListener()
        {
	        public void stateChanged(SensorPort aSource, int aOldValue,int aNewValue)
	        {
	        	if(aNewValue < 1000 && aOldValue != 0 && isStop == false)
	        	{
	        		isStop = true;
	        		Drive.ultrasonicDisable = false;
                    Drive.driveStop();  
	                Drive.driveBackward(250);
	                Drive.driveRight();
	                //startUltrasonic = true;
	                isStop = false;
	        	}	
	        	
	        }
        });
    	                	
        SensorPort.S1.activate();
        SensorPort.S2.activate();
        SensorPort.S3.activate();        
    	
        Motor.A.forward();
    	Motor.C.forward();
    	
    	Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				LCD.drawString("Stopping...", 0, 0);
				Drive.driveStop();
			}

			public void buttonReleased(Button b) {
				isExit = true;
				LCD.clear();
				System.exit(0);
			}
		});
    	
    	
		
		/*while (true) {
			Drive.driveStop();
			//pilot.travel(50.0f);
			Drive.wait(1000);
			Drive.driveLeft();
		}*/
        
        
        while(isExit == false){        	
           
            if(ultrasonicSensor.getDistance() < 20 && Drive.ultrasonicDisable == false){
            	startUltrasonic = true;
            }
            
        	if(startUltrasonic) {
            	Drive.checkDistance(ultrasonicSensor.getDistance());
            }
            else
            	Drive.wait(300);	
        	                    
         }

        	
        //Button.waitForAnyPress();
    }
}
