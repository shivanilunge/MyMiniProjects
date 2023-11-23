package items;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import myPackage.GamePanel;

//blueprint for an item
public class Item {

	public BufferedImage bImg;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48); //to make item tiles solid
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp)
	{
		int screenX = worldX - gp.p.worldX + gp.p.screenX;
		int screenY = worldY - gp.p.worldY + gp.p.screenY;
		
		if(worldX + gp.tileSize > gp.p.worldX - gp.p.screenX && worldX - gp.tileSize < gp.p.worldX + gp.p.screenX && worldY + gp.tileSize > gp.p.worldY - gp.p.screenY && worldY - gp.tileSize < gp.p.worldY + gp.p.screenY ) 
		{
			g2.drawImage(bImg, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
