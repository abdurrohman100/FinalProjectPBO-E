package com.fp.spacewar.main;

import com.fp.spacewar.main.Game.GameState;

public class TimerSendiri implements Runnable{
	private long second;
	private long i;
	private void counter() throws InterruptedException {
		
			for (i =0; ;i++) {
				second = i;
				Thread.sleep(1000);
				System.out.println("second" + second);
			}
		
		
	}
	void timerReset() {
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
			System.out.println(Game.currentGameState);
			//if(Game.currentGameState==GameState.IN_PLAY) {
				
				System.out.println("SASUU");
				counter();
			//}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Timer error");
			e.printStackTrace();
		}
		
	}


}
