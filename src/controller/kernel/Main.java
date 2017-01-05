package controller.kernel;

import view.MainFrame;
import controller.packets.OutputThread;
import model.global.Datas;

/**
 * Main, handle the launching and initialization of the server.
 * 
 * @author Léo Dorbes
 * @version 1.0 01 Dec, 2016.
 */
public class Main {

	public static void main(String[] args) {
		
		Datas datas = new Datas();
		
		MainFrame mainFrame = new MainFrame(datas);
		datas.setMainFrame(mainFrame);
		Thread newConnectionThread = new Thread(new NewConnectionThread(datas));
		newConnectionThread.start();
		
	}
	
}
