package main.util.timer;

public class TimerUtil {
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
