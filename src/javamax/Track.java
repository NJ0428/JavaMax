/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바 클래스 매뉴얼.
  - 하나의 곡의 정보를 받은 클래스
  
  자바프로제트 Track 매뉴얼 :
  - 엘범표지 이름 받을수있도록
  - 생성자를 이용
 ******************************************************************************/
package javamax;

public class Track {
	private String titleImage; //제목 부분 이미지
	private String startImage; //게임 선택 창 표지 이미지
	private String gameImage; //해당 곡을 실행했을 떄 표지 이미지
	private String startMusic; //게임 선택 창 음악
	private String gameMusic; //해당 곡을 실행했음 떄 음악
	private String titleName; // 곡 제목
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}
}
