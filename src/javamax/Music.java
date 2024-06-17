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

import javazoom.jl.player.Player;

public class Music extends Thread{
	
	private Player player;//자바라이브러이 사용
	private boolean isLoop;//무한 반복설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop){//생성자 (곡 재목, 무한반복)
		try {
			this.isLoop= isLoop;//루프 초기화
			file = new File(Main.class.getResource("../music/" + name).toURI());//음악을 가져원다
			fis = new FileInputStream(file);//퍼버를 답아서 가져온다.
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public int getTime(){//현재실행되는 시간을 반환하는 함수
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close(){//언제 실행해도 종료할수 있게 만드는 함수
		isLoop = false;
		player.close();
		this.interrupt();//쓰레드 중지
	}
	
	@Override
	public void run(){
		try{
			do{
				player.play();//곡을 실행 다음에
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);//isLoop가 무한 반복
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
