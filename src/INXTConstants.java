
public interface INXTConstants {
	
	
	/**
	 * Geschwindigkeit der Motoren
	 * Unterschiedliche Werte da nicht alle Motoren gleich schnell sind...
	 * Muss getestet werden!	  
	 */
	public static int MOTOR_C_SPEED = 500;  
	public static int MOTOR_B_SPEED = 505;
	
	
	/**
	 * Geschwindigkeit beim rotieren
	 * 
	 */
	public static int ROTATE_SPEED = 50;
	public static int ROTATE_ANGLE = 93;
	
	
	/**
	 * Distanz Werte für den Ultraschallsensor
	 */
	public static int MAX_LEFT_DISTANCE = 40;
	public static int MAX_ULTRASONIC_DISTANCE = 30;
	public static int START_ULTRASONIC_DISTANCE = 20;
	
	
	/**
	 *  Tastsensor Aktivierungswert
	 */
	public static int BUMPER_VALUE = 1000;
	
	
	/**
	 *  Verschiedene Wartezeiten
	 */	
	public static int WAIT_TIME_VERY_SHORT = 100;
	public static int WAIT_TIME_SHORT = 250;
	public static int WAIT_TIME_MIDDLE = 300;
	public static int WAIT_TIME_LONG = 500;
	public static int WAIT_TIME_VERY_LONG = 600;
	
	

}
