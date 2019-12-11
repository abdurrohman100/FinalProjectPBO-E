package com.fp.spacewar.main;

import com.fp.spacewar.main.Game.GameState;

public class TimerSendiri implements Runnable{
	private long second;
	private long i;
	private void counter() throws InterruptedException {
			boolean status = Game.currentGameState==GameState.IN_PLAY;
			for (i =0;;i++) {
				
				System.out.println(status);
				status=Game.currentGameState==GameState.IN_PLAY;
				
				second = i;
				Thread.sleep(1000);
				System.out.println("second" + second);
			}
		
		
	}
	void timerReset() {
		System.out.println("Reset");
		second=0;
		i=0;
	}
	public long getTime() {
//		System.out.println("panggil waktu" + second);
		return second;
	}
	
	@Override
	public void run() {
		try {
			
			//while(true) {
				//if(Game.currentGameState==GameState.IN_PLAY) {
					
					//System.out.println("SASUU");
					counter();
				//}
			//}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Timer error");
			e.printStackTrace();
		}
		
	}


}
