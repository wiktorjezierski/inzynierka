package multimedia.video;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.bytedeco.javacv.CanvasFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import actions.DeviceType;
import connections.Serwer;
import masterdata.SystemParameter;

public class RecordVideo extends Thread {

	private Serwer serwer;
	private Webcam webcam;
	
	private CanvasFrame canvas;
	
	private DeviceType deviceType;

	public RecordVideo(Serwer serwer, DeviceType deviceType) {
		this.serwer = serwer;
		this.deviceType = deviceType;
		
		canvas = new CanvasFrame("Web Cam2");
//		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

	public void run() {
		Thread t = new Thread() {
			public void run() {
				captureImage();
			}
		};
		t.start();

		try {
			displayImage();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		canvas.dispose();
		webcam.close();
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
			ImageBuffer imageToSend;

			while ((boolean) SystemParameter.get(SystemParameter.VIDEO_INTERVIEW)) {
				image = webcam.getImage();
				imageToSend = new ImageBuffer(image);
				serwer.sendImage(imageToSend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayImage() throws ClassNotFoundException, IOException {
		while ((boolean) SystemParameter.get(SystemParameter.VIDEO_INTERVIEW)) {
//			Image image = serwer.receiveImage();
			canvas.showImage(serwer.receiveImage());
		}
	}
}
