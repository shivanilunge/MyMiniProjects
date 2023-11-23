package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Boot extends Item{

	public Boot()
	{
		name = "boot";
		
		try {
			bImg = ImageIO.read(getClass().getResourceAsStream("/items/boot.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		collision = true;
	}
}
