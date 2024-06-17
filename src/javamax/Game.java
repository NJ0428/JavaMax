/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바 클래스 매뉴얼.
  - 게임이 시작이 되면 클래스에서 만들어진 인스턴스변수를 이용해서 게임의 컨트롤를 할수 있게 만든다
  
  자바프로제트 Game 매뉴얼 :
  - 주의 게임이 시작 할떄 키보드 포커스를 맞쳐야함
  - 노트 판정이 580
 ******************************************************************************/

package javamax;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Game extends Thread {
	//private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png"))
			.getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png"))
			.getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png"))
			.getImage();//

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	
	//Space1와 Space2 나누어진 이유는 다른 노트보다 길어서 
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlare;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	
	private String titleName;//현재 실행할 곡
	private String difficulty;//난이도 설정
	private String musicTitle;//곡 제목
	private Music gameMusic;//게임 곡
	
	//각각의 노트를 관리
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {//
		
		//변수 초기화
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		
		gameMusic = new Music(this.musicTitle, false);//한번번 실행 되도록
		
		//dropNotes(titleName);
	}
	
	public void screenDraw(Graphics2D g) {
		//노트 경로
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		
		//노트 구분선
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		g.drawImage(gameInfoImage, 0, 660, null);//아래 게임정보를 보여준다
		
		g.drawImage(judgementLineImage, 0, 580, null);// 리듬게임 판정 선
		for(int i = 0; i < noteList.size(); i++){//노트 크기 까지 노트를 불러와서 출력 한다. 
			Note note = noteList.get(i);//노트를 생성 해줌
			if(note.getY() > 620) {//620경우 miss로
				judgeImage= new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();//노트가 넘어가면
			}
			if(!note.isProceeded()){//현재노트가 작동중이 아니라면
				noteList.remove(i);//사용하지 않은 노트는 지워진다
				i--;
			}else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);//컬러를 화이트로
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//안티엘리어싱 설정
		g.setColor(Color.WHITE);//컬러를 화이트로
		g.setFont(new Font("고딕", Font.BOLD, 30));//폰트 설정
		g.drawString(titleName, 20, 702);//현재 곡을 출력
		g.drawString(difficulty, 1190, 702);//난이도 확인
		g.setFont(new Font("Arial", Font.PLAIN, 26));//폰트사용
		g.setColor(Color.DARK_GRAY);
		
		//노트 키패드 확인 
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);//현재 점수 확인
		g.drawImage(blueFlare, 320, 230, null);//플레어 이미지
		g.drawImage(judgeImage, 460, 420, null);//판정 이미지 
		g.drawImage(keyPadSImage, 228,580, null);//s키 이미지
		g.drawImage(keyPadDImage, 332,580, null);//D키 이미지
		g.drawImage(keyPadFImage, 436,580, null);//F키 이미지
		g.drawImage(keyPadSpace1Image, 540,580, null);//space키 이미지
		g.drawImage(keyPadSpace2Image, 640,580, null);//space키 이미지
		g.drawImage(keyPadJImage, 744,580, null);//J키 이미지
		g.drawImage(keyPadKImage, 848,580, null);//K키 이미지
		g.drawImage(keyPadLImage, 952,580, null);//L키 이미지
		
		//현제 실행중이 곡 정보를 보여줌
	}
	
	public void pressS() {//키보드에서 S 를 눌렸을떄 눌렀을때 색 변경 
		judge("S");//넣어서 판정을 확인
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();		
		new Music("drumSmall1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseS() {//키보드에서 S 를 때어낼 경우 색 돌아옴 
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	}
	
	public void pressD() {//키보드에서 D 를 눌렸을떄 눌렀을때 색 변경 
		judge("D");//넣어서 판정을 확인
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		new Music("drumSmall1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseD() {//키보드에서 D 를 때어낼 경우 색 돌아옴 
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	}

	public void pressF() {//키보드에서 F 를 눌렸을떄 눌렀을때 색 변경 
		judge("F");//넣어서 판정을 확인
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		new Music("drumSmall1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseF() {//키보드에서 F 를 때어낼 경우 색 돌아옴 
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	}

	public void pressSpace() {//키보드에서 Space 를 눌렸을떄 눌렀을때 색 변경 
		judge("Space");//넣어서 판정을 확인
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		new Music("drumBig1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseSpace() {//키보드에서 Space 를 때어낼 경우 색 돌아옴 
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	}
	
	public void pressJ() {//키보드에서 J 를 눌렸을떄 눌렀을때 색 변경 
		judge("J");//넣어서 판정을 확인
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		new Music("drumSmall1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseJ() {//키보드에서 J 를 때어낼 경우 색 돌아옴 
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
		
	}
	
	public void pressK() {//키보드에서 K 를 눌렸을떄 눌렀을때 색 변경 
		judge("K");//넣어서 판정을 확인
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		new Music("drumSmall1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseK() {//키보드에서 K 를 때어낼 경우 색 돌아옴 
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	}
	
	public void pressL() {//키보드에서 L 를 눌렸을떄 눌렀을때 색 변경 
		judge("L");//넣어서 판정을 확인
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();	
		new Music("drumSmall1.mp3", false).start();//입력시 소리설정
	}
	
	public void releaseL() {//키보드에서 L 를 때어낼 경우 색 돌아옴 
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/KeyPadBasic.png")).getImage();
	}
	@Override
	public void run() {
		dropNotes();
	}
	public void close() {//게임 노래 종료
		gameMusic.close();
		this.interrupt();//쓰래드 종료
	}
	public void dropNotes() {//각각의 노트가 떨어질수도 있게 만든다
		//들어갈 배열을 나누어준다.
		/*Beat[] beats = {//비트 변수변에 비트를 넣어주면 비트추가
			new Beat(1000,"S"),//1초 S 노트 출력
			new Beat(2000,"D"),//2초 D 노트 출력
			new Beat(3000,"F"),//3초 F 노트 출력
			
		};*/
		Beat[] beats=null;
		//해당 곡에 따라서 서로 다른게 출력
		if(titleName.equals("Goodbye Mr. My 머리카락")&& difficulty.equals("Easy")) {//실행곡이 Wellerman이라면 
			int startTime=1400-Main.REACH_TIME*1000;//똑같은 노트 타이밍르 정해준다
			int gap = 125;//최소 박자의 간격
			beats = new Beat[] {//음악과 싱크를 맞춘다
					new Beat(startTime,"Space"),//스페이스에 노트를 보여준다
					new Beat(startTime+gap*2,"S"),
					new Beat(startTime+gap*4,"D"),
					new Beat(startTime+gap*6,"S"),
					new Beat(startTime+gap*8,"D"),
					new Beat(startTime+gap*12,"D"),
					new Beat(startTime+gap*14,"K"),
					new Beat(startTime+gap*12,"Space"),
					new Beat(startTime+gap*18,"L"),
					new Beat(startTime+gap*20,"F"),
					new Beat(startTime+gap*22,"D"),
					new Beat(startTime+gap*26,"F"),
					new Beat(startTime+gap*28,"L"),
					new Beat(startTime+gap*32,"K"),
					new Beat(startTime+gap*35,"J"),
					new Beat(startTime+gap*42,"K"),
					new Beat(startTime+gap*52,"D"),
					new Beat(startTime+gap*62,"S"),
					new Beat(startTime+gap*64,"D"),
					new Beat(startTime+gap*66,"S"),
					new Beat(startTime+gap*68,"D"),
					new Beat(startTime+gap*72,"S"),
					new Beat(startTime+gap*77,"D"),
					new Beat(startTime+gap*81,"S"),
					new Beat(startTime+gap*82,"D"),
					new Beat(startTime+gap*89,"S"),
					new Beat(startTime+gap*92,"D"),
					new Beat(startTime+gap*98,"S"),
					new Beat(startTime+gap*99,"D"),
					new Beat(startTime+gap*100,"S"),
					new Beat(startTime+gap*102,"D"),
					new Beat(startTime+gap*104,"S"),
					new Beat(startTime+gap*108,"D"),
					new Beat(startTime+gap*(2+110),"S"),
					new Beat(startTime+gap*(4+110),"D"),
					new Beat(startTime+gap*(6+110),"S"),
					new Beat(startTime+gap*(8+110),"D"),
					new Beat(startTime+gap*(12+110),"D"),
					new Beat(startTime+gap*(14+110),"K"),
				    new Beat(startTime+gap*(12+110),"Space"),
				    new Beat(startTime+gap*(18+110),"L"),
				    new Beat(startTime+gap*(20+110),"F"),
				    new Beat(startTime+gap*(22+110),"D"),
				    new Beat(startTime+gap*(26+110),"F"),
				    new Beat(startTime+gap*(28+110),"L"),
				    new Beat(startTime+gap*(32+110),"K"),
				    new Beat(startTime+gap*(35+110),"J"),
				    new Beat(startTime+gap*(42+110),"K"),
				    new Beat(startTime+gap*(52+110),"D"),
				    new Beat(startTime+gap*(62+110),"S"),
				    new Beat(startTime+gap*(64+110),"D"),
				    new Beat(startTime+gap*(66+110),"S"),
				    new Beat(startTime+gap*(68+110),"D"),
				    new Beat(startTime+gap*(72+110),"S"),
				    new Beat(startTime+gap*(77+110),"D"),
				    new Beat(startTime+gap*(81+110),"S"),
				    new Beat(startTime+gap*(82+110),"D"),
				    new Beat(startTime+gap*(89+110),"S"),
				    new Beat(startTime+gap*(92+110),"D"),
				    new Beat(startTime+gap*(98+110),"S"),
				    new Beat(startTime+gap*(99+110),"D"),
					new Beat(startTime+gap*210,"S"),
					new Beat(startTime+gap*212,"D"),
					new Beat(startTime+gap*214,"S"),
					new Beat(startTime+gap*218,"D"),
			};
		}else {
			int startTime=400-Main.REACH_TIME*1000;//똑같은 노트 타이밍르 정해준다
			int gap = 125;//최소 박자의 간격
			beats = new Beat[] {//음악과 싱크를 맞춘다
					new Beat(startTime,"Space"),//스페이스에 노트를 보여준다
					new Beat(startTime+gap*2,"S"),
					new Beat(startTime+gap*4,"D"),
					new Beat(startTime+gap*6,"S"),
					new Beat(startTime+gap*8,"D"),
					new Beat(startTime+gap*12,"D"),
					new Beat(startTime+gap*14,"K"),
					new Beat(startTime+gap*12,"Space"),
					new Beat(startTime+gap*18,"L"),
					new Beat(startTime+gap*20,"F"),
					new Beat(startTime+gap*22,"D"),
					new Beat(startTime+gap*26,"F"),
					new Beat(startTime+gap*28,"L"),
					new Beat(startTime+gap*32,"K"),
					new Beat(startTime+gap*35,"J"),
					new Beat(startTime+gap*42,"K"),
					new Beat(startTime+gap*52,"D"),
			};
		}
		//else if(titleName.equals("Wellerman"))//다른곡
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {//현재 노래를 학인해서 노트를 기능을 구현
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {//만약에 비트가 떨어지는 시간때가 게임 시간보다 작다면
				Note note = new Note(beats[i].getNoteName());//노트를 선언해서 구현하단
				note.start();
				noteList.add(note);//노트 추가
				i++;//노트를 1개식 접근해서 실행하기
				dropped=true;
			}
			if(!dropped){//중간에 후식 시간을 넣는다
				try{
					Thread.sleep(5);//0.005초 동안 쉼
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	//큐 처럼 구현
	public void judge(String input){//판정 메소드
		for (int i=0; i<noteList.size(); i++) {//가장 먼저 노트 부터
			Note note = noteList.get(i);//노트를 방문
			if(input.equals(note.getNoteType())) {// 입력한갑이 타입에 맞다면
				judgeEvent(note.judge());//판정을 수행
				break;
			}
		}
	}
	
	public void judgeEvent(String judge){//판정 이미지 바꾸기
		if(!judge.equals("None")) {
			blueFlare = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		}
		else if(judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		}
		else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		}
		else if(judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}
		
	}
}
