package multimedia.video;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.bytedeco.javacv.CanvasFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import actions.DeviceType;
import connections.Client;
import masterdata.SystemParameter;

public class DisplayVideo extends Thread {

	private DeviceType deviceType;
	
	private CanvasFrame canvas;
	private Client client;
	
	private Webcam webcam;

	public DisplayVideo(Client client, DeviceType device) {
		this.client = client;
		this.deviceType = device;
		
		canvas = new CanvasFrame("Web Cam");
//		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	public void run() {
		if (deviceType == DeviceType.PC) {
			clientPC();
		}
	}

	public void clientPC() {
		Thread t = new Thread() {
			public void run() {
				try {
					displayImage();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();

		captureImage();
		
		canvas.dispose();
		webcam.close();
	}

	public void displayImage() throws ClassNotFoundException, IOException {
		while ((boolean) SystemParameter.get(SystemParameter.VIDEO_INTERVIEW)) {
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

			while ((boolean) SystemParameter.get(SystemParameter.VIDEO_INTERVIEW)) {
				try {
					image = webcam.getImage();
					ImageBuffer imageToSend = new ImageBuffer(image);
					client.sendImage(imageToSend);
				} catch (NullPointerException e) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
