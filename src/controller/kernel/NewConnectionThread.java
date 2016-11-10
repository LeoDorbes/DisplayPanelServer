package controller.kernel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.packets.InputThread;
import controller.packets.OutputThread;
import model.Datas;
import model.SocketAction;

//Cette classe est en charge de gerer les nouvelles connexions:
public class NewConnectionThread implements Runnable {

	private Datas datas;
	private ServerSocket serverSocket;

	public NewConnectionThread(Datas datas) {
		this.datas = datas;
		try {
			this.serverSocket = new ServerSocket(datas.getPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		
		while (datas.isServerOn()) {
			System.out.println("Initialisation du serveur --- Ecoute du port : "+datas.getPort());
			try {
				System.out.println("En attente de connexion.");
				Socket socket = serverSocket.accept();
				SocketAction socketAction = new SocketAction(socket);
				System.out.println("Nouvelle connexion detectée!");
				
				Thread outputThread = new Thread(new OutputThread(socketAction,datas));
				outputThread.start();
				Thread inputThread = new Thread(new InputThread(socketAction,datas));
				inputThread.start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
