/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바프로제트 매뉴얼.
  - 
  
  자바프로제트 Note 매뉴얼 :
  - 노트 타이밍과 노트 종류를 보여주는 클래스
 ******************************************************************************/

package javamax;

public class Beat {
	
	private int time;//타이밍
	private String noteName;//노트 종류
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
	
	
	
}
