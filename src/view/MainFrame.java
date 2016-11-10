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

}
