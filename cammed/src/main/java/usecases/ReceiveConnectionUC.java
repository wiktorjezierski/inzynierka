package usecases;

import actions.DeviceType;
import connections.DataHelper;
import connections.Serwer;
import multimedia.audio.RecordAudio;

public class ReceiveConnectionUC implements UseCase {

private DeviceType deviceType;
	
	public ReceiveConnectionUC(DeviceType device) {
		this.deviceType = device;
	}
	
	public void execute() {
		Serwer serwerAudio = Serwer.establishSerwer(DataHelper.AUDIO_PORT);
		Thread recordAudio = new RecordAudio(serwerAudio, deviceType);
		recordAudio.start();
		
		Serwer serwerVideo = Serwer.establishSerwer(DataHelper.VIDEO_PORT);
		Thread recordVideo = new RecordAudio(serwerVideo, deviceType);
		recordVideo.start();
	}

}
