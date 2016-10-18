package usecases;

import actions.DeviceType;
import connections.DataHelper;
import connections.Serwer;
import multimedia.audio.RecordAudio;
import multimedia.video.RecordVideo;

public class ReceiveConnectionUC implements UseCase {

private DeviceType deviceType;
	
	public ReceiveConnectionUC(DeviceType device) {
		this.deviceType = device;
	}
	
	public void execute() {
		Thread t1 = new Thread() {
			public void run() {
				Serwer serwerAudio = Serwer.establishSerwer(DataHelper.AUDIO_PORT);
				RecordAudio recordAudio = new RecordAudio(serwerAudio, deviceType);
				recordAudio.run();
			}
		};
		t1.start();
		
		Thread t2 = new Thread() {
			public void run() {
				Serwer serwerVideo = Serwer.establishSerwer(DataHelper.VIDEO_PORT);
				Thread recordVideo = new RecordVideo(serwerVideo, deviceType);
				recordVideo.run();
			}
		};
		t2.start();
	}

}
