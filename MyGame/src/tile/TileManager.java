package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Integer;
import javax.imageio.ImageIO;

import myPackage.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] t;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		t = new Tile[10]; //to create 10 different kind of tiles like grass, water, rock, etc	
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
		
	}
	
	public void getTileImage()
	{
		try
		{

			t[0] = new Tile();
			t[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			t[1] = new Tile();
			t[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			t[1].collision = true;
			
			t[2] = new Tile();
			t[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			t[2].collision = true;
			
			t[3] = new Tile();
			t[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			t[4] = new Tile();
			t[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			t[4].collision = true;
			
			t[5] = new Tile();
			t[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath)
	{
		try {
		InputStream is = getClass().getResourceAsStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used InputStreamReader(is) to read that .txt file
		
		int col = 0; 
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow)  //to set world map as a boundry for game
		{
			String line = br.readLine(); //to read each line from the file
			
			while(col < gp.maxWorldCol)
			{
				String numbers[] = line.split(" "); //to take each number from file and put them here
				
				int num = Integer.parseInt(numbers[col]); //readline gives string data...and we are converting it to int
				
				mapTileNum[col][row] = num;
				col++;
			}
			if(col == gp.maxWorldCol)
			{
				col = 0;
				row++;
			}
		}
		br.close();
	}catch(Exception e){
		}
	}
	
	public void draw(Graphics2D g2) {
//		g2.drawImage(t[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(t[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(t[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow]; // this number decides the tile

			int worldX = worldCol * gp.tileSize; // for ex. if it is 0
			int worldY = worldRow * gp.tileSize; // 0
			int screenX = worldX - gp.p.worldX + gp.p.screenX; // 0*48
			int screenY = worldY - gp.p.worldY + gp.p.screenY; // 0*48
			// this is going to represent each tile one by one

			//to render only visible tiles on screen
			if(worldX + gp.tileSize > gp.p.worldX - gp.p.screenX && worldX - gp.tileSize < gp.p.worldX + gp.p.screenX && worldY + gp.tileSize > gp.p.worldY - gp.p.screenY && worldY - gp.tileSize < gp.p.worldY + gp.p.screenY ) 
			{
				g2.drawImage(t[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;

			if (worldCol == gp.maxWorldCol) { //if tile count reaches the limit like end of the like then next line starts 
				worldCol = 0;
				worldRow++;
			}
		}
	}
	
}
