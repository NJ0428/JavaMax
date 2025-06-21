/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바 클래스 매뉴얼.
  - 사용자가 키보드를 입력을 발생시키면 감지하는 클래스
  
  자바프로제트 keyAdapter 매뉴얼 :
  - 
 ******************************************************************************/
package javamax;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 키보드 입력을 처리하는 클래스
 * 게임 진행 중 키 입력을 감지하고 해당 액션을 실행합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class KeyListener extends KeyAdapter {

	// 키 코드와 액션을 매핑하는 Map
	private static final Map<Integer, String> KEY_MAPPING;

	static {
		KEY_MAPPING = new HashMap<>();
		KEY_MAPPING.put(KeyEvent.VK_S, "S");
		KEY_MAPPING.put(KeyEvent.VK_D, "D");
		KEY_MAPPING.put(KeyEvent.VK_F, "F");
		KEY_MAPPING.put(KeyEvent.VK_SPACE, "Space");
		KEY_MAPPING.put(KeyEvent.VK_J, "J");
		KEY_MAPPING.put(KeyEvent.VK_K, "K");
		KEY_MAPPING.put(KeyEvent.VK_L, "L");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isGameActive()) {
			return;
		}

		String keyType = KEY_MAPPING.get(e.getKeyCode());
		if (keyType != null) {
			executeKeyPressAction(keyType);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!isGameActive()) {
			return;
		}

		String keyType = KEY_MAPPING.get(e.getKeyCode());
		if (keyType != null) {
			executeKeyReleaseAction(keyType);
		}
	}

	/**
	 * 게임이 활성 상태인지 확인합니다.
	 * 
	 * @return 게임이 활성 상태면 true
	 */
	private boolean isGameActive() {
		return JavaMax.game != null;
	}

	/**
	 * 키 눌림 액션을 실행합니다.
	 * 
	 * @param keyType 키 타입
	 */
	private void executeKeyPressAction(String keyType) {
		try {
			switch (keyType) {
				case "S":
					JavaMax.game.pressS();
					break;
				case "D":
					JavaMax.game.pressD();
					break;
				case "F":
					JavaMax.game.pressF();
					break;
				case "Space":
					JavaMax.game.pressSpace();
					break;
				case "J":
					JavaMax.game.pressJ();
					break;
				case "K":
					JavaMax.game.pressK();
					break;
				case "L":
					JavaMax.game.pressL();
					break;
			}
		} catch (Exception ex) {
			System.err.println("키 입력 처리 중 오류 발생: " + ex.getMessage());
		}
	}

	/**
	 * 키 해제 액션을 실행합니다.
	 * 
	 * @param keyType 키 타입
	 */
	private void executeKeyReleaseAction(String keyType) {
		try {
			switch (keyType) {
				case "S":
					JavaMax.game.releaseS();
					break;
				case "D":
					JavaMax.game.releaseD();
					break;
				case "F":
					JavaMax.game.releaseF();
					break;
				case "Space":
					JavaMax.game.releaseSpace();
					break;
				case "J":
					JavaMax.game.releaseJ();
					break;
				case "K":
					JavaMax.game.releaseK();
					break;
				case "L":
					JavaMax.game.releaseL();
					break;
			}
		} catch (Exception ex) {
			System.err.println("키 해제 처리 중 오류 발생: " + ex.getMessage());
		}
	}

	/**
	 * 지원하는 키인지 확인합니다.
	 * 
	 * @param keyCode 키 코드
	 * @return 지원하는 키면 true
	 */
	public static boolean isSupportedKey(int keyCode) {
		return KEY_MAPPING.containsKey(keyCode);
	}

	/**
	 * 키 코드에 해당하는 키 타입을 반환합니다.
	 * 
	 * @param keyCode 키 코드
	 * @return 키 타입 문자열
	 */
	public static String getKeyType(int keyCode) {
		return KEY_MAPPING.get(keyCode);
	}
}
