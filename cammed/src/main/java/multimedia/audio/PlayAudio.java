package multimedia.audio;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import actions.DeviceType;
import connections.Client;
import connections.DataHelper;

public class PlayAudio extends Thread {
	
	private DeviceType deviceType;

	private Client client;
	private SourceDataLine sourceDataLine;

	private AudioFormat audioFormat;
	
	public PlayAudio(Client client, DeviceType device) {
		this.client = client;
		this.deviceType = device;
		audioFormat = new AudioFormat(16000, 16, 1, true, false);
	}

	public void run() {
		if (deviceType == DeviceType.PC) {
			clientStreamPC();
		}
	}

	private void clientStreamPC() {
		try {
			Thread t2 = new Thread() {	// odebranie danych od klienta
				public void run() {
					try {
						playAudio();
					} catch (LineUnavailableException | IOException e) {
						e.printStackTrace();
					}
				}
			};
			t2.start();
			
			Thread t = new Thread() {	// odebranie danych od klienta
				public void run() {
					try {
						recordAudio();
					} catch (LineUnavailableException | IOException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void playAudio() throws LineUnavailableException, IOException {
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
		sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();

		byte tempBuffer[];
		while (true) {
			tempBuffer = client.receiveAudio();
			if (tempBuffer.length > 0) {
				sourceDataLine.write(tempBuffer, 0, DataHelper.AUDIO_CHUNK_SIZE);
			}
		}
	}
	
	private void recordAudio() throws LineUnavailableException, IOException {
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

		TargetDataLine microphone = AudioSystem.getTargetDataLine(audioFormat);
		microphone = (TargetDataLine) AudioSystem.getLine(info);
		microphone.open(audioFormat);
		microphone.start();

		int numBytesRead;
		byte[] data = new byte[DataHelper.AUDIO_CHUNK_SIZE];
		int bytesRead = 0;

		while (true) {
			numBytesRead = microphone.read(data, 0, DataHelper.AUDIO_CHUNK_SIZE);
			bytesRead = bytesRead + numBytesRead;
			client.sendAudio(data);
			System.out.println(bytesRead);
		}
	}
}
