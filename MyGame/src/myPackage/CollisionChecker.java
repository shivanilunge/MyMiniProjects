package myPackage;

import entities.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp)
	{
		this.gp = gp;
	}

	public void checkTile(Entity e)  //for all entities
	{
		int entityLeftWorldX = e.worldX + e.solidArea.x;
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entityTopWorldY = e.worldY + e.solidArea.y;
		int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(e.direction)
		{
		case "up":
			entityTopRow = (entityTopWorldY - e.speed)/ gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tm.t[tileNum1].collision == true || gp.tm.t[tileNum2].collision == true)
			{
				e.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + e.speed)/ gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tm.t[tileNum1].collision == true || gp.tm.t[tileNum2].collision == true)
			{
				e.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - e.speed)/ gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tm.t[tileNum1].collision == true || gp.tm.t[tileNum2].collision == true)
			{
				e.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + e.speed)/ gp.tileSize;
			tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tm.t[tileNum1].collision == true || gp.tm.t[tileNum2].collision == true)
			{
				e.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkItem(Entity e, boolean player)
	{
		int index = 999;
		
		for(int i = 0; i < gp.item.length; i++)
		{
			if(gp.item[i] != null)
			{
				//get players solid position
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;
				//get items solid position
				gp.item[i].solidArea.x = gp.item[i].worldX + gp.item[i].solidArea.x;
				gp.item[i].solidArea.y = gp.item[i].worldY + gp.item[i].solidArea.y;
				
				switch(e.direction)
				{
				case "up":
					e.solidArea.y -= e.speed;
					if(e.solidArea.intersects(gp.item[i].solidArea)) //intersects is a method from Rectangle class
					{
						if(gp.item[i].collision == true)
						{
							e.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
					break;
				case "down":
					e.solidArea.y += e.speed;
					if(e.solidArea.intersects(gp.item[i].solidArea)) //intersects is a method from Rectangle class
					{
						if(gp.item[i].collision == true)
						{
							e.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}					
					break;
				case "left":
					e.solidArea.x -= e.speed;
					if(e.solidArea.intersects(gp.item[i].solidArea)) //intersects is a method from Rectangle class
					{
						if(gp.item[i].collision == true)
						{
							e.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
					break;
				case "right":
					e.solidArea.x += e.speed;
					if(e.solidArea.intersects(gp.item[i].solidArea)) //intersects is a method from Rectangle class
					{
						if(gp.item[i].collision == true)
						{
							e.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
						break;
					}
					
				}
				e.solidArea.x = e.solidAreaDefaultX;
				e.solidArea.y = e.solidAreaDefaultY;
				gp.item[i].solidArea.x = gp.item[i].solidAreaDefaultX;
				gp.item[i].solidArea.y = gp.item[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}
