package myPackage;

import items.Boot;
import items.Chest;
import items.Door;
import items.Key;

//To set all Placement setting for items
public class ItemPositionSetter {

	GamePanel gp;
	
	public ItemPositionSetter(GamePanel gp)
	{
			this.gp = gp;
	}
	public void setItem()
	{
		gp.item[0] = new Key();
		gp.item[0].worldX = gp.tileSize * 23; 
		gp.item[0].worldY = gp.tileSize * 7;
		
		gp.item[1] = new Key();
		gp.item[1].worldX = gp.tileSize * 23; 
		gp.item[1].worldY = gp.tileSize * 39;
		
		gp.item[2] = new Key();
		gp.item[2].worldX = gp.tileSize * 38; 
		gp.item[2].worldY = gp.tileSize * 9;
		
		gp.item[3] = new Key();
		gp.item[3].worldX = gp.tileSize * 8; 
		gp.item[3].worldY = gp.tileSize * 17;
		
		gp.item[4] = new Door();
		gp.item[4].worldX = gp.tileSize * 23; 
		gp.item[4].worldY = gp.tileSize * 10;
		
		gp.item[5] = new Door();
		gp.item[5].worldX = gp.tileSize * 38; 
		gp.item[5].worldY = gp.tileSize * 13;
		
		gp.item[6] = new Door();
		gp.item[6].worldX = gp.tileSize * 10; 
		gp.item[6].worldY = gp.tileSize * 11;
		
		gp.item[7] = new Door();
		gp.item[7].worldX = gp.tileSize * 12; 
		gp.item[7].worldY = gp.tileSize * 22;
		
		gp.item[8] = new Chest();
		gp.item[8].worldX = gp.tileSize * 10; 
		gp.item[8].worldY = gp.tileSize * 8;
		
		gp.item[9] = new Boot();
		gp.item[9].worldX = gp.tileSize * 37; 
		gp.item[9].worldY = gp.tileSize * 42;
	
	}
	
}
