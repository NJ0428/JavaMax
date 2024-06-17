/******************************************************************************
	자바프로제트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  자바프로제트 매뉴얼.
  - 
  
  자바프로제트 Music 매뉴얼 :
  - 
 ******************************************************************************/
package javamax;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JavaMax extends JFrame{
	
	//버퍼이미지 설정
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));//나기기 이미지
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));//나기기 이미지 명도50
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));//시작버튼
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));//시작버튼 명도 50
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));//종료버튼 
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));//종료버튼 명도50
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));//노래 <= 선택버튼
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));//노래 <= 선택버튼 50
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));//노래 => 선택버튼
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));//노래 => 선택버튼 50
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));//이지 버튼
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));//이지 버튼
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));//하드 버튼
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));//하드 버튼 50
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));//뒤로가기 버튼
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));//뒤로가기버튼 50
	//private Image titleImage = new ImageIcon(Main.class.getResource("../images/Chilituna.png")).getImage();
	//private Image selectedImage = new ImageIcon(Main.class.getResource("../images/Mighty Love Title Image.png")).getImage();

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();//배경
	
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));//메뉴바 이미지
	private JButton exitButton = new JButton(exitButtonBasicImage);//프로그램 종료 버튼
	private JButton startButton = new JButton(startButtonBasicImage);//시작 버튼 
	private JButton quitButton = new JButton(quitButtonBasicImage);//종료 버튼
	private JButton leftButton = new JButton(leftButtonBasicImage);//왼쫀버튼
	private JButton rightButton = new JButton(rightButtonBasicImage);//오른쪽버튼
	private JButton easyButton = new JButton(easyButtonBasicImage);//이지 버튼
	private JButton hardButton = new JButton(hardButtonBasicImage);//하드 버튼
	private JButton backButton = new JButton(backButtonBasicImage);//뒤로가기 버튼
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen= false;//메인 스크린으로 넘어 왔는지 확인한느 변수
	private boolean isGameScreen = false;//게임 스크린으로 넘어 왔는지 확인하는 변수
	
	ArrayList<Track> trackList = new ArrayList<Track>();//어떤 변수를 담을 수 있는 배열
	
	private Image titleImage;//현재 선택된 곡의 이미지
	private Image selectedImage;//현재 선택된 곡의 이름 이미지
	private Music selectedMusic;//현재 선택된 곡
	private Music introMusic = new Music("introMusic.mp3",true);//반복실행
	private int nowSelected = 0;//현재 선택된 곡 번호
	
	public static Game game ;//game객체 생성 
	
	public JavaMax(){
		//Track으로 변수 초기화 순서적으로 값이 들어간다 (제목,게임 선택 창표지,실행했을때 표지, 하이라이트, 해당곡,곡제목) 여기는 DB로 업이트 할 예정
		trackList.add(new Track("Goodbye_mr_my.png", "hellomo.png",
				"introBackground.jpg", "hellomy.mp3", "hellomy.mp3","Goodbye Mr. My 머리카락"));
		trackList.add(new Track("Nathan_Evans_Wellerman.png", "Energy Start Image.png",
				"introBackground.jpg", "Wellerman.mp3", "Wellerman.mp3","Nathan Evans Wellerman"));
		trackList.add(new Track("Super_Shy_NewJeans.png", "Super Shy.gif",
				"introBackground.jpg", "Super Shy.mp3", "Super Shy.mp3","Super Shy 뉴진스"));
		
		setUndecorated(true);//메뉴 바가 보이지 않는다
		setTitle("JavaMax");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);//사용자가 크기조절 금지
		setLocationRelativeTo(null);//컴퓨터 정중앙 정렬
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프로그램 종료
		setVisible(true);//게임창 보여주기
		setBackground(new Color(0,0,0,0));//화이트 색으로
		setLayout(null);//JLabel정확한 위치로
		
		addKeyListener(new javamax.KeyListener());//키입력 이벤트 처리
		
		introMusic.start();
		
		exitButton.setBounds(1245,0,30,30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				exitButton.setIcon(exitButtonEnteredImage);//종료 버튼 바꾸기
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 나왔을때
				exitButton.setIcon(exitButtonBasicImage);//종료버튼 바꾸기
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 디몰트 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);//1초 대기
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);//프로그램 종료 버튼 생성
		
		//시작 버튼
		startButton.setBounds(450, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				startButton.setIcon(startButtonEnteredImage);//시작 버튼 바꾸기
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 나왔을때
				startButton.setIcon(startButtonBasicImage);//시작버튼 바꾸기
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 디몰트 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
				enterMain();//메인함수 들어가자
					
			}
		});
		add(startButton);//게임 시작 버튼 생성
		
		//종료버튼
		quitButton.setBounds(450, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				quitButton.setIcon(quitButtonEnteredImage);//시작 버튼 바꾸기
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 나왔을때
				quitButton.setIcon(quitButtonBasicImage);//종료 버튼 바꾸기
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 디몰트 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);//1초 대기
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);//종료 버튼
		
		//음악 선택 왼쪽 버튼
		leftButton.setVisible(false);//보이도록 하기
		leftButton.setBounds(140, 310, 60, 60);//위치
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				leftButton.setIcon(leftButtonEnteredImage);//음악선택 왼쪽 바꾸기
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 나왔을때
				leftButton.setIcon(leftButtonBasicImage);//음악선택 왼쪽 버튼 바꾸기
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 디몰트 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		add(leftButton);//음악선택 왼쪽 버튼
		
		//음학 선택 왼쪽 버튼
		rightButton.setVisible(false);//보이도록 하기
		rightButton.setBounds(1080, 310, 60, 60);//위치
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				rightButton.setIcon(rightButtonEnteredImage);//음악선택 오른쪽 바꾸기
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 나왔을때
				rightButton.setIcon(rightButtonBasicImage);//음악선택 오른쪽 버튼 바꾸기
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 디몰트 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);//음악선택 왼쪽 버튼
		
		//이지
		easyButton.setVisible(false);//보이도록 하기
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 나왔을때
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//음악선택 오른쪽 버튼 바꾸기
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스를 올라갈떄 할떄
				easyButton.setIcon(easyButtonBasicImage);//음악선택 오른쪽 바꾸기
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);///버튼 클릭음
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);//이지 버튼
		
		//하드
		hardButton.setVisible(false);//보이도록 하기
		hardButton.setBounds(655, 580, 250, 67);//위치
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				hardButton.setIcon(hardButtonEnteredImage);//음악선택 오른쪽 바꾸기
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스를 올라갈떄 할떄
				hardButton.setIcon(hardButtonBasicImage);//음악선택 오른쪽 버튼 바꾸기
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
			}
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);///버튼 클릭음
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);//하드 버튼
		
		//뒤로가기 버튼
		backButton.setVisible(false);//보이도록 하기
		backButton.setBounds(20, 50, 60, 60);//위치
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {//마우스를 올라갈떄 할떄
				backButton.setIcon(backButtonEnteredImage);//뒤로가기
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스 올라갈때 손가락 모양으로 바꾼다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {//마우스가 나왔을때
				backButton.setIcon(backButtonBasicImage);//뒤로가기 버튼 바꾸기
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//마우스 올라갈때 디몰트 모양으로 바꾼다
			}
			@Override
			//메인 화면으로 돌아가는 이벤트
			public void mousePressed(MouseEvent e) {//버튼을 클릭할때
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);//버튼 클릭음
				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);//뒤로가기 버튼
		
		menuBar.setBounds(0,0,1280,30);//위치와 크기
		menuBar.addMouseListener(new MouseAdapter() {//마우스 익명 글래스
			@Override
			public void mousePressed(MouseEvent e) {//버튼을 눌렸을떄
				mouseX = e.getX();//마우스x위치
				mouseY = e.getY();//마우스Y위치
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {//드래그 할떄 이벤트 발생
				int x = e.getXOnScreen();//스크린 x좌표 가져오기
				int y = e.getYOnScreen();//스크린 y죄표 가져오기
				setLocation(x - mouseX, y - mouseY);//위치를 바꾼다
			}
		});
		add(menuBar);//메뉴바추가
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		screenGraphic=screenImage.getGraphics(); //그래픽 객체 얻어오기
		screenDraw((Graphics2D)screenGraphic);//드로우에 그림기리기 Graphics2 으로 형변환
		g.drawImage(screenImage, 0, 0, null);//0,0에 위치에 그림을 기려준다
	}
	
	public void screenDraw(Graphics2D g) {//backgroud이미지를screenImage넣는다
		g.drawImage(background, 0, 0, null);//0,0에 위치에 그림을 기려준다
		if(isMainScreen){//true경우 츨력
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen)
		{
			game.screenDraw(g);//게임 클래스 
		}
		if(isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);//background이외에JLabel를 추가해주는 부분을 보여준다.
		//화면 재조정
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.repaint();//프로그램 반복되면서 돌려준다
	}
	
	public void selectTrack(int nowSelected){//현재 선택한 곡의 번호 넣어서 선택한 것을 알려준다
		if(selectedMusic != null)//selectedMusic가 없다면 종료
			selectedMusic.close();//어떤 곡이 실행 되고 있다면 종료
		//현재 선택한 곡의 타이틀을 가져와서 넣어준다.
		//음악 이미지
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);//선택한 곡 하이라트 부분 무안 재생 
		selectedMusic.start();//현재 선택한 곡의 타이틀을 가져와서 넣어준다.
	}
	
	//노래 선택 왼존버튼 오른쪽 버튼 눌렸을때 이벤트
	public void selectLeft(){
		if(nowSelected == 0)
			nowSelected = trackList.size() - 1;//0번 쨰 곡을 때는 가장 오른쪽 곡으로 간다
		else
			nowSelected--;//아닐경우 1을 뺸다
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1)//마지막곡이라면  0번쨰 곡을 때는 간다
			nowSelected = 0;
		else
			nowSelected++;//아닐경우 1을 더 한다.
		selectTrack(nowSelected);
	}
	
	public void backMain() {
		isMainScreen = true;//메인으로 돌아옴
		
		//버튼들이 보이도록 수정
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		backButton.setVisible(false);//뒤로가기 숨기기
		selectTrack(nowSelected);//하이라이트 부분 재생
		isGameScreen = false;
		game.close();// 곡 을 종료 함으로 서 다른 곡을 선택 할수 있도록 하기
	}
	public void gameStart(int nowSelected, String difficulty) {//모드의 정보를 받는다
		if(selectedMusic != null)//음악이 실행중이라면
			selectedMusic.close();//음악 종료
		
		isMainScreen = false;//메인 화면이 아니라고 설정
		
		//메인 화면 버튼을 숨김
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();//현재 선택이 된 게임의 이미지를 불러온다
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());//현재선태된 곡을 
		game.start();//런함수 실행 (노트 생성)
		setFocusable(true);//키보드 포커스를 맞춤
	}
	
	public void enterMain() {
		//Music selectedMusic = new Music("Wellerman.mp3",true);//넘어 올떄 선택할 곡 재생(하이라이트 곡재생 30초)
		//selectedMusic.start();//시작
		//게임시작 이벤트(메인 화면으로 이동)
		startButton.setVisible(false);//시작버튼 안 보이도록 하기
		quitButton.setVisible(false);//종료버튼 안 보이도록 하기

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();//게임 시작 배경
		
		isMainScreen = true;//선택곡 이미지로 변경
	
		//음악 선택 버튼
		leftButton.setVisible(true);//음악선택 왼쪽 버튼 보이도록 하기
		rightButton.setVisible(true);//음악선택 오른쪽 버튼 보이도록 하기
		
		//모드
		easyButton.setVisible(true);//이지 모드
		hardButton.setVisible(true);//하드 모드
		
		introMusic.close();
		
		selectTrack(0);//0번째 곡을 선택해라
	}
}
