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

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {//어떤한 키를 눌렸는지 확인
		if(JavaMax.game == null) {
			return;//현재 게임이 진행 되고 있지 않으면 변화없음
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			JavaMax.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			JavaMax.game.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			JavaMax.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			JavaMax.game.pressSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			JavaMax.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			JavaMax.game.pressK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			JavaMax.game.pressL();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {//어떤한 키를 눌렀을때 어떠한 반응 할지
		if(JavaMax.game == null)
			return;//현재 게임이 진행 되고 있지 않으면 변화없음
		if(e.getKeyCode() == KeyEvent.VK_S) {
			JavaMax.game.releaseS();
    	}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			JavaMax.game.releaseD();
    	}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			JavaMax.game.releaseF();
    	}           
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			JavaMax.game.releaseSpace();
    	}           
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			JavaMax.game.releaseJ();
    	}           	
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			JavaMax.game.releaseK();
    	}           	
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			JavaMax.game.releaseL();
    	}                   
	}

}
