package multimedia.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import actions.DeviceType;
import connections.DataHelper;
import connections.Serwer;
import masterdata.SystemParameter;

public class RecordAudio extends Thread {

	private Serwer serwer;
	private AudioFormat audioFormat;
	private int numberFile;
	
	private DeviceType deviceType;

	public RecordAudio(Serwer serwer, DeviceType deviceType) {
		this.serwer = serwer;
		this.deviceType = deviceType;
		audioFormat = new AudioFormat(16000, 16, 1, true, false);
	}

	public void run() {
		if (deviceType == DeviceType.PC) {
			serwerStreamPC();
		} else {
			serwerStreamAndroid();
		}
	}

	public void serwerStreamAndroid() {
		AudioInputStream audioInputStream;
		SourceDataLine sourceDataLine;

		try {

			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
			final TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
			microphone.open(audioFormat);

			audioInputStream = new AudioInputStream(microphone);
			microphone.start();

			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						while (true) {
							Thread.sleep(1000);
							microphone.stop();
							microphone.start();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t.start();

			for (int i = 0; true; i++) {
				File file = new File("C:/Projekty/kamerka/xxx" + i + ".wav");
				AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void serwerStreamPC() {
			Thread t2 = new Thread() {
				public void run() {
					try {
						recordAudio();
						serwer.closeConnection();
						serwer = null;
					} catch (Exception e) {
						e.printStackTrace();
						serwer.closeConnection();
						serwer = null;
					} 
				}
			};
			t2.start();

			Thread t = new Thread() { // odebranie danych od klienta
				public void run() {
					try {
						playAudio();
						serwer.closeConnection();
						serwer = null;
					} catch (Exception e) {
						e.printStackTrace();
						serwer.closeConnection();
						serwer = null;
					} 
				}
			};
			t.start();
	}
	
	private void recordAudio() throws LineUnavailableException, IOException {
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

			TargetDataLine microphone = AudioSystem.getTargetDataLine(audioFormat);
			microphone = (TargetDataLine) AudioSystem.getLine(info);
			try{
				microphone.open(audioFormat);
			} catch (Exception e) {
				
			}
			microphone.start();
			
			int numBytesRead;
			byte[] data = new byte[DataHelper.AUDIO_CHUNK_SIZE];
			int bytesRead = 0;

			while ((boolean) SystemParameter.get(SystemParameter.VIDEO_INTERVIEW)) {
				numBytesRead = microphone.read(data, 0, DataHelper.AUDIO_CHUNK_SIZE);
				bytesRead = bytesRead + numBytesRead;
				serwer.sendAudio(data);
				System.out.print("Serwer recordAudio ");
//				System.out.println(bytesRead);
			}
	}
	
	private void playAudio() throws LineUnavailableException, IOException {
		int ilosc = 0;
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();
			byte tempBuffer[];
			while ((boolean) SystemParameter.get(SystemParameter.VIDEO_INTERVIEW)) {
				tempBuffer = serwer.receiveAudio();
				if (tempBuffer.length > 0) {
					sourceDataLine.write(tempBuffer, 0, DataHelper.AUDIO_CHUNK_SIZE);
					System.out.print("Serwer playAudio ");
					System.out.println(ilosc += tempBuffer.length);
				}
			}
//		sourceDataLine.drain();
//		sourceDataLine.close();
	}
}
