package view;

import javax.swing.JPanel;

import model.Datas;

public class MainPanel extends JPanel{
	
	private Datas datas;
	
	public MainPanel(Datas datas) {
		this.datas = datas;
		this.setSize(800, 550);
		setLayout(null);
	}
}
