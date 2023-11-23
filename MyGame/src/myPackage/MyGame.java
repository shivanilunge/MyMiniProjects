package myPackage;

import javax.swing.JFrame;

public class MyGame {

	public static void main(String[] args) {
		
		JFrame window = new JFrame(); //to create window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to close window when user clicks on 'X'
		window.setResizable(false); //to fix window size
		window.setTitle("Treasure Hunter"); //to set title
		
		GamePanel gp = new GamePanel(); //creating object of GamePanel
		window.add(gp); //adding object to window
		window.pack(); //to see our GamePanel 
		
		window.setLocationRelativeTo(null); //By default, position of window will be in center.
		window.setVisible(true); //to enable visibility of window
		
		gp.setUpGame();
		gp.startGameThread();
		

	}

}
