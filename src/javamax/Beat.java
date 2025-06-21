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

/**
 * 음악의 비트 정보를 담는 클래스
 * 노트의 타이밍과 종류를 저장합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class Beat {

	private int time;// 타이밍
	private String noteName;// 노트 종류

	/**
	 * Beat 생성자
	 * 
	 * @param time     비트가 발생하는 시간 (밀리초)
	 * @param noteName 노트 종류 (S, D, F, Space, J, K, L)
	 */
	public Beat(int time, String noteName) {
		this.time = time;
		this.noteName = noteName;
	}

	/**
	 * 기본 생성자
	 */
	public Beat() {
		this(0, "");
	}

	/**
	 * 비트 시간을 반환합니다.
	 * 
	 * @return 비트 시간 (밀리초)
	 */
	public int getTime() {
		return time;
	}

	/**
	 * 비트 시간을 설정합니다.
	 * 
	 * @param time 설정할 시간 (밀리초)
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * 노트 이름을 반환합니다.
	 * 
	 * @return 노트 이름
	 */
	public String getNoteName() {
		return noteName;
	}

	/**
	 * 노트 이름을 설정합니다.
	 * 
	 * @param noteName 설정할 노트 이름
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	/**
	 * 유효한 노트인지 확인합니다.
	 * 
	 * @return 유효한 노트면 true, 그렇지 않으면 false
	 */
	public boolean isValidNote() {
		return noteName != null &&
				(noteName.equals("S") || noteName.equals("D") || noteName.equals("F") ||
						noteName.equals("Space") || noteName.equals("J") || noteName.equals("K") ||
						noteName.equals("L"));
	}

	@Override
	public String toString() {
		return String.format("Beat{time=%d, noteName='%s'}", time, noteName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Beat beat = (Beat) obj;
		return time == beat.time &&
				(noteName != null ? noteName.equals(beat.noteName) : beat.noteName == null);
	}

	@Override
	public int hashCode() {
		return time * 31 + (noteName != null ? noteName.hashCode() : 0);
	}
}
