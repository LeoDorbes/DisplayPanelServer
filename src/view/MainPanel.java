package view;

import javax.swing.JPanel;

import model.Datas;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainPanel extends JPanel{
	
	private Datas datas;
	private JLabel lblHomeName;
	private JLabel lblGuestName;
	private JLabel lblHomeScore;
	private JLabel lblGuestScore;
	private JLabel lblTimer;
	private JLabel lblTimerCount;
	private JLabel lblSportName;
	
	public MainPanel(Datas datas) {
		this.datas = datas;
		this.setSize(600, 400);
		setLayout(null);
		
		this.lblHomeName = new JLabel("Home Name");
		lblHomeName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeName.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHomeName.setBounds(10, 110, 200, 75);
		add(lblHomeName);
		
		this.lblGuestName = new JLabel("Guest Name");
		lblGuestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestName.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblGuestName.setBounds(390, 110, 200, 75);
		add(lblGuestName);
		
		this.lblHomeScore = new JLabel("0");
		lblHomeScore.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHomeScore.setBounds(84, 164, 115, 43);
		add(lblHomeScore);
		
		this.lblGuestScore = new JLabel("0");
		lblGuestScore.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGuestScore.setBounds(485, 164, 115, 43);
		add(lblGuestScore);
		
		this.lblTimer = new JLabel("Timer :");
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblTimer.setBounds(141, 287, 115, 43);
		add(lblTimer);
		
		this.lblTimerCount = new JLabel("0");
		lblTimerCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimerCount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTimerCount.setBounds(266, 289, 115, 43);
		add(lblTimerCount);
		
		lblSportName = new JLabel("Sport Name");
		lblSportName.setForeground(Color.BLUE);
		lblSportName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSportName.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblSportName.setBounds(200, 11, 200, 75);
		add(lblSportName);
	}

	public Datas getDatas() {
		return datas;
	}

	public void setDatas(Datas datas) {
		this.datas = datas;
	}

	public JLabel getLblHomeName() {
		return lblHomeName;
	}

	public void setLblHomeName(JLabel lblHomeName) {
		this.lblHomeName = lblHomeName;
	}

	public JLabel getLblGuestName() {
		return lblGuestName;
	}

	public void setLblGuestName(JLabel lblGuestName) {
		this.lblGuestName = lblGuestName;
	}

	public JLabel getLblHomeScore() {
		return lblHomeScore;
	}

	public void setLblHomeScore(JLabel lblHomeScore) {
		this.lblHomeScore = lblHomeScore;
	}

	public JLabel getLblGuestScore() {
		return lblGuestScore;
	}

	public void setLblGuestScore(JLabel lblGuestScore) {
		this.lblGuestScore = lblGuestScore;
	}

	public JLabel getLblTimer() {
		return lblTimer;
	}

	public void setLblTimer(JLabel lblTimer) {
		this.lblTimer = lblTimer;
	}

	public JLabel getLblTimerCount() {
		return lblTimerCount;
	}

	public void setLblTimerCount(JLabel lblTimerCount) {
		this.lblTimerCount = lblTimerCount;
	}

	public JLabel getLblSportName() {
		return lblSportName;
	}

	public void setLblSportName(JLabel lblSportName) {
		this.lblSportName = lblSportName;
	}
	
	
}
