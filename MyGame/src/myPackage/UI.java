package myPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import items.Key;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	Font fontForMsg;
	Font congratesFont;
	BufferedImage keyImg;
	public boolean msgOn = false;
	public String msg = "";
	int msgCount = 0;
	public boolean gameOver = false;
	
	public UI(GamePanel gp)
	{
		this.gp = gp;
		arial_40 = new Font("Arial", Font.BOLD, 30);
		fontForMsg = new Font("Arial", Font.BOLD, 20);
		congratesFont = new Font("Arial", Font.BOLD, 40);
		Key key = new Key();
		keyImg = key.bImg;
	}
	
	public void showMsg(String text)
	{
		msg = text;
		msgOn = true;
	}
	
	public void draw(Graphics2D g2) {
		if (gameOver == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x,y;
			
			text = "You Found Treasure";
			//to find center to display congrates msg
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //return length og the text
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - gp.tileSize*2;
			g2.drawString(text, x, y);

			g2.setFont(congratesFont);
			g2.setColor(Color.yellow);
			text = "Congrates Treasure Hunter! You Won!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //return length og the text
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + gp.tileSize*2;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		} else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImg, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.p.hasKey, 68, 65);
			msgCount++;

			// msg
			if (msgOn == true) {
				g2.setFont(fontForMsg);
				g2.drawString(msg, 35, 100);
				msgCount++;
			}
			if (msgCount > 150) // after 120 frames
			{
				msgCount = 0;
				msgOn = false;
			}
		}

	}
}
