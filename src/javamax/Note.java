/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바프로제트 매뉴얼.
  - 
  
  자바프로제트 Note 매뉴얼 :
  - 노트를 떨어트리는 기능을 구형하는 클래스
  - 떨어지는 노트를 실행하기 위해 쓰레드 사용
 ******************************************************************************/

package javamax;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 게임에서 떨어지는 노트를 관리하는 클래스
 * Thread를 상속받아 노트가 지속적으로 떨어지도록 구현
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class Note extends Thread {

	// 판정 결과 상수
	public static final String PERFECT = "Perfect";
	public static final String GREAT = "Great";
	public static final String GOOD = "Good";
	public static final String EARLY = "Early";
	public static final String LATE = "Late";
	public static final String MISS = "Miss";
	public static final String NONE = "None";

	private Image noteBasicImage;
	private int x;
	private int y;
	private String noteType;
	private boolean isActive;

	/**
	 * Note 생성자
	 * 
	 * @param noteType 노트 타입 (S, D, F, Space, J, K, L)
	 */
	public Note(String noteType) {
		this.noteType = noteType;
		this.isActive = true;

		initializeNote();
		initializePosition();
	}

	/**
	 * 노트 이미지를 초기화합니다.
	 */
	private void initializeNote() {
		noteBasicImage = new ImageIcon(Main.class.getResource(Main.IMAGE_PATH + "noteBasic.png")).getImage();
		// 노트 생성 시 판정선에서 1초 전 위치에서 시작
		y = Main.JUDGMENT_LINE_Y - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	}

	/**
	 * 노트 타입에 따른 X 좌표를 설정합니다.
	 */
	private void initializePosition() {
		switch (noteType) {
			case "S":
				x = Main.NOTE_LANE_S;
				break;
			case "D":
				x = Main.NOTE_LANE_D;
				break;
			case "F":
				x = Main.NOTE_LANE_F;
				break;
			case "Space":
				x = Main.NOTE_LANE_SPACE;
				break;
			case "J":
				x = Main.NOTE_LANE_J;
				break;
			case "K":
				x = Main.NOTE_LANE_K;
				break;
			case "L":
				x = Main.NOTE_LANE_L;
				break;
			default:
				x = Main.NOTE_LANE_S; // 기본값
				break;
		}
	}

	/**
	 * 노트를 화면에 그립니다.
	 * 
	 * @param g Graphics2D 객체
	 */
	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			// 일반 노트
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			// 스페이스 노트는 두 칸을 차지
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}

	/**
	 * 노트를 아래로 떨어트립니다.
	 */
	public void drop() {
		y += Main.NOTE_SPEED;

		// Miss 판정 처리
		if (y > Main.NOTE_MISS_LINE_Y) {
			System.out.println(MISS);
			close();
		}
	}

	/**
	 * 키 입력에 따른 판정을 수행합니다.
	 * 
	 * @return 판정 결과 문자열
	 */
	public String judge() {
		int judgmentLine = Main.JUDGMENT_LINE_Y;
		int distance = Math.abs(y - judgmentLine);

		if (distance <= Main.PERFECT_RANGE / 2) {
			System.out.print(PERFECT);
			close();
			return PERFECT;
		} else if (distance <= Main.GREAT_RANGE / 2) {
			String result = (y < judgmentLine) ? GREAT : GREAT;
			System.out.print(result);
			close();
			return result;
		} else if (distance <= Main.GOOD_RANGE / 2) {
			String result = (y < judgmentLine) ? GOOD : GOOD;
			System.out.print(result);
			close();
			return result;
		} else if (distance <= Main.EARLY_LATE_RANGE / 2) {
			String result = (y < judgmentLine) ? EARLY : LATE;
			System.out.print(result);
			close();
			return result;
		}

		return NONE;
	}

	/**
	 * 노트를 비활성화합니다.
	 */
	public void close() {
		isActive = false;
	}

	@Override
	public void run() {
		try {
			while (isActive && !Thread.currentThread().isInterrupted()) {
				drop();
				Thread.sleep(Main.SLEEP_TIME);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Getter 메서드들
	public String getNoteType() {
		return noteType;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
}
