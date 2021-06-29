
package gameCode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Frame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel panel;
	JMenuBar menuBar;
	JMenu game;
	JMenuItem newGame;
	JMenuItem exit;
	JMenuItem cancel;
	JMenu difficulty;
	JMenuItem easy;
	JMenuItem medium;
	JMenuItem hard;
	JMenuItem veryHard;
	JMenu snakeColor;
	JMenuItem blue;
	JMenuItem green;
	JMenuItem disco;
	JMenu sound;
	JMenuItem on;
	JMenuItem off;
	
	Frame(){
	
	panel = new Panel();
	 
	menuBar = new JMenuBar(); //the main menu bar
	
	game = new JMenu("Game"); //items of menu bar, the code of game menu begins
	
	newGame = new JMenuItem("New Game"); //items of game menu of menu bar
	exit = new JMenuItem("Exit Game");
	cancel = new JMenuItem("Cancel");
	
	game.add(newGame); 
	game.add(exit);
	game.add(cancel);
	
	newGame.addActionListener(this);
	exit.addActionListener(this);
	
	menuBar.add(game);  //here the game menu code is finished
	
	
	difficulty = new JMenu("Difficulty"); //the code of difficulty menu begins here
	
	easy = new JMenuItem("Easy");
	medium = new JMenuItem("Medium");
	hard = new JMenuItem("Hard");
	veryHard = new JMenuItem("Very Hard");
	
	difficulty.add(easy);
	difficulty.add(medium);
	difficulty.add(hard);
	difficulty.add(veryHard);
	
	easy.addActionListener(this);
	medium.addActionListener(this);
	hard.addActionListener(this);
	veryHard.addActionListener(this);
	menuBar.add(difficulty);
	
	
	snakeColor = new JMenu("Snake"); //snakeColor menu code begins
	
	green = new JMenuItem("Green");
	blue = new JMenuItem("Blue");
	disco = new JMenuItem("Disco");
	
	snakeColor.add(green);
	snakeColor.add(blue);
	snakeColor.add(disco);
	
	green.addActionListener(this);
	blue.addActionListener(this);
	disco.addActionListener(this);
	
	menuBar.add(snakeColor);  //snakeColor menu code finished
	
	sound = new JMenu("Sound"); //sound menu code begins
	
	on =new JMenuItem("ON");
	off = new JMenuItem("OFF");
	
	sound.add(on);
	sound.add(off);
	
	on.addActionListener(this);
	off.addActionListener(this);
	
	menuBar.add(sound); //sound menu code finished
	
	ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("gameLogo.png")); //create an ImageIcon
	this.setIconImage(image.getImage());  // change icon of frame
	
	this.add(panel);
	this.setJMenuBar(menuBar);
	this.setLocationRelativeTo(null);
	this.setTitle("Snakes");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.pack();
	this.setVisible(true);
	this.setLocationRelativeTo(null);
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//game menu of menuBar
		if(e.getSource()==newGame) {
			this.remove(panel);
			panel = new Panel();
			this.add(panel);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==exit) {
			this.dispose();
		}
		
		//difficulty menu of menuBar
		if(e.getSource()==easy) {
			Panel.DELAY = 150;
			this.remove(panel);
			panel = new Panel();
			this.add(panel);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==medium) {
			Panel.DELAY = 100;
			this.remove(panel);
			panel = new Panel();
			this.add(panel);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==hard) {
			Panel.DELAY = 70;
			this.remove(panel);
			panel = new Panel();
			this.add(panel);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==veryHard) {
			Panel.DELAY = 40;
			this.remove(panel);
			panel = new Panel();
			this.add(panel);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}

		//snakeColor menu of menuBar
		if(e.getSource()==green) {
			Panel.snakeColor =0;
		}
		if(e.getSource()==blue) {
			Panel.snakeColor =1;
		}
		if(e.getSource()==disco) {
			Panel.snakeColor =2;
		}
		
		//sound menu of menuBar
		if(e.getSource()==on) {
			Panel.soundEffect =true;
		}
		if(e.getSource()==off) {
			Panel.soundEffect=false;
		}
		
	}

	
}
