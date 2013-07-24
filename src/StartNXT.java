
public class StartNXT  {
	
	
    /**
     * Einstiegspunkt des NXTs
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
    	System.out.println("start NXT"); 
        
        NXTDrive.driveForward();
        NXTDrive.startPledge();

        
    }
}
