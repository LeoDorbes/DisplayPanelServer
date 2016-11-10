package controller.kernel;

import view.MainFrame;
import controller.packets.OutputThread;
import model.Datas;

public class Main {

	public static void main(String[] args) {
		
		Datas datas = new Datas();
		
		MainFrame mainFrame = new MainFrame(datas);
		Thread newConnectionThread = new Thread(new NewConnectionThread(datas));
		newConnectionThread.start();
		
	}
	
}
