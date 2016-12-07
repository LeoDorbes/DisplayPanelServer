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
			System.out.println("Impossible de démarrer le serveur d'écoute avec le port : "+datas.getPort());
			e.printStackTrace();
		}
	}

	public void run() {
		
		System.out.println("Initialisation du serveur --- Ecoute du port : "+datas.getPort());
		
		while (datas.isServerOn()) {
			try {
				System.out.println("En attente de connexion.");
				Socket socket = serverSocket.accept();
				SocketAction socketAction = new SocketAction(socket);
				System.out.println("Nouvelle connexion detectée!");
				
				OutputThread opt = new OutputThread(socketAction,datas); 
				datas.getOutputThreads().add(opt);
				int index = datas.getOutputThreads().indexOf(opt);
				opt.setIndex(index);
				Thread outputThread = new Thread(opt);
				outputThread.start();
				Thread inputThread = new Thread(new InputThread(socketAction,datas,opt));
				inputThread.start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
