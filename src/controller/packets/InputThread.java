package controller.packets;

import java.io.IOException;

import controller.actions.GameActions;
import model.SocketAction;
import model.global.Datas;

/**
 * InputThread is the thread in charge of receiving all the datas from the
 * client it's connected to. It's also charged to identify the data and
 * determine wich method should be executed depending on what is received.
 * 
 * @author Léo Dorbes
 * @version 1.0 01 Dec, 2016.
 */
public class InputThread implements Runnable {

	private SocketAction socketAction;
	private Datas datas;
	private OutputThread outputThread;

	/**
	 * Constructor fot the InputThread
	 * 
	 * @param socketAction
	 *            The socketAction containing the connection to the client.
	 * @param datas
	 *            accessor to the datas from the server
	 * @param outputThread
	 *            The outputThread related to the same client.
	 */
	public InputThread(SocketAction socketAction, Datas datas, OutputThread outputThread) {
		this.outputThread = outputThread;
		this.datas = datas;
		this.socketAction = socketAction;
	}

	/**
	 * The run method for the InputThread
	 */
	public void run() {

		String s;
		String[] firstString;

		while (socketAction.isConnected()) {

			try {
				s = (String) socketAction.receive();
				System.out.println("recu : " + s);

				firstString = s.split(Datas.separator);

				switch (firstString[0].toLowerCase()) {
				case "new game":
					GameActions.newGame(s, datas, outputThread.getIndex());
					break;
				case "cancel game":
					GameActions.cancelGame(datas, outputThread.getIndex());
					break;
				case "get game":
					GameActions.sendGame(datas, outputThread.getIndex());
					break;

				case "pause game":
					GameActions.pauseGame(datas);
					break;

				case "home 1":
					GameActions.score(datas, 0, 1);
					break;
				case "home 2":
					GameActions.score(datas, 0, 2);
					break;
				case "home 3":
					GameActions.score(datas, 0, 3);
					break;
				case "guest 1":
					GameActions.score(datas, 1, 1);
					break;
				case "guest 2":
					GameActions.score(datas, 1, 2);
					break;
				case "guest 3":
					GameActions.score(datas, 1, 3);
					break;

				default:
					break;
				}

			} catch (ClassNotFoundException e) {
				System.out.println("Received message cannot be read by the server InputThread");
			} catch (IOException e) {
				socketAction.closeConnections();
				System.out.println("Lost connection with client");
			}

			datas.getMainFrame().updateContent();

		}
		outputThread.getOutputQueue().add("fin connexion");

		System.out.println("Fermeture de la connexion serveur!");
	}

}
