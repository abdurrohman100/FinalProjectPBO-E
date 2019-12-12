package com.fp.spacewar.main;

public class TimerSendiri implements Runnable{
	private long second;
	private long i;
	
	/**
	 * Counter timer dengan sleep 1000 milisecond
	 * @throws InterruptedException
	 */
	private void counter() throws InterruptedException {
			for (i =0;;i++) {
				second = i;
				Thread.sleep(1000);
			}
	}
	/**
	 * Reset timer
	 */
	void timerReset() {
		System.out.println("Reset");
		second=0;
		i=0;
	}
	
	/**
	 *Runnable yang akan dijalankan oleh thread ini
	 */
	@Override
	public void run() {
		try {
			counter();
			
		} catch (InterruptedException e) {
			System.out.println("Timer error");
			e.printStackTrace();
		}
		
	}

	//Getter
	public long getTime() {
		return second;
	}

}
