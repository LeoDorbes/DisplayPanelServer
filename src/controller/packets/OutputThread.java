package controller.packets;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import model.Datas;
import model.SocketAction;

public class OutputThread implements Runnable {

	private int index;
	private boolean closeThread;
	private LinkedBlockingQueue<String> outputQueue;
	private SocketAction socketAction;
	private Datas datas;

	public OutputThread(SocketAction socketAction, Datas datas) {

		this.closeThread = false;
		this.outputQueue = new LinkedBlockingQueue<String>();
		this.datas = datas;
		this.socketAction = socketAction;
	}

	public void run() {
		String cmd;
		while (!closeThread) {
			try {
				cmd = outputQueue.take();

				if (cmd.equalsIgnoreCase("fin connexion")) {
					this.closeThread = true;
				} else {
					socketAction.send(cmd);
					System.out.println("Sent by server (' " +cmd +" ') from OutputThread : (' "+this.index + " ')");
				}

				
			} catch (InterruptedException e) {
				this.closeThread = true;
			}
		}

		System.out.println("Fermeture du thread d'écoute : " + this.index);
	
		datas.removeOutputThreadFromList(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public LinkedBlockingQueue<String> getOutputQueue() {
		return outputQueue;
	}

	public void setOutputQueue(LinkedBlockingQueue<String> outputQueue) {
		this.outputQueue = outputQueue;
	}

}
