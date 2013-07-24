import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class NXTDrive implements INXTConstants {
	
	
	/**
	 * Pilot der die Fahrfunktionen uebernimmt (Parameter: Abmessungen des Roboters, Motoren)
	 */
	public final static DifferentialPilot pilot = new DifferentialPilot(3.4f, 17.75f, Motor.B, Motor.C, false); 
	
	/**
	 * TouchSensor 1
	 */
	public static TouchSensor touchSensor1 = new TouchSensor(SensorPort.S1);  
	
	/**
	 *  TouchSensor 2
	 */
	public static TouchSensor touchSensor2 = new TouchSensor(SensorPort.S2); 
	
	/**
	 * UltraschallSensor
	 */
	public static UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(SensorPort.S3); 
	
	
    static boolean isTurn = false; // gerade am Kurve fahren
    static boolean ultrasonicDisable = false; // UltraschallSensor deaktiviert      
	static boolean isStop = false;
	static boolean isExit = false;
	static boolean startUltrasonic = false;
	static int turnCount = 0; // Count fuer den Pledge-Algo

    
    

	/**
	 * Static-Konstruktor zum initialisieren der Listener und aktivieren der Sensorports
	 * 
	 */
    static
    {

        // fuegt einen Listener am Sensorport 1 hinzu
    	SensorPort.S1.addSensorPortListener(new SensorPortListener()
        {
	        public void stateChanged(SensorPort aSource, int aOldValue,int aNewValue)
	        {


	        	    if(aNewValue < BUMPER_VALUE && aOldValue != 0 && isStop == false)
		        	{
		        		isStop = true;
		        		NXTDrive.ultrasonicDisable = false;
		        		NXTDrive.wait(WAIT_TIME_LONG);
	                    NXTDrive.driveStop();  
		                NXTDrive.driveBackward(WAIT_TIME_SHORT);
		                NXTDrive.driveRight();
		                NXTDrive.driveForward();
		                isStop = false;
		        	}	
	            	
	        }
        });
        
    	// fuegt einen Listener am Sensorport 2 hinzu
        SensorPort.S2.addSensorPortListener(new SensorPortListener()
        {
	        public void stateChanged(SensorPort aSource, int aOldValue,int aNewValue)
	        {
	        	if(aNewValue < BUMPER_VALUE && aOldValue != 0 && isStop == false)
	        	{
	        		isStop = true;
	        		NXTDrive.ultrasonicDisable = false;
	        		NXTDrive.wait(WAIT_TIME_LONG);
                    NXTDrive.driveStop();  
	                NXTDrive.driveBackward(WAIT_TIME_SHORT);
	                NXTDrive.driveRight();
	                isStop = false;
	        	}	
	        	
	        }
        });	
        
     // fuegt einen Listener am ESCAPE-Button hinzu
    	Button.ESCAPE.addButtonListener(new ButtonListener()
    	{
			public void buttonPressed(Button b)
			{
				LCD.drawString("Stopping...", 0, 0);
				NXTDrive.driveStop();
			}

			public void buttonReleased(Button b)
			{
				isExit = true;
				LCD.clear();
				System.exit(0);
			}
		});
    	
    	// aktiviert die Sensorports
        SensorPort.S1.activate();
        SensorPort.S2.activate();
        SensorPort.S3.activate();  
    	
    }
    
    
    
    /**
     * Startet den Pledge-Algorithmus
     * 
     */
	public static void startPledge()
	{
		
        while(isExit == false){        	
            
            if(ultrasonicSensor.getDistance() < START_ULTRASONIC_DISTANCE && NXTDrive.ultrasonicDisable == false)
            {
            	startUltrasonic = true;
            }
            
        	if(startUltrasonic)
        	{
            	NXTDrive.checkDistance(ultrasonicSensor.getDistance());
            }
            else
            	NXTDrive.wait(WAIT_TIME_MIDDLE);	
        	                    
         }		
	}
	
	
    
	/**
	 * Faehrt eine bestimmte Zeit zurueck und stoppt anschließend
	 * 
	 * @param time Zeit die er zurueck fahren soll in Millisekunden
	 */
	public static void driveBackward(int time)
	{

		setDefaultSpeed();
		pilot.backward();
    	
    	try
    	{
			Thread.sleep(time);
		}
    	catch (InterruptedException e)
    	{
			e.printStackTrace();
		}
    	
        pilot.stop();
	}
	
	
	
	/**
	 * Faehrt eine bestimmte Zeit vorwaerts und stoppt anschließend
	 * 
	 * @param time Zeit die er vorwaerts fahren soll in Millisekunden
	 */
	public static void driveForward(int time)
	{
		setDefaultSpeed();
		pilot.forward();

		
    	try
    	{
			Thread.sleep(time);
		}
    	catch (InterruptedException e)
    	{
			e.printStackTrace();
		}
    	
        
        pilot.stop();
	}
	
	
	/**
	 * Setzt die Standardgeschwindigkeit der beiden Motoren
	 * 
	 */
	public static void setDefaultSpeed()
	{
		Motor.C.setSpeed(MOTOR_C_SPEED);
		Motor.B.setSpeed(MOTOR_B_SPEED);
	}
	
	
	/**
	 * Faehrt vorwaerts und stoppt nicht mehr automatisch
	 * 
	 */	
	public static void driveForward()
	{
        setDefaultSpeed();
		pilot.forward();
    	
	}
	
	
	/**
	 * Stoppt den Roboter
	 * 
	 */
	public static void driveStop()
	{
        pilot.stop();
	}
	
	/**
	 * Biegt rechts ab und faehrt dann weiter geradeaus.
	 * Prueft zusaetzlich ob der Ultraschallsensor deaktiviert werden soll (fuer den Pledge-Algo)
	 * 
	 */
	public static void driveRight()
	{
		pilot.setRotateSpeed(ROTATE_SPEED);
		turnCount--;
		pilot.rotate(ROTATE_ANGLE);
        NXTDrive.driveForward();
		checkTurnCount();
		
    	
	}
	
	
	/**
	 * Biegt links ab und faehrt dann weiter geradeaus.
	 * Prueft zusaetzlich ob der Ultraschallsensor deaktiviert werden soll (fuer den Pledge-Algo)
	 * 
	 */
	public static void driveLeft()
	{
		pilot.setRotateSpeed(ROTATE_SPEED);
		turnCount++;
		pilot.rotate(-ROTATE_ANGLE);
    	driveForward();
		checkTurnCount();		
    	
	}
	
	
	
	/**
	 * Unterbricht den NXT-Code eine bestimmte Zeit, der aktuelle Vorgang wie z.B. geradeausfahren wird
	 * trotzdem fortgesetzt. 
	 * 
	 * @param time Zeit die er warten soll in Millisekunden
	 */
	public static void wait(int time)
	{
        try
        {
			Thread.sleep(time);
		}
        catch (InterruptedException e)
		{

			e.printStackTrace();
		}
    	
	}
	
	
	
	/**
	 *  Ueberprueft die Distanz vom Roboter bis zur linken Mauer, wenn bestimmter Wert
	 *  ueberschritten wird dann biegt er links ab
	 * 
	 * @param distance  Distanz vom Ultraschallsensor
	 */
	public static void checkDistance(int distance)
	{        
		
		if(distance > MAX_LEFT_DISTANCE)
		{
        	NXTDrive.wait(WAIT_TIME_VERY_LONG);
        	driveStop();
        	isTurn = true;
        	driveLeft();
        	
        }
        
        if(isTurn)
        {
        	while(ultrasonicSensor.getDistance() > MAX_ULTRASONIC_DISTANCE)
        		NXTDrive.wait(WAIT_TIME_VERY_SHORT);
        	
        	isTurn = false;
        }        
        
		wait(WAIT_TIME_MIDDLE);
    	
	}
	
	
	/**
	 * Ueberprueft den TurnCount (des Pledge-Algos) und deaktiviert wenn noetig den Ultraschallsensor
	 * damit der NXT nicht im Kreis faehrt
	 * 
	 */	
	public static void checkTurnCount()
	{
      if(turnCount == 0){
    	  startUltrasonic = false;
    	  ultrasonicDisable = true;
      }
      else
      {
    	  
    	  while(ultrasonicSensor.getDistance() > MAX_ULTRASONIC_DISTANCE)
    		  wait(WAIT_TIME_VERY_SHORT); 
    	  
    	  startUltrasonic = true; 
      }	
	}
	

}
