/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바프로제트 매뉴얼.
  - 
  
  자바프로제트 Main 매뉴얼 :
  - 
 ******************************************************************************/
package javamax;

/**
 * JavaMax 음악 게임 메인 클래스
 * 게임의 기본 설정과 상수들을 정의합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class Main {
	// 화면 설정
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	// 게임 설정
	public static final int NOTE_SPEED = 7;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 1;

	// 게임 좌표 상수
	public static final int JUDGMENT_LINE_Y = 580;
	public static final int NOTE_MISS_LINE_Y = 620;

	// 노트 레인 X 좌표
	public static final int NOTE_LANE_S = 228;
	public static final int NOTE_LANE_D = 332;
	public static final int NOTE_LANE_F = 436;
	public static final int NOTE_LANE_SPACE = 540;
	public static final int NOTE_LANE_J = 744;
	public static final int NOTE_LANE_K = 848;
	public static final int NOTE_LANE_L = 952;

	// 판정 범위 상수
	public static final int PERFECT_RANGE = 14;
	public static final int GREAT_RANGE = 25;
	public static final int GOOD_RANGE = 40;
	public static final int EARLY_LATE_RANGE = 55;

	// 버튼 위치 상수
	public static final int EXIT_BUTTON_X = 1245;
	public static final int EXIT_BUTTON_Y = 0;
	public static final int EXIT_BUTTON_WIDTH = 30;
	public static final int EXIT_BUTTON_HEIGHT = 30;

	public static final int START_BUTTON_X = 450;
	public static final int START_BUTTON_Y = 200;
	public static final int BUTTON_WIDTH = 400;
	public static final int BUTTON_HEIGHT = 100;

	public static final int QUIT_BUTTON_X = 450;
	public static final int QUIT_BUTTON_Y = 330;

	// 음악 선택 버튼 상수
	public static final int LEFT_BUTTON_X = 140;
	public static final int LEFT_BUTTON_Y = 310;
	public static final int RIGHT_BUTTON_X = 1080;
	public static final int RIGHT_BUTTON_Y = 310;
	public static final int MUSIC_SELECT_BUTTON_WIDTH = 60;
	public static final int MUSIC_SELECT_BUTTON_HEIGHT = 60;

	// 난이도 버튼 상수
	public static final int EASY_BUTTON_X = 375;
	public static final int EASY_BUTTON_Y = 580;
	public static final int HARD_BUTTON_X = 835;
	public static final int HARD_BUTTON_Y = 580;
	public static final int DIFFICULTY_BUTTON_WIDTH = 200;
	public static final int DIFFICULTY_BUTTON_HEIGHT = 80;

	// 뒤로가기 버튼 상수
	public static final int BACK_BUTTON_X = 20;
	public static final int BACK_BUTTON_Y = 50;
	public static final int BACK_BUTTON_WIDTH = 60;
	public static final int BACK_BUTTON_HEIGHT = 60;

	// 리소스 경로
	public static final String IMAGE_PATH = "/images/";
	public static final String MUSIC_PATH = "/music/";

	/**
	 * 프로그램의 시작점입니다.
	 * JavaMax 게임을 실행합니다.
	 * 
	 * @param args 명령줄 인수
	 */
	public static void main(String[] args) {
		// JavaMax 게임 시작
		new JavaMax();
	}

}
