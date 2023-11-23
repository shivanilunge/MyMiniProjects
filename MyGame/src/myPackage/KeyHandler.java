package myPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//To take input from keybord to move our entity
public class KeyHandler implements KeyListener{  //KeyListener is used to perform keyboard events

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); //getKeyCode() method returns the key value associated with keys on the keyboard
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
