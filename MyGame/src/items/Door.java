package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends Item {

	public Door()
	{
		name = "door";
		
		try {
			bImg = ImageIO.read(getClass().getResourceAsStream("/items/door.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		collision = true;
	}
}
