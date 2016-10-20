package usecases;

import java.net.InetAddress;
import java.net.UnknownHostException;

import actions.DeviceType;
import connections.Client;
import connections.DataHelper;
import multimedia.audio.PlayAudio;
import multimedia.video.DisplayVideo;

public class EstablishConnectionUC implements UseCase {
	
	private String addressIp;
	private DeviceType deviceType;
	
	public EstablishConnectionUC(String addressIp, DeviceType device) {
		this.addressIp = addressIp;
		this.deviceType = device;
	}

	public void execute(){
//		try {
//			Client client1 = Client.connectWithAnotherUser(findLocalAddress(), DataHelper.AUDIO_PORT);
//			Client client2 = Client.connectWithAnotherUser(findLocalAddress(), DataHelper.VIDEO_PORT);
//			client1.closeConnection();
//			client2.closeConnection();
//		} catch (RuntimeException e) {
//			return;
//		}
		
		findLocalAddress();
		Client clientAudio = Client.connectWithAnotherUser(addressIp, DataHelper.AUDIO_PORT, true);
		Thread playAudio = new PlayAudio(clientAudio, deviceType);
		playAudio.run();
		
		Client clientVideo = Client.connectWithAnotherUser(addressIp, DataHelper.VIDEO_PORT, false);
		Thread displayVideo = new DisplayVideo(clientVideo, deviceType);
		displayVideo.start();
	}
	
	private String findLocalAddress() {
		try {
			return InetAddress.getLocalHost().toString().split("/")[1];
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
