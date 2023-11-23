package myPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
import items.Item;
import tile.TileManager;
import myPackage.UI;

//This GamePanel is going to work as a game screen
public class GamePanel extends JPanel implements Runnable{

	//Screen Settings
	final int originalTileSize = 16; //to set size of 16*16 for any mob or entity in the game
	
	//All PC have different resolution so 16*16 might look small on some displays so we are scaling it
	final int scale = 3; 
	
	public final int tileSize = originalTileSize * scale; //16 * 3(scale) = 48 i.e. entity became 48*48
	
	public final int maxScreenCols = 16; //to set max no of columns window can have
	
	public final int maxScreenRows = 12; //to set max no of rows window can have
	
	public final int screenWidth = tileSize * maxScreenCols; // 48 * 16 = 768 px
	
	public final int screenHeight = tileSize * maxScreenRows; // 48 * 12 = 576 px
	
	//world map settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	int fps = 60; //60 new Drawings per second
	
	//Instanciation of all classes
	TileManager tm = new TileManager(this);
	KeyHandler kh = new KeyHandler(); //created object for cls
	Sound sound = new Sound();
	Sound effect = new Sound();
	
	public CollisionChecker cc = new CollisionChecker(this);
	public ItemPositionSetter ips = new ItemPositionSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; //to repeat some process again and again, like to, show moving images (basically to set timing) //this requires implementing Runnable<I>
	
	public Player p = new Player(this,kh);
	public Item item[] = new Item[10]; //we can add 10 items at a time on the screen

	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //method from JPanel to set size for this class panel (i.e 768 px * 576 px)
		this.setBackground(Color.black); //to set BG color for our game panel
		this.setDoubleBuffered(true); // 'true' to have all drawing from the component will be done in an off-screen painting buffer (good for smooth rendering)
		this.addKeyListener(kh); //Now our gamePanel call recognize key input from keyboard
		this.setFocusable(true); //So that GamePanel can receive key input
	}
	
	public  void setUpGame()
	{
		ips.setItem();
		
		playMusic(0); //calling method to play music present on 0th index in array
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this); //to instanced our gameThread, we pass instance of our current class i.e GamePanel to Thread
		gameThread.start(); //will automatically call run();
	}
		
	//In this method, We are going to create our game loop
	public void run() //Whenever we start gameThread, automatically this method will get called
	{
		
		double interval = 1000000000/fps; //dividing original fps in 60 fps to get visible update 
		double delta = 0;
		long lastTime = System.nanoTime(); //time taken by loop till now
		long currentTime;
		
		//To check fps
		long timer = 0;
		long updatecount = 0;
		
		while(gameThread != null) //untill process continues
		{
			//while running the loop, we have to update some info, such as position of our entity and Display the screen with the updated info
//			//to do that we create two methods 1)update 2)paintComponent
//			
//			//for each interval(i.e.entity drawing) this update and repaint happens
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / interval; // 0 + (ns - ns)/0.001666
			
			timer += (currentTime - lastTime); //for fps
			
			lastTime = currentTime;
			
			if(delta >= 1) 
			{
				update();
				repaint();
				delta--;
				updatecount++;//to update fps
			}
			
			if(timer >= 1000000000)
			{
				System.out.println("FPS : " + updatecount);
				//resetting
				updatecount = 0;
				timer = 0;
			}
		}
		update();
		repaint(); //to draw a screen with updated info
	}
	
	public void update()
	{
		//here we change our player positions
		p.update();
		
	}
	
	public void paintComponent(Graphics g) // this is an build in method from java and Graphic is build in class(Used to
											// draw)
	{
		super.paintComponent(g); // calls parent class(JPanel) method

		Graphics2D g2 = (Graphics2D) g; // converting Graphics cls to Graphics2D cls bcoz Graphics2D has more functions
										// to work with

		// tile
		tm.draw(g2);

		// item
		for (int i = 0; i < item.length; i++) {
			if (item[i] != null) {
				item[i].draw(g2, this);
			}
		}

		// player
		p.draw(g2);
		
		//UI
		ui.draw(g2);
		
		g2.dispose(); // to save memory weastage
	}
	
	//for music
	public void playMusic(int i)
	{
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic()
	{
		sound.stop();
	}
	
	public void soundEffect(int i)
	{
		effect.setFile(i);
		effect.play();
	}
}
