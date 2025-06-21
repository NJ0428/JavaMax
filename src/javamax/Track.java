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

/**
 * 음악 트랙 정보를 관리하는 클래스
 * 하나의 곡에 대한 모든 정보를 담고 있습니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class Track {
	private String titleImage; // 제목 부분 이미지
	private String startImage; // 게임 선택 창 표지 이미지
	private String gameImage; // 해당 곡을 실행했을 때 표지 이미지
	private String startMusic; // 게임 선택 창 음악
	private String gameMusic; // 해당 곡을 실행했을 때 음악
	private String titleName; // 곡 제목

	/**
	 * Track 생성자
	 * 
	 * @param titleImage 제목 이미지 파일명
	 * @param startImage 시작 화면 이미지 파일명
	 * @param gameImage  게임 화면 이미지 파일명
	 * @param startMusic 시작 음악 파일명
	 * @param gameMusic  게임 음악 파일명
	 * @param titleName  곡 제목
	 */
	public Track(String titleImage, String startImage, String gameImage,
			String startMusic, String gameMusic, String titleName) {
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}

	/**
	 * 기본 생성자
	 */
	public Track() {
		this("", "", "", "", "", "");
	}

	// Getter 메서드들
	public String getTitleImage() {
		return titleImage;
	}

	public String getStartImage() {
		return startImage;
	}

	public String getGameImage() {
		return gameImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public String getTitleName() {
		return titleName;
	}

	// Setter 메서드들
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * 트랙이 유효한지 확인합니다.
	 * 
	 * @return 모든 필드가 null이 아니고 빈 문자열이 아니면 true
	 */
	public boolean isValid() {
		return titleImage != null && !titleImage.trim().isEmpty() &&
				startImage != null && !startImage.trim().isEmpty() &&
				gameImage != null && !gameImage.trim().isEmpty() &&
				startMusic != null && !startMusic.trim().isEmpty() &&
				gameMusic != null && !gameMusic.trim().isEmpty() &&
				titleName != null && !titleName.trim().isEmpty();
	}

	/**
	 * 트랙의 이미지 리소스가 존재하는지 확인합니다.
	 * 
	 * @return 이미지 파일들이 존재하면 true
	 */
	public boolean hasValidImages() {
		try {
			return Main.class.getResource(Main.IMAGE_PATH + titleImage) != null &&
					Main.class.getResource(Main.IMAGE_PATH + startImage) != null &&
					Main.class.getResource(Main.IMAGE_PATH + gameImage) != null;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 트랙의 음악 리소스가 존재하는지 확인합니다.
	 * 
	 * @return 음악 파일들이 존재하면 true
	 */
	public boolean hasValidMusic() {
		try {
			return Main.class.getResource(Main.MUSIC_PATH + startMusic) != null &&
					Main.class.getResource(Main.MUSIC_PATH + gameMusic) != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return String.format("Track{titleName='%s', titleImage='%s', startImage='%s', " +
				"gameImage='%s', startMusic='%s', gameMusic='%s'}",
				titleName, titleImage, startImage, gameImage, startMusic, gameMusic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Track track = (Track) obj;
		return titleName != null ? titleName.equals(track.titleName) : track.titleName == null;
	}

	@Override
	public int hashCode() {
		return titleName != null ? titleName.hashCode() : 0;
	}

	/**
	 * Track 객체를 생성하는 Builder 클래스
	 */
	public static class Builder {
		private String titleImage = "";
		private String startImage = "";
		private String gameImage = "";
		private String startMusic = "";
		private String gameMusic = "";
		private String titleName = "";

		public Builder titleImage(String titleImage) {
			this.titleImage = titleImage;
			return this;
		}

		public Builder startImage(String startImage) {
			this.startImage = startImage;
			return this;
		}

		public Builder gameImage(String gameImage) {
			this.gameImage = gameImage;
			return this;
		}

		public Builder startMusic(String startMusic) {
			this.startMusic = startMusic;
			return this;
		}

		public Builder gameMusic(String gameMusic) {
			this.gameMusic = gameMusic;
			return this;
		}

		public Builder titleName(String titleName) {
			this.titleName = titleName;
			return this;
		}

		public Track build() {
			return new Track(titleImage, startImage, gameImage, startMusic, gameMusic, titleName);
		}
	}
}
