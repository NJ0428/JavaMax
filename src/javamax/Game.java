/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바 클래스 매뉴얼.
  - 게임이 시작이 되면 클래스에서 만들어진 인스턴스변수를 이용해서 게임의 컨트롤를 할수 있게 만든다
  
  자바프로제트 Game 매뉴얼 :
  - 주의 게임이 시작 할떄 키보드 포커스를 맞쳐야함
  - 노트 판정이 580
 ******************************************************************************/

package javamax;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

/**
 * 게임의 메인 로직을 담당하는 클래스
 * 노트 생성, 판정, 화면 렌더링 등의 핵심 기능을 구현합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class Game extends Thread {
	private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

	// 게임 상수
	private static final int SCORE_DISPLAY_X = 565;
	private static final int SCORE_DISPLAY_Y = 702;
	private static final int TITLE_DISPLAY_X = 20;
	private static final int TITLE_DISPLAY_Y = 702;
	private static final int DIFFICULTY_DISPLAY_X = 1190;
	private static final int DIFFICULTY_DISPLAY_Y = 702;

	private static final int GAME_INFO_Y = 660;
	private static final int BLUE_FLARE_X = 320;
	private static final int BLUE_FLARE_Y = 230;
	private static final int JUDGE_IMAGE_X = 460;
	private static final int JUDGE_IMAGE_Y = 420;

	// 게임 이미지들
	private final Map<String, Image> gameImages;
	private final Map<String, Image> noteRouteImages;
	private final Map<String, Image> keyPadImages;
	private final Map<String, Image> judgeImages;

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRouteLine.png"))
			.getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/ui/judgment/judgementLine.png"))
			.getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/ui/game/gameInfo.png"))
			.getImage();//

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png")).getImage();

	// Space1와 Space2 나누어진 이유는 다른 노트보다 길어서
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png"))
			.getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png"))
			.getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/ui/game/noteRoute.png")).getImage();
	private Image blueFlare;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png"))
			.getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png"))
			.getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/ui/game/KeyPadBasic.png")).getImage();

	private String titleName;// 현재 실행할 곡
	private String difficulty;// 난이도 설정
	private String musicTitle;// 곡 제목
	private Music gameMusic;// 게임 곡
	private int currentScore;// 현재 점수
	private boolean gameEnded = false;// 게임 종료 여부
	private DatabaseManager dbManager;// 데이터베이스 매니저
	private Settings settings;// 설정 객체

	// 각각의 노트를 관리
	ArrayList<Note> noteList = new ArrayList<Note>();

	/**
	 * Game 생성자
	 * 
	 * @param titleName  곡 제목
	 * @param difficulty 난이도
	 * @param musicTitle 음악 파일명
	 */
	public Game(String titleName, String difficulty, String musicTitle) {//

		// 변수 초기화
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;

		this.currentScore = 0;
		this.gameEnded = false;
		this.noteList = new ArrayList<>();
		this.gameImages = new HashMap<>();
		this.noteRouteImages = new HashMap<>();
		this.keyPadImages = new HashMap<>();
		this.judgeImages = new HashMap<>();
		this.dbManager = DatabaseManager.getInstance();
		this.settings = Settings.getInstance();

		initializeImages();
		this.gameMusic = new Music(this.musicTitle, false);// 한번번 실행 되도록

		// dropNotes(titleName);
	}

	/**
	 * 게임에 필요한 이미지들을 초기화합니다.
	 */
	private void initializeImages() {
		try {
			// 기본 게임 이미지들
			gameImages.put("noteRouteLine",
					new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "noteRouteLine.png")).getImage());
			gameImages.put("judgementLine",
					new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "judgementLine.png")).getImage());
			gameImages.put("gameInfo",
					new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "gameInfo.png")).getImage());
			gameImages.put("blueFlare",
					new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "blueFlare.png")).getImage());

			// 노트 레인 이미지들 (기본)
			String[] noteKeys = { "S", "D", "F", "Space1", "Space2", "J", "K", "L" };
			for (String key : noteKeys) {
				noteRouteImages.put(key,
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "noteRoute.png")).getImage());
				keyPadImages.put(key,
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "KeyPadBasic.png")).getImage());
			}

			// 판정 이미지들
			String[] judgeTypes = { "Perfect", "Great", "Good", "Early", "Late", "Miss" };
			for (String judgeType : judgeTypes) {
				judgeImages.put(judgeType,
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "judge" + judgeType + ".png"))
								.getImage());
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "이미지 초기화 실패", e);
		}
	}

	/**
	 * 게임 화면을 그립니다.
	 * 
	 * @param g Graphics2D 객체
	 */
	public void screenDraw(Graphics2D g) {
		drawNoteRoutes(g);
		drawGameInfo(g);
		drawNotes(g);
		drawUI(g);
		drawKeyPads(g);
		drawEffects(g);
	}

	/**
	 * 노트 레인을 그립니다.
	 */
	private void drawNoteRoutes(Graphics2D g) {
		// 노트 경로 그리기
		g.drawImage(noteRouteImages.get("S"), Main.NOTE_LANE_S, 30, null);
		g.drawImage(noteRouteImages.get("D"), Main.NOTE_LANE_D, 30, null);
		g.drawImage(noteRouteImages.get("F"), Main.NOTE_LANE_F, 30, null);
		g.drawImage(noteRouteImages.get("Space1"), Main.NOTE_LANE_SPACE, 30, null);
		g.drawImage(noteRouteImages.get("Space2"), Main.NOTE_LANE_SPACE + 100, 30, null);
		g.drawImage(noteRouteImages.get("J"), Main.NOTE_LANE_J, 30, null);
		g.drawImage(noteRouteImages.get("K"), Main.NOTE_LANE_K, 30, null);
		g.drawImage(noteRouteImages.get("L"), Main.NOTE_LANE_L, 30, null);

		// 노트 구분선 그리기
		int[] linePositions = { 224, 328, 432, 536, 740, 844, 948, 1052 };
		for (int pos : linePositions) {
			g.drawImage(gameImages.get("noteRouteLine"), pos, 30, null);
		}

		// 판정선 그리기
		g.drawImage(gameImages.get("judgementLine"), 0, Main.JUDGMENT_LINE_Y, null);
	}

	/**
	 * 게임 정보를 그립니다.
	 */
	private void drawGameInfo(Graphics2D g) {
		g.drawImage(gameImages.get("gameInfo"), 0, GAME_INFO_Y, null);
	}

	/**
	 * 노트들을 그립니다.
	 */
	private void drawNotes(Graphics2D g) {
		for (int i = noteList.size() - 1; i >= 0; i--) {
			Note note = noteList.get(i);
			if (note.getY() > Main.NOTE_MISS_LINE_Y) {
				judgeImage = judgeImages.get("Miss");
			}
			if (!note.isActive()) {
				noteList.remove(i);
			} else {
				note.screenDraw(g);
			}
		}
	}

	/**
	 * UI 요소들을 그립니다.
	 */
	private void drawUI(Graphics2D g) {
		// 안티앨리어싱 설정
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// 곡 정보 표시
		g.setColor(Color.WHITE);
		g.setFont(new Font("고딕", Font.BOLD, 30));
		g.drawString(titleName, TITLE_DISPLAY_X, TITLE_DISPLAY_Y);
		g.drawString(difficulty, DIFFICULTY_DISPLAY_X, DIFFICULTY_DISPLAY_Y);

		// 키 표시
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		String[] keyLabels = { "S", "D", "F", "Space Bar", "J", "K", "L" };
		int[] keyPositions = { 270, 374, 478, 580, 784, 889, 993 };
		for (int i = 0; i < keyLabels.length; i++) {
			g.drawString(keyLabels[i], keyPositions[i], 609);
		}

		// 점수 표시
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(String.format("%06d", currentScore), SCORE_DISPLAY_X, SCORE_DISPLAY_Y);
	}

	/**
	 * 키패드를 그립니다.
	 */
	private void drawKeyPads(Graphics2D g) {
		g.drawImage(keyPadImages.get("S"), Main.NOTE_LANE_S, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("D"), Main.NOTE_LANE_D, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("F"), Main.NOTE_LANE_F, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("Space1"), Main.NOTE_LANE_SPACE, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("Space2"), Main.NOTE_LANE_SPACE + 100, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("J"), Main.NOTE_LANE_J, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("K"), Main.NOTE_LANE_K, Main.JUDGMENT_LINE_Y, null);
		g.drawImage(keyPadImages.get("L"), Main.NOTE_LANE_L, Main.JUDGMENT_LINE_Y, null);
	}

	/**
	 * 특수 효과를 그립니다.
	 */
	private void drawEffects(Graphics2D g) {
		if (blueFlare != null) {
			g.drawImage(blueFlare, BLUE_FLARE_X, BLUE_FLARE_Y, null);
		}
		if (judgeImage != null) {
			g.drawImage(judgeImage, JUDGE_IMAGE_X, JUDGE_IMAGE_Y, null);
		}
	}

	/**
	 * 키 입력 처리를 위한 통합 메서드
	 */
	public void pressKey(String keyType) {
		judge(keyType);
		updateKeyVisuals(keyType, true);
		playKeySound(keyType);
	}

	public void releaseKey(String keyType) {
		updateKeyVisuals(keyType, false);
	}

	/**
	 * 키 비주얼을 업데이트합니다.
	 */
	private void updateKeyVisuals(String keyType, boolean pressed) {
		String routeImagePath = pressed ? "noteRoutePressed.png" : "noteRoute.png";
		String keyPadImagePath = pressed ? "keyPadPressed.png" : "KeyPadBasic.png";

		try {
			if (keyType.equals("Space")) {
				noteRouteImages.put("Space1",
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + routeImagePath)).getImage());
				noteRouteImages.put("Space2",
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + routeImagePath)).getImage());
				keyPadImages.put("Space1",
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + keyPadImagePath)).getImage());
				keyPadImages.put("Space2",
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + keyPadImagePath)).getImage());
			} else {
				noteRouteImages.put(keyType,
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + routeImagePath)).getImage());
				keyPadImages.put(keyType,
						new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + keyPadImagePath)).getImage());
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "키 비주얼 업데이트 실패: " + keyType, e);
		}
	}

	/**
	 * 키 입력 시 사운드를 재생합니다.
	 */
	private void playKeySound(String keyType) {
		String soundFile = keyType.equals("Space") ? "drumBig1.mp3" : "drumSmall1.mp3";
		new Music(soundFile, false).start();
	}

	// 각 키에 대한 press/release 메서드들
	public void pressS() {
		pressKey("S");
	}

	public void releaseS() {
		releaseKey("S");
	}

	public void pressD() {
		pressKey("D");
	}

	public void releaseD() {
		releaseKey("D");
	}

	public void pressF() {
		pressKey("F");
	}

	public void releaseF() {
		releaseKey("F");
	}

	public void pressSpace() {
		pressKey("Space");
	}

	public void releaseSpace() {
		releaseKey("Space");
	}

	public void pressJ() {
		pressKey("J");
	}

	public void releaseJ() {
		releaseKey("J");
	}

	public void pressK() {
		pressKey("K");
	}

	public void releaseK() {
		releaseKey("K");
	}

	public void pressL() {
		pressKey("L");
	}

	public void releaseL() {
		releaseKey("L");
	}

	@Override
	public void run() {
		dropNotes();
	}

	/**
	 * 게임을 종료합니다.
	 */
	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	/**
	 * 노트를 생성하고 떨어뜨립니다.
	 */
	public void dropNotes() {
		Beat[] beats = getBeatPattern();

		int i = 0;
		gameMusic.start();

		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getCurrentTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}

			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}

	/**
	 * 곡과 난이도에 따른 비트 패턴을 자동으로 생성합니다.
	 */
	private Beat[] getBeatPattern() {
		return generateAutoPattern();
	}

	/**
	 * 음악 길이에 맞춰 자동으로 노트 패턴을 생성합니다.
	 */
	private Beat[] generateAutoPattern() {
		ArrayList<Beat> beatList = new ArrayList<Beat>();

		// 음악 길이를 구합니다
		long musicLength = getMusicLength();
		System.out.println("음악 길이: " + musicLength + "ms (" + (musicLength / 1000) + "초)");

		// 시작 시간 설정
		int startTime = 1000 - Main.REACH_TIME * 1000;

		// 난이도에 따른 설정
		NoteGeneratorConfig config = createNoteConfig();
		System.out.println("난이도: " + difficulty + ", 마디 길이: " + config.measureLength + "ms");

		// 현재 시간과 패턴 상태
		int currentTime = startTime;
		int measureCount = 0; // 마디 카운터
		String lastNoteType = ""; // 마지막 노트 타입

		// 음악이 끝날 때까지 노트 생성
		while (currentTime < musicLength - 2000) {
			// 4박자 기준으로 마디 생성
			Beat[] measureBeats = generateMeasure(currentTime, measureCount, config, lastNoteType);

			for (Beat beat : measureBeats) {
				if (beat.getTime() < musicLength - 2000) {
					beatList.add(beat);
					lastNoteType = beat.getNoteName();
				}
			}

			// 다음 마디로 이동 (4박자 = 2000ms 기준)
			currentTime += config.measureLength;
			measureCount++;
		}

		System.out.println("생성된 노트 수: " + beatList.size() + "개");
		System.out.println("생성된 마디 수: " + measureCount + "개");

		return beatList.toArray(new Beat[0]);
	}

	/**
	 * 난이도에 따른 노트 생성 설정을 생성합니다.
	 */
	private NoteGeneratorConfig createNoteConfig() {
		NoteGeneratorConfig config = new NoteGeneratorConfig();

		if (difficulty.equals("Easy")) {
			config.measureLength = 2400; // 마디 길이 (밀리초)
			config.beatInterval = 600; // 기본 박자 간격
			config.noteChance = 0.5; // 노트 생성 확률
			config.comboChance = 0.1; // 연속 노트 확률
			config.spaceChance = 0.2; // 스페이스 노트 확률
		} else { // Hard
			config.measureLength = 2000;
			config.beatInterval = 500;
			config.noteChance = 0.7;
			config.comboChance = 0.3;
			config.spaceChance = 0.3;
		}

		return config;
	}

	/**
	 * 한 마디분의 노트를 생성합니다.
	 */
	private Beat[] generateMeasure(int startTime, int measureNumber,
			NoteGeneratorConfig config, String lastNoteType) {
		ArrayList<Beat> measureBeats = new ArrayList<Beat>();
		String[] noteTypes = { "S", "D", "F", "Space", "J", "K", "L" };

		// 마디 내 박자 위치들 계산
		int[] beatPositions = calculateBeatPositions(startTime, config);

		for (int position : beatPositions) {
			if (Math.random() < config.noteChance) {
				String noteType = selectNoteType(noteTypes, lastNoteType, config);

				// 연속 노트 생성 (콤보)
				if (Math.random() < config.comboChance && !noteType.equals("Space")) {
					// 100ms 후에 다른 노트 추가
					String comboNote = selectDifferentNote(noteTypes, noteType);
					measureBeats.add(new Beat(position, noteType));
					measureBeats.add(new Beat(position + 150, comboNote));
				} else {
					measureBeats.add(new Beat(position, noteType));
				}

				lastNoteType = noteType;
			}
		}

		return measureBeats.toArray(new Beat[0]);
	}

	/**
	 * 마디 내 박자 위치들을 계산합니다.
	 */
	private int[] calculateBeatPositions(int startTime, NoteGeneratorConfig config) {
		ArrayList<Integer> positions = new ArrayList<Integer>();

		// 기본 4박자 위치
		for (int i = 0; i < 4; i++) {
			positions.add(startTime + i * config.beatInterval);
		}

		// 하드 모드에서는 8분음표 추가
		if (difficulty.equals("Hard")) {
			for (int i = 0; i < 4; i++) {
				if (Math.random() < 0.4) { // 40% 확률로 8분음표 추가
					positions.add(startTime + i * config.beatInterval + config.beatInterval / 2);
				}
			}
		}

		// 정렬
		positions.sort(Integer::compareTo);

		return positions.stream().mapToInt(Integer::intValue).toArray();
	}

	/**
	 * 적절한 노트 타입을 선택합니다.
	 */
	private String selectNoteType(String[] noteTypes, String lastNoteType, NoteGeneratorConfig config) {
		// 스페이스 노트 확률 체크
		if (Math.random() < config.spaceChance) {
			return "Space";
		}

		// 같은 노트 연속 방지
		String selectedNote;
		do {
			selectedNote = noteTypes[(int) (Math.random() * noteTypes.length)];
		} while (selectedNote.equals(lastNoteType) && Math.random() < 0.7);

		return selectedNote;
	}

	/**
	 * 다른 노트 타입을 선택합니다 (콤보용).
	 */
	private String selectDifferentNote(String[] noteTypes, String excludeNote) {
		String selectedNote;
		do {
			selectedNote = noteTypes[(int) (Math.random() * noteTypes.length)];
		} while (selectedNote.equals(excludeNote) || selectedNote.equals("Space"));

		return selectedNote;
	}

	/**
	 * 노트 생성 설정 클래스
	 */
	private static class NoteGeneratorConfig {
		int measureLength; // 마디 길이
		int beatInterval; // 박자 간격
		double noteChance; // 노트 생성 확률
		double comboChance; // 연속 노트 확률
		double spaceChance; // 스페이스 노트 확률
	}

	/**
	 * 음악 파일의 길이를 반환합니다.
	 * 
	 * @return 음악 길이 (밀리초)
	 */
	private long getMusicLength() {
		// 임시 Music 객체를 생성하여 길이를 측정
		try {
			Music tempMusic = new Music(musicTitle, false);
			long length = tempMusic.getMusicLength();
			tempMusic.close();
			return length;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "음악 길이 측정 실패", e);
			return 180000; // 기본값 3분
		}
	}

	/**
	 * 키 입력에 대한 판정을 수행합니다.
	 */
	public void judge(String input) {
		for (Note note : noteList) {
			if (input.equals(note.getNoteType())) {
				String result = note.judge();
				judgeEvent(result);
				updateScore(result);
				break;
			}
		}
	}

	/**
	 * 판정 결과에 따른 이벤트를 처리합니다.
	 */
	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlare = gameImages.get("blueFlare");
		}

		if (judgeImages.containsKey(judge)) {
			judgeImage = judgeImages.get(judge);
		}
	}

	/**
	 * 점수를 업데이트합니다.
	 */
	private void updateScore(String judge) {
		switch (judge) {
			case "Perfect":
				currentScore += 100;
				break;
			case "Great":
				currentScore += 80;
				break;
			case "Good":
				currentScore += 60;
				break;
			case "Early":
			case "Late":
				currentScore += 30;
				break;
			// Miss는 점수 추가 없음
		}
	}

	// Getter 메서드들
	public int getCurrentScore() {
		return currentScore;
	}

	public String getTitleName() {
		return titleName;
	}

	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * 게임 종료 시 점수를 저장합니다.
	 */
	public void saveScore() {
		if (!gameEnded) {
			gameEnded = true;
			try {
				// 기본 플레이어 이름을 "Player"로 설정 (나중에 입력받도록 확장 가능)
				String playerName = "Player";
				dbManager.saveScore(playerName, titleName, difficulty, currentScore);
				System.out.println(
						"점수 저장됨: " + playerName + " - " + titleName + " (" + difficulty + ") - " + currentScore + "점");
			} catch (Exception e) {
				System.err.println("점수 저장 실패: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 게임 종료 여부를 확인합니다.
	 */
	public boolean isGameEnded() {
		return gameEnded;
	}

	/**
	 * 특정 트랙의 최고 점수를 반환합니다.
	 */
	public int getBestScoreForTrack() {
		return dbManager.getBestScore(titleName);
	}
}
