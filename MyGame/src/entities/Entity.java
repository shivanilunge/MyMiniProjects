package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//This will act as parent cls for all entity classes
public class Entity {
	
	public int worldX,worldY; //for game camera
	
	public int speed;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //BufferedImage is used to store image files
	public String direction;
	
	//to create walking animation
	public int imgCount = 0;
	public int imgNum = 1;
	
	public Rectangle solidArea; //to create invisible rectangle aroung player (basically to make olny some part of player tile as solid and not complete tile)
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	
	public boolean collisionOn = false;
	
}
