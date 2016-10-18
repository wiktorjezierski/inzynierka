package multimedia.video;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ImageBuffer implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	int width;
	int height;
	int[] pixels;

	public ImageBuffer(BufferedImage bi) {
		if(pixels == null) {
			width = bi.getWidth();
			height = bi.getHeight();
			pixels = new int[width * height];
		}
		bi.getRGB(0, 0, width, height, pixels, 0, width);
	}

	public BufferedImage getImage() {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bi.setRGB(0, 0, width, height, pixels, 0, width);
		return bi;
	}

}
