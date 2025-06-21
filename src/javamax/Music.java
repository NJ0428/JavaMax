/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바프로제트 매뉴얼.
  - 
  
  자바프로제트 JavaMax 매뉴얼 :
  - 
 ******************************************************************************/
package javamax;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

/**
 * 음악 재생을 담당하는 클래스
 * MP3 파일을 재생하고 루프 기능을 제공합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class Music extends Thread {
	private static final Logger LOGGER = Logger.getLogger(Music.class.getName());

	private Player player;
	private boolean isLoop;
	private File musicFile;
	private FileInputStream fileInputStream;
	private BufferedInputStream bufferedInputStream;
	private String musicName;

	/**
	 * Music 생성자
	 * 
	 * @param musicName 음악 파일명
	 * @param isLoop    무한 반복 여부
	 */
	public Music(String musicName, boolean isLoop) {
		this.musicName = musicName;
		this.isLoop = isLoop;
		initializePlayer();
	}

	/**
	 * 플레이어를 초기화합니다.
	 */
	private void initializePlayer() {
		try {
			musicFile = new File(Main.class.getResource(Main.MUSIC_PATH + musicName).toURI());
			fileInputStream = new FileInputStream(musicFile);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			player = new Player(bufferedInputStream);
		} catch (URISyntaxException | IOException | JavaLayerException e) {
			LOGGER.log(Level.SEVERE, "음악 파일 초기화 실패: " + musicName, e);
		}
	}

	/**
	 * 현재 재생 시간을 반환합니다.
	 * 
	 * @return 현재 재생 시간 (밀리초)
	 */
	public int getCurrentTime() {
		return (player != null) ? player.getPosition() : 0;
	}

	/**
	 * 음악 재생을 중지하고 리소스를 해제합니다.
	 */
	public void close() {
		isLoop = false;
		if (player != null) {
			player.close();
		}
		closeStreams();
		this.interrupt();
	}

	/**
	 * 스트림들을 안전하게 닫습니다.
	 */
	private void closeStreams() {
		try {
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "스트림 닫기 실패", e);
		}
	}

	/**
	 * 플레이어를 재초기화합니다.
	 */
	private void reinitializePlayer() {
		try {
			fileInputStream = new FileInputStream(musicFile);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			player = new Player(bufferedInputStream);
		} catch (IOException | JavaLayerException e) {
			LOGGER.log(Level.SEVERE, "플레이어 재초기화 실패: " + musicName, e);
		}
	}

	@Override
	public void run() {
		try {
			do {
				if (player != null) {
					player.play();
					if (isLoop) {
						reinitializePlayer();
					}
				}
			} while (isLoop && !Thread.currentThread().isInterrupted());
		} catch (JavaLayerException e) {
			LOGGER.log(Level.SEVERE, "음악 재생 중 오류 발생: " + musicName, e);
		} finally {
			closeStreams();
		}
	}

	/**
	 * 음악 파일의 전체 길이를 계산합니다.
	 * 간단한 추정 방법을 사용합니다.
	 * 
	 * @return 음악 길이 (밀리초), 계산 실패 시 기본값 180000 (3분)
	 */
	public long getMusicLength() {
		try {
			long fileSize = musicFile.length();

			// MP3 파일의 대략적인 길이 계산
			// 평균 비트레이트 128kbps 기준으로 계산
			// 1초당 약 16KB (128 kbps / 8 bits = 16 KB/s)
			long estimatedLength = (fileSize / 16) * 1000;

			// 최소 30초, 최대 10분으로 제한
			estimatedLength = Math.max(30000, Math.min(600000, estimatedLength));

			return estimatedLength;

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "음악 길이 계산 실패: " + musicName, e);
			return 180000; // 기본값 3분
		}
	}

	/**
	 * 음악 파일명을 반환합니다.
	 * 
	 * @return 음악 파일명
	 */
	public String getMusicName() {
		return musicName;
	}
}
