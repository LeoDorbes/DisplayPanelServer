package controller.packets;

import java.io.IOException;

import controller.actions.GameActions;
import model.Datas;
import model.SocketAction;

public class InputThread implements Runnable{

	private SocketAction socketAction;
	private Datas datas;
	private OutputThread outputThread;
	
	public InputThread(SocketAction socketAction, Datas datas, OutputThread outputThread){
		this.outputThread = outputThread;
		this.datas = datas;
		this.socketAction = socketAction;
	}
	
	public void run() {
		
		String s;
		String[] firstString;
		
		while(socketAction.isConnected()){
			
				try {
					s = (String) socketAction.receive();
					System.out.println("recu : "+s);
					
					firstString = s.split(Datas.separator);
					
					switch (firstString[0].toLowerCase()) {
					case "new game":
						GameActions.newGame(s,datas,outputThread.getIndex());
						break;
					case "end game": //This should be sendable from the client @TODO (work somewhere else?)
						GameActions.endGame(datas);
						break;
					case "cancel game":
						GameActions.cancelGame(datas,outputThread.getIndex());
						break;
					case "get game":
						GameActions.sendGame(datas, outputThread.getIndex());
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
