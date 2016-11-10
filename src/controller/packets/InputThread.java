package controller.packets;

import java.io.IOException;

import model.Datas;
import model.SocketAction;

public class InputThread implements Runnable{

	private SocketAction socketAction;
	private Datas datas;
	
	public InputThread(SocketAction socketAction, Datas datas){
		this.datas = datas;
		this.socketAction = socketAction;
	}
	
	public void run() {
		
		String s;
		
		while(socketAction.isConnected()){
			
				try {
					s = (String) socketAction.receive();
				} catch (ClassNotFoundException e) {
					System.out.println("Message recu non conforme!");
				} catch (IOException e) {
					socketAction.closeConnections();
					System.out.println("Déconnexion du client");
				}
				
				
				
			
		}
		
		System.out.println("Fermeture de la connexion serveur!");
	}

}
