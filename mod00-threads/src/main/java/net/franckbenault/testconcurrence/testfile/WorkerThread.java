package net.franckbenault.testconcurrence.testfile;

import java.io.BufferedWriter;
import java.io.IOException;


public class WorkerThread  implements Runnable {

	private int threadNumber;
	private int loopSize;
	private BufferedWriter bw;
	private boolean isSynchronized;
	
	public WorkerThread(int threadNumber, int loopSize, BufferedWriter bw, boolean isSynchronized) {
		
		this.threadNumber = threadNumber;
		this.loopSize = loopSize;
		this.bw = bw;
		this.isSynchronized=isSynchronized;
	}
	
	public void run() {
		
		for(int i=1; i<=loopSize; i++) {
			try {
				if(isSynchronized) {
					synchronized(bw) {
						writeLine(bw);
					}
				} else {
					 writeLine(bw);
				}
				
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeLine(BufferedWriter bw) {
		try {
			bw.write("something");
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String command(int i) {
		return "Callable thread "+threadNumber+"-loopNumber "+i;
	}
	
	public String toString() {
		return "Thread "+threadNumber;
	}

}
