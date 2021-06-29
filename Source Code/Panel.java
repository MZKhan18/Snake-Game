package gameCode;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.Random;

public class Panel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int SCREEN_WIDTH = 1530;  //width of the screen
	static final int SCREEN_HEIGHT = 770; //height of screen
	static final int UNIT_SIZE = 30; //size of objects in the game or size of snake
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE); //units of game
	static int DELAY = 150; //sets the speed of snake (lesser the delay value, faster will be the speed)
	final int x[] = new int[GAME_UNITS]; //this array will store x coordinate of all the snake parts
	final int y[] = new int[GAME_UNITS]; //this array will store y coordinate of all the snake parts
	int bodyParts = 5;  //initial amount of body parts of snake or initial length of snake
	int applesEaten; // the number of apples eaten
	int appleX;  // the position where apple is present X-Coordinate
	int appleY; // the position where apple is present Y-Coordinate
	char direction = 'R'; //direction of snake ('R' represents RIGHT, so initially the snake is moving right)
	boolean running = false; //check whether snake is running or not
	Timer timer;  //timer class
	Random random;  //random class to generate apples randomly
	boolean gameOn = false; 
	static int snakeColor =0;
	static boolean soundEffect = true;  //static variable can be used in other class also
		
	Panel(){ //the constructor class 
		random = new Random();
		Border border = BorderFactory.createLineBorder(Color.green,3);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); //size of panel
		this.setBackground(new Color(44,40,40)); 
		this.setFocusable(true);
		this.setBorder(border);
		this.addKeyListener(new MyKeyAdapter());  //adding key listener to panel
		startGame(); //this will start the game
		
	}
	
	public void startGame() {   //method to start game
     	newApple(); //when we start the game it will call newApple method i.e a new apple
		running = true; //keep the snake running
		timer = new Timer(DELAY,this); 
		timer.start(); //now starting the timer
	
}
		
	public void pause() { //method to pause the game
		gameOn = true;
		timer.stop();
	}
	
	public void resume() { //method to resume the game
		gameOn =false;
		timer.start();
	}
	
	public void paintComponent(Graphics g) {   //method to paint component with graphic g parameter
		super.paintComponent(g);   //painting the components or properties of panel
		draw(g);
	}
	
	public void draw(Graphics g) {   //method to draw components
		
		if(running) { //IF THE SNAKE IS RUNNING
			
			
			//this loop will draw some grids on the panel
//			for(int i=0;i<SCREEN_WIDTH/UNIT_SIZE;i++) { //i will iterate until it is under the screen width size
//				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT); //using drawLine method
//				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
//			}
			
			
			g.setColor(Color.red); //color of apple
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);  //this will fill color in oval shape, with its dimensions and size
		
			for(int i = 0; i< bodyParts;i++) { //iterate to all body part
			if(snakeColor==0) {
				if(i == 0) { //i =0 means the first part or head of snake
					g.setColor(Color.green); //color of snake
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //shape of snake
				}
				else {
					g.setColor(new Color(45,180,0));
					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); //for multiple colors
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}			
			}else if(snakeColor == 1) {
				if(i == 0) { //i =0 means the first part or head of snake
					g.setColor(Color.blue); //color of snake
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //shape of snake
				}
				else {
					g.setColor(new Color(0,200,250));
					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); //for multiple colors
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}else if(snakeColor==2) {
				if(i == 0) { //i =0 means the first part or head of snake
					g.setColor(Color.blue); //color of snake
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //shape of snake
				}
				else {
//					g.setColor(new Color(0,200,250));
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); //for multiple colors
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
		}
			g.setColor(Color.red); //this will display score while the snake is running
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
		else {//ELSE PRINT GAME OVER
			gameOver(g);
		}
		
	}
	
	
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;   //creating apple somewhere in the x axis
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;	//creating apple somewhere in the y axis
	}
	
	
	public void move(){  //method to move snake
		for(int i = bodyParts;i>0;i--) {  //for loop to iterate to all body 
			x[i] = x[i-1];       //here we're shifting all the components of array to left
			y[i] = y[i-1];
		}
		
		switch(direction) {  //this will switch the direction of snake
		case 'U': //U = up
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D': //D=down
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L': //L=left
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R': //R=right
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	
	
	public void checkApple() throws UnsupportedAudioFileException, IOException, LineUnavailableException {  //check if snake eats the apple
		
		
		if((x[0] == appleX) && (y[0] == appleY)) { //check that whether the head of snake collides with apple or not
			bodyParts++;
			applesEaten++;
			newApple();
			if(soundEffect) {
				URL file ;
				file = getClass().getClassLoader().getResource("points1.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(file);
				Clip score = AudioSystem.getClip();
				score.open(audio);	
			    score.start();	
			}
		}
		
	}
	
	
	public void checkCollisions() throws UnsupportedAudioFileException, IOException, LineUnavailableException {  //method to check collision
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) { 
			if((x[0] == x[i])&& (y[0] == y[i])) {  //if the position of head overlaps with the body then stop snake game over
				running = false;
				if(soundEffect) {
					URL file1;
					file1 =getClass().getClassLoader().getResource("collision.wav");
					AudioInputStream endAudio = AudioSystem.getAudioInputStream(file1);  //endAudio is variable name of our file
					Clip end= AudioSystem.getClip(); //end is the variable name of clip
					end.open(endAudio);
					end.start();
				}
			
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
			if(soundEffect) {
				URL file1;
				file1 =getClass().getClassLoader().getResource("collision.wav");
				AudioInputStream endAudio = AudioSystem.getAudioInputStream(file1);  //endAudio is variable name of our file
				Clip end= AudioSystem.getClip(); //end is the variable name of clip
				end.open(endAudio);
				end.start();
				}
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH-4) {
			running = false;
			if(soundEffect) { 
				URL file1;
				file1 =getClass().getClassLoader().getResource("collision.wav");
				AudioInputStream endAudio = AudioSystem.getAudioInputStream(file1);  //endAudio is variable name of our file
				Clip end= AudioSystem.getClip(); //end is the variable name of clip
				end.open(endAudio);
				end.start();
			}
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
			if(soundEffect) {
				URL file1;
				file1 =getClass().getClassLoader().getResource("collision.wav");
				AudioInputStream endAudio = AudioSystem.getAudioInputStream(file1);  //endAudio is variable name of our file
				Clip end= AudioSystem.getClip(); //end is the variable name of clip
				end.open(endAudio);
				end.start();
			}
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT-4) {
			running = false;
			if(soundEffect) { 
				URL file1;
				file1 =getClass().getClassLoader().getResource("collision.wav");
				AudioInputStream endAudio = AudioSystem.getAudioInputStream(file1);  //endAudio is variable name of our file
				Clip end= AudioSystem.getClip(); //end is the variable name of clip
				end.open(endAudio);
				end.start();
			}
		}
		
		if(!running) {
			timer.stop();
			
		}
		
		
	}
	
	
	public void gameOver(Graphics g) {    //method to declare game over
		//Score
		g.setColor(Color.red); //font color of score
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont()); //font metrics help to line up text to the center or screen
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) { //the action performed method
		
		if(running) { //if running i.e if snake is moving
			move(); //then move the snake
			
			
			try { //check if snake eats apple
				checkApple();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			
			
			try { //check if snake makes any collisions
				checkCollisions();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		repaint();
		
		
	}
	
	
	public class MyKeyAdapter extends KeyAdapter{  //inner class
		@Override
		public void keyPressed(KeyEvent e) { 
				
						
			switch(e.getKeyCode()) { //this will examine the key events
			case KeyEvent.VK_SPACE:
				if(gameOn) {
					resume(); //this will resume game
				}else {
					pause(); //this will pause game
				}
				break;
			case KeyEvent.VK_NUMPAD4:
				if(direction != 'R') { //if left key pressed and snake is not moving to right then turn left
					direction = 'L';
				}
			case KeyEvent.VK_LEFT:
				if(direction != 'R') { //if left key pressed and snake is not moving to right then turn left
					direction = 'L';
				}
				break;
			case KeyEvent.VK_NUMPAD6:
				if(direction != 'L') {
					direction = 'R';
				}
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_NUMPAD8:
				if(direction != 'D') {
					direction = 'U';
				}
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_NUMPAD2:
				if(direction != 'U') {
					direction = 'D';
				}
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}
