package controller.kernel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.packets.InputThread;
import controller.packets.OutputThread;
import model.SocketAction;
import model.global.Datas;

/**
 * Thread in charge of the connections to the serverSocket, accept them and
 * convert them in two new threads
 * 
 * @author L�o Dorbes
 * @version 1.0 08 Dec, 2016.
 */
public class NewConnectionThread implements Runnable {

	private Datas datas;
	private ServerSocket serverSocket;

	/**
	 * The constructor for NewConnectionThread
	 * 
	 * @param datas
	 *            accessor to the datas from the server
	 */
	public NewConnectionThread(Datas datas) {
		this.datas = datas;
		try {
			this.serverSocket = new ServerSocket(datas.getPort());
		} catch (IOException e) {
			System.out.println("Impossible de d�marrer le serveur d'�coute avec le port : " + datas.getPort());
			e.printStackTrace();
		}
	}

	/**
	 * The run method for the NewConnectionThread
	 */
	public void run() {

		System.out.println("Initialisation du serveur --- Ecoute du port : " + datas.getPort());

		while (datas.isServerOn()) {
			try {
				System.out.println("En attente de connexion.");
				Socket socket = serverSocket.accept();
				SocketAction socketAction = new SocketAction(socket);
				System.out.println("Nouvelle connexion detect�e!");

				OutputThread opt = new OutputThread(socketAction, datas);
				datas.getOutputThreads().add(opt);
				int index = datas.getOutputThreads().indexOf(opt);
				opt.setIndex(index);
				Thread outputThread = new Thread(opt);
				outputThread.start();
				Thread inputThread = new Thread(new InputThread(socketAction, datas, opt));
				inputThread.start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
