package controller.packets;

import model.Datas;
import model.SocketAction;

public class OutputThread implements Runnable {
	
	private SocketAction socketAction;
	private Datas datas;
	
	public OutputThread(SocketAction socketAction, Datas datas){
		this.datas = datas;
		this.socketAction = socketAction;
	}
	
	public void run() {
		
	}
	
}


