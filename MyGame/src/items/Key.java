package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends Item{
	
	public Key()
	{
		name = "key";
		
		try {
			bImg = ImageIO.read(getClass().getResourceAsStream("/items/key.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
