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
		try {
			Client.connectWithAnotherUser(findLocalAddress(), DataHelper.AUDIO_PORT);
			Client.connectWithAnotherUser(findLocalAddress(), DataHelper.VIDEO_PORT);
		} catch (RuntimeException e) {
			return;
		}
		
		Client clientAudio = Client.connectWithAnotherUser(addressIp, DataHelper.AUDIO_PORT);
		Thread playAudio = new PlayAudio(clientAudio, deviceType);
		playAudio.run();
		
		Client clientVideo = Client.connectWithAnotherUser(addressIp, DataHelper.VIDEO_PORT);
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
