package multimedia.video;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import org.bytedeco.javacv.CanvasFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import connections.Client;

public class DisplayVideo extends Thread {

	private CanvasFrame canvas;
	private Client client;
	
	private Webcam webcam;

	public DisplayVideo(Client client) {
		this.client = client;
		
		canvas = new CanvasFrame("Web Cam");
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	public void run() {
		Thread t = new Thread() {
			public void run() {
				displayImage();
			}
		};
		t.start();
		
		Thread t2 = new Thread() {
			public void run() {
				captureImage();
			}
		};
		t2.start();
	}

	public void displayImage() {
		while (true) {
			Image image = client.receiveImage();
			canvas.showImage(image);
		}
	}
	
	private void captureImage() {
		try {
			Dimension[] nonStandardResolutions = new Dimension[] { 
					WebcamResolution.PAL.getSize(),
					// WebcamResolution.HD720.getSize(),
					new Dimension(2000, 1000), 
					new Dimension(1000, 500)};
			webcam = Webcam.getDefault();
			webcam.setCustomViewSizes(nonStandardResolutions);
			webcam.setViewSize(WebcamResolution.PAL.getSize());
			webcam.open();
			
			BufferedImage image;

			while (true) {
				image = webcam.getImage();
				ImageBuffer imageToSend = new ImageBuffer(image);
				client.sendImage(imageToSend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
