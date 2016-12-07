package model;

import java.util.ArrayList;
import java.util.TimerTask;

import controller.packets.OutputThread;

public class GameFinishedTask extends TimerTask{

	private ArrayList<OutputThread> outputThreads;
	private Datas datas;
	public GameFinishedTask(ArrayList<OutputThread> outputThreads,Datas datas){
		this.datas = datas;
		this.outputThreads = outputThreads;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	


}
