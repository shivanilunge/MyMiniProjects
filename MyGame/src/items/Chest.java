package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Chest extends Item {

	public Chest()
	{
		name = "chest1";
		
		try {
			bImg = ImageIO.read(getClass().getResourceAsStream("/items/chest1.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
