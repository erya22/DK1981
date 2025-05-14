package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Hammer extends SuperObject {

	public Hammer() {
		name = "Hammer";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/hammer/f1.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		}
	}

}
