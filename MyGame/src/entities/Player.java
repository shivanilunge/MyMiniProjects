package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import myPackage.GamePanel;
import myPackage.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler kh;
	
	//this indicates where is want to place our player's charecter on the screen (i.e. center)
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler kh){
		
		this.gp = gp;
		this.kh = kh;
		
		//this will make player position as center (this won't change throughout the game)
		screenX = gp.screenWidth/2 - (gp.tileSize/2); //just bcoz we dont have any center bcoz of 16*16 screen so we are subtracting half size , so that it will looks like a center
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//to set solid area of player tile
		solidArea = new Rectangle(); //x y width height
		solidArea.x = 4;
		solidArea.y = 4;
	
		solidArea.width = 32;
		solidArea.height = 32;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		// it is player's starting position on world map
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage()
	{
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update() // this method gets called 60 times per second
	{
		if (kh.upPressed == true || kh.downPressed == true || kh.leftPressed == true || kh.rightPressed == true) // to stop animation while player is standing																										
		{
			if (kh.upPressed == true) {
				direction = "up";
			}
			if (kh.downPressed == true) {
				direction = "down";
			}
			if (kh.leftPressed == true) {
				direction = "left";
			}
			if (kh.rightPressed == true) {
				direction = "right";
			}
			
			//check item collision
			int itemIndex = gp.cc.checkItem(this, true);
			pickItem(itemIndex);
			
			// cheak tile collision and if collision is false , player can move
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			collisionOn = false;
			gp.cc.checkTile(this);

			// image changer
			imgCount++;
			if (imgCount > 12) // to change player image in every 10 frames
			{
				if (imgNum == 1) {
					imgNum = 2;
				} else if (imgNum == 2) {
					imgNum = 1;
				}
				imgCount = 0; // resetting counter each time
			}
		}
	}
	
	public void pickItem(int index)
	{
		if(index != 999)
		{
			String itemName = gp.item[index].name;
			
			//key and door mechanism
			switch(itemName)
			{
			case "key":
				gp.soundEffect(1);
				hasKey++;
				gp.item[index] = null;
				gp.ui.showMsg("You Found Key");
				break;
			case "door":
				gp.soundEffect(3);
				if(hasKey > 0)
				{
					gp.item[index] = null;
					hasKey--;
					gp.ui.showMsg("Door Opened");
				}
				else
				{
					gp.ui.showMsg("Need Key To Open Door");
				}
				break;
			case "boot":
				gp.soundEffect(2);
				speed += 3;
				gp.item[index] = null;
				gp.ui.showMsg("Yeah..SpeedUp!");
				break;	
				
			case "chest1":
				gp.ui.gameOver = true;
				gp.soundEffect(4);
				gp.stopMusic();
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2)
	{
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction)
		{
		case "up":
			if(imgNum == 1)
			{
			image = up1;
			}
			if(imgNum == 2)
			{
			image = up2;
			}
			
			break;
		
		case "down":
		
			if(imgNum == 1)
			{
			image = down1;
			}
			if(imgNum == 2)
			{
			image = down2;
			}
			break;
		
		case "left":
		
			if(imgNum == 1)
			{
			image = left1;
			}
			if(imgNum == 2)
			{
			image = left2;
			}
			break;
		
		case "right":
		
			if(imgNum == 1)
			{
			image = right1;
			}
			if(imgNum == 2)
			{
			image = right2;
			}
			break;
		
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
