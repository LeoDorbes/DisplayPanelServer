package view;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;

import model.Datas;

public class MainFrame extends JFrame {

	private Datas datas;
	private MainPanel mainPanel;

	public MainFrame(Datas datas) {

		this.datas = datas;
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Panneau d'affichage");
		this.setVisible(true);
		this.mainPanel = new MainPanel(datas);
		this.setContentPane(mainPanel);

	}
	
	public void updateContent(){
		if(datas.isGameOn()){
			
			mainPanel.getLblSportName().setText(datas.getCurrentSportName());
			mainPanel.getLblHomeName().setText(datas.getHomeTeam().getName());
			mainPanel.getLblGuestName().setText(datas.getGuestTeam().getName());
			
			mainPanel.getLblHomeScore().setText(""+datas.getHomeTeam().getScore());
			mainPanel.getLblGuestScore().setText(""+datas.getGuestTeam().getScore());
			
		}else{
			
			mainPanel.getLblSportName().setText("Sport Name");
			mainPanel.getLblHomeName().setText("Home Name");
			mainPanel.getLblGuestName().setText("Guest Name");
			
			mainPanel.getLblHomeScore().setText("0");
			mainPanel.getLblGuestScore().setText("0");
			
		}
	}

}
