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

public class Note extends Thread {
	
	//이미지폴더 의 노트를 거저온다
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	
	//현재 노트가 어디있는지
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED)*Main.REACH_TIME;// 노트 생성된고 1초뒤에 판정라이 
	private String noteType;
	
	//현재 노트 진행여부
	private boolean Proceeded = true;
	
	public String getNoteType() {//노트의 타입을 반환
		return noteType;
	}
	
	public boolean isProceeded() {
		return Proceeded;
	}
	public void close() {//노트 종료
		Proceeded = false;
	}
	
	//생성자
	public Note(String noteType){//노트의 위치 노트 타입
		//초기화
		//this.x = x;
		//this.noteType = noteType;
		
		//노트 타입이 키에 맞다면 키를 동적으로 바꾼다
		
		if(noteType.equals("S")) {
			x=228;
		}else if(noteType.equals("D")) {
			x=332;
		}else if(noteType.equals("F")) {
			x=436;
		}else if(noteType.equals("Space")) {
			x=540;
		}else if(noteType.equals("J")) {
			x=744;
		}else if(noteType.equals("K")) {
			x=848;
		}else if(noteType.equals("L")) {
			x=952;
		}
		this.noteType = noteType;//노트를 넣어주준다
		
	}
	
	public void screenDraw(Graphics2D g) {//하나의 노트를 그린다.
		if(!noteType.equals("Space")){//잛은 안 노트라면
			g.drawImage(noteBasicImage, x, y, null);//현재 위치한 공간에 넣어준다.
		}else{//아닐 겨우
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;//NOTE_SPEED만큼 떨어진다
		//Missd
		if(y>620) { //노트가 판정바 620이 넘으면 
			System.out.println("Miss");
			close();
		}
	}
	@Override
	public void run() {//쓰레드 실행
		try {
			while (true) {//무한 반복
				drop();
				if(Proceeded) {//만약에 진행중이면 슬립 타입 만큼 쉰다
					Thread.sleep(Main.SLEEP_TIME);
				}else {
					interrupt();//쓰레드 멈춤
					break;
				}
				Thread.sleep(Main.SLEEP_TIME);//슬림타임 만큼 노트 정지
			}
		} catch(Exception e) {//오류 출력
			System.err.println(e.getMessage());
		}
	}
	public String judge() {//게임 판정
		if(y >=613) {
			System.out.print("Late");
			close();
			return "Late";
		}else if(y >=600) {
			System.out.print("Good");
			close();
			return "Good";
		}else if(y >=587) {
			System.out.print("Great");
			close();
			return "Great";
		}else if(y >=573) {
			System.out.print("Perfect");
			close();
			return "Perfect";
		}else if(y >=565) {
			System.out.print("Great");
			close();
			return "Great";
		}else if(y >=550) {
			System.out.print("Good");
			close();
			return "Good";
		}else if(y >=535) {
			System.out.print("Early");
			close();
			return "Early";
		}
		return "None";		
	}
	
	public int getY() {//y좌표를 반환해준다.
		return y;
	}
}
