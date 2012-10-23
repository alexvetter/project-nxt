package projectnxt;

import lejos.nxt.*;

public class HelloWorld {
	public static void main (String[] args) throws Exception {
		System.out.println("Hello World");
		Motor.A.forward();
		Motor.B.forward();
		Thread.sleep(3000);
		Motor.A.forward();
		Motor.B.backward();
		Thread.sleep(1000);

		Motor.A.forward();
		Motor.B.forward();
		Thread.sleep(3000);
		
		Motor.A.stop();
		Motor.B.stop();
	}
}
