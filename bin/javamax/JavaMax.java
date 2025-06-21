/******************************************************************************
	?�바?�로?�트 JavaMax

	update : 2024-02-20
	
	관리자 : PNJ
	
  ?�바?�로?�트 매뉴??
  - 
  
  ?�바?�로?�트 Music 매뉴??:
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

public class JavaMax extends JFrame {

	// 버퍼?��?지 ?�정
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/exitButtonEntered.png"));// ?�기�?	// ?��?지
	private ImageIcon exitButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/exitButtonBasic.png"));// ?�기�?	// ?��?지
	// 명도50
	// ?��?지 버튼?��? ?�스??버튼?�로 변경되??주석 처리
	// private ImageIcon startButtonEnteredImage = new
	// ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	// private ImageIcon startButtonBasicImage = new
	// ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	// private ImageIcon quitButtonEnteredImage = new
	// ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	// private ImageIcon quitButtonBasicImage = new
	// ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/leftButtonEntered.png"));// ?�래
	// <=
	// ?�택버튼
	private ImageIcon leftButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/leftButtonBasic.png"));// ?�래
	// <=
	// ?�택버튼
	// 50
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/rightButtonEntered.png"));// ?�래 => ?�택버튼
	private ImageIcon rightButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/rightButtonBasic.png"));// ?�래
	// =>
	// ?�택버튼
	// 50
	private ImageIcon easyButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/easyButtonEntered.png"));// ?��?
	// 버튼
	private ImageIcon easyButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/easyButtonBasic.png"));// ?��?
	// 버튼
	private ImageIcon hardButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/hardButtonEntered.png"));// ?�드
	// 버튼
	private ImageIcon hardButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/hardButtonBasic.png"));// ?�드
	// 버튼
	// 50
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/backButtonEntered.png"));// ?�로가�?	// 버튼
	private ImageIcon backButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/backButtonBasic.png"));// ?�로가기버??	// 50
	// private Image titleImage = new
	// ImageIcon(Main.class.getResource("../images/Chilituna.png")).getImage();
	// private Image selectedImage = new
	// ImageIcon(Main.class.getResource("../images/Mighty Love Title
	// Image.png")).getImage();

	private Image background = new ImageIcon(Main.class.getResource("../images/backgrounds/introBackground.jpg"))
			.getImage();// 배경

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/ui/game/menuBar.png")));// 메뉴�?																												// ?��?지
	private JButton exitButton = new JButton(exitButtonBasicImage);// ?�로그램 종료 버튼
	private JButton startButton = new JButton("?�작?�기");// ?�작 버튼
	private JButton quitButton = new JButton("종료?�기");// 종료 버튼
	private JButton leftButton = new JButton(leftButtonBasicImage);// ?��?버튼
	private JButton rightButton = new JButton(rightButtonBasicImage);// ?�른쪽버??	private JButton easyButton = new JButton(easyButtonBasicImage);// ?��? 버튼
	private JButton hardButton = new JButton(hardButtonBasicImage);// ?�드 버튼
	private JButton backButton = new JButton(backButtonBasicImage);// ?�로가�?버튼
	private JButton scoreButton = new JButton("?�수보드");// ?�수보드 버튼
	private JButton settingsButton = new JButton("?�정");// ?�정 버튼

	private int mouseX, mouseY;

	private boolean isMainScreen = false;// 메인 ?�크린으�??�어 ?�는지 ?�인?�느 변??	private boolean isGameScreen = false;// 게임 ?�크린으�??�어 ?�는지 ?�인?�는 변??	private boolean isSettingsScreen = false;// ?�정 ?�크린으�??�어 ?�는지 ?�인?�는 변??
	ArrayList<Track> trackList = new ArrayList<Track>();// ?�떤 변?��? ?�을 ???�는 배열
	private DatabaseManager dbManager;// ?�이?�베?�스 매니?�

	private Image titleImage;// ?�재 ?�택??곡의 ?��?지
	private Image selectedImage;// ?�재 ?�택??곡의 ?�름 ?��?지
	private Music selectedMusic;// ?�재 ?�택??�?	private Music introMusic = new Music("background/introMusic.mp3", true);// 반복?�행
	private int nowSelected = 0;// ?�재 ?�택??�?번호

	public static Game game;// game객체 ?�성
	private Settings settings;// ?�정 객체

	// ?�정 ?�면 관??변?�들
	private String gameLanguage = "KOREAN";
	private boolean freestyleMode = true;
	private boolean rhythmDirection = true;
	private String rhythmTag = "PERFECT";
	private int masterVolume = 50;
	private int commandVolume = 50;
	private int backgroundVolume = 50;
	private String soundEffect = "SPEAKER";
	private String resolution = "PLAYABLE";
	private boolean displayMode = true;
	private String autoPlay = "TYPE A";
	private String camera = "SETTINGS";

	public JavaMax() {
		// ?�이?�베?�스 매니?� 초기??		dbManager = DatabaseManager.getInstance();

		// Track?�로 변??초기???�서?�으�?값이 ?�어간다 (?�목,게임 ?�택 창표지,?�행?�을???��?, ?�이?�이?? ?�당�?곡제�? ?�기??DB�?		// ?�이?????�정
		trackList.add(new Track("album_covers/Goodbye_mr_my.png", "album_covers/hellomo.png",
				"backgrounds/introBackground.jpg", "game_music/hellomy.mp3", "game_music/hellomy.mp3",
				"Goodbye Mr. My 머리카락"));
		trackList.add(new Track("album_covers/Nathan_Evans_Wellerman.png", "album_covers/Energy Start Image.png",
				"backgrounds/introBackground.jpg", "game_music/Wellerman.mp3", "game_music/Wellerman.mp3",
				"Nathan Evans Wellerman"));
		trackList.add(new Track("album_covers/Super_Shy_NewJeans.png", "album_covers/Super Shy.gif",
				"backgrounds/introBackground.jpg", "game_music/Super Shy.mp3", "game_music/Super Shy.mp3",
				"Super Shy ?�진??));

		setUndecorated(true);// 메뉴 바�? 보이지 ?�는??		setTitle("JavaMax");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);// ?�용?��? ?�기조절 금�?
		setLocationRelativeTo(null);// 컴퓨???�중???�렬
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ?�로그램 종료
		setVisible(true);// 게임�?보여주기
		setBackground(new Color(0, 0, 0, 0));// ?�이???�으�?		setLayout(null);// JLabel?�확???�치�?
		addKeyListener(new javamax.KeyListener(this));// ?�입???�벤??처리

		introMusic.start();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);

		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				exitButton.setIcon(exitButtonEnteredImage);// 종료 버튼 바꾸�?				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�왔?�때
				exitButton.setIcon(exitButtonBasicImage);// 종료버튼 바꾸�?				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?�몰??모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);// 1�??��?				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);// ?�로그램 종료 버튼 ?�성

		// ?�작 버튼
		startButton.setBounds(450, 200, 400, 80);
		startButton.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		startButton.setBackground(new Color(100, 150, 250));
		startButton.setForeground(Color.WHITE);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(true);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				startButton.setBackground(new Color(120, 170, 255));// 버튼 ?�상 변�?				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�왔?�때
				startButton.setBackground(new Color(100, 150, 250));// ?�래 ?�상?�로 ?�돌리기
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?�몰??모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
				enterMain();// 메인?�수 ?�어가??
			}
		});
		add(startButton);// 게임 ?�작 버튼 ?�성

		// 종료버튼
		quitButton.setBounds(450, 290, 400, 80);
		quitButton.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		quitButton.setBackground(new Color(200, 100, 100));
		quitButton.setForeground(Color.WHITE);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(true);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				quitButton.setBackground(new Color(220, 120, 120));// 버튼 ?�상 변�?				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�왔?�때
				quitButton.setBackground(new Color(200, 100, 100));// ?�래 ?�상?�로 ?�돌리기
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?�몰??모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);// 1�??��?				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);// 종료 버튼

		// ?�악 ?�택 ?�쪽 버튼
		leftButton.setVisible(false);// 보이?�록 ?�기
		leftButton.setBounds(140, 310, 60, 60);// ?�치
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				leftButton.setIcon(leftButtonEnteredImage);// ?�악?�택 ?�쪽 바꾸�?				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�왔?�때
				leftButton.setIcon(leftButtonBasicImage);// ?�악?�택 ?�쪽 버튼 바꾸�?				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?�몰??모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		add(leftButton);// ?�악?�택 ?�쪽 버튼

		// ?�학 ?�택 ?�쪽 버튼
		rightButton.setVisible(false);// 보이?�록 ?�기
		rightButton.setBounds(1080, 310, 60, 60);// ?�치
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				rightButton.setIcon(rightButtonEnteredImage);// ?�악?�택 ?�른�?바꾸�?				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�왔?�때
				rightButton.setIcon(rightButtonBasicImage);// ?�악?�택 ?�른�?버튼 바꾸�?				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?�몰??모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);// ?�악?�택 ?�쪽 버튼

		// ?��?
		easyButton.setVisible(false);// 보이?�록 ?�기
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�왔?�때
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ?�악?�택 ?�른�?버튼 바꾸�?				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				easyButton.setIcon(easyButtonBasicImage);// ?�악?�택 ?�른�?바꾸�?				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ?�을 ?�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);/// 버튼 ?�릭??				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);// ?��? 버튼

		// ?�드
		hardButton.setVisible(false);// 보이?�록 ?�기
		hardButton.setBounds(655, 580, 250, 67);// ?�치
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				hardButton.setIcon(hardButtonEnteredImage);// ?�악?�택 ?�른�?바꾸�?				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				hardButton.setIcon(hardButtonBasicImage);// ?�악?�택 ?�른�?버튼 바꾸�?				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);/// 버튼 ?�릭??				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);// ?�드 버튼

		// ?�로가�?버튼
		backButton.setVisible(false);// 보이?�록 ?�기
		backButton.setBounds(20, 50, 60, 60);// ?�치
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우?��? ?�라갈떄 ?�떄
				backButton.setIcon(backButtonEnteredImage);// ?�로가�?				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우???�라갈때 ?��???모양?�로 바꾼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// 마우?��? ?�왔?�때
				backButton.setIcon(backButtonBasicImage);// ?�로가�?버튼 바꾸�?				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// 마우???�라갈때 ?�몰??모양?�로 바꾼??			}

			@Override
			// 메인 ?�면?�로 ?�아가???�벤??			public void mousePressed(MouseEvent e) {// 버튼???�릭?�때
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// 버튼 ?�릭??				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);// ?�로가�?버튼

		menuBar.setBounds(0, 0, 1280, 30);// ?�치?� ?�기
		menuBar.addMouseListener(new MouseAdapter() {// 마우???�명 글?�스
			@Override
			public void mousePressed(MouseEvent e) {// 버튼???�렸?�떄
				mouseX = e.getX();// 마우?�x?�치
				mouseY = e.getY();// 마우?�Y?�치
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {// ?�래�??�떄 ?�벤??발생
				int x = e.getXOnScreen();// ?�크�?x좌표 가?�오�?				int y = e.getYOnScreen();// ?�크�?y죄표 가?�오�?				setLocation(x - mouseX, y - mouseY);// ?�치�?바꾼??			}
		});
		add(menuBar);// 메뉴바추가

		// ?�수보드 버튼 ?�정
		scoreButton.setBounds(50, 650, 120, 40);
		scoreButton.setVisible(false); // 초기?�는 ?��?
		scoreButton.setFont(getKoreanFont(java.awt.Font.BOLD, 14));
		scoreButton.setBackground(Color.DARK_GRAY);
		scoreButton.setForeground(Color.WHITE);
		scoreButton.setBorderPainted(true);
		scoreButton.setFocusPainted(false);
		scoreButton.addActionListener(e -> {
			// ?�수보드 �??�기
			ScoreBoard scoreBoard = new ScoreBoard();
			scoreBoard.showScoreBoard();
		});
		add(scoreButton);

		// ?�정 버튼 ?�정 (초기 ?�면?�서�?보임)
		settingsButton.setBounds(450, 380, 400, 80);
		settingsButton.setVisible(true); // 초기 ?�면?�서 보임
		settingsButton.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		settingsButton.setBackground(new Color(120, 120, 120));
		settingsButton.setForeground(Color.WHITE);
		settingsButton.setBorderPainted(false);
		settingsButton.setContentAreaFilled(true);
		settingsButton.setFocusPainted(false);
		settingsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				settingsButton.setBackground(new Color(150, 150, 150));
				settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingsButton.setBackground(new Color(120, 120, 120));
				settingsButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
				enterSettings();
			}
		});
		add(settingsButton);

		// �?목록 ?�릭 ?�벤??추�?
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (isMainScreen) {
					handleTrackListClick(e.getX(), e.getY());
				} else if (isSettingsScreen) {
					handleSettingsClick(e.getX(), e.getY());
				}
			}
		});
	}

	/**
	 * �?목록 ?�릭 처리
	 */
	private void handleTrackListClick(int x, int y) {
		int listX = 450;
		int listY = 80;
		int listWidth = 750;
		int itemHeight = 80;

		// �?목록 ?�역 ?�릭 ?�인
		if (x >= listX && x <= listX + listWidth) {
			for (int i = 0; i < trackList.size(); i++) {
				int itemY = listY + (i * (itemHeight + 10));

				if (y >= itemY && y <= itemY + itemHeight) {
					// Easy 버튼 ?�역 ?�릭
					if (x >= listX + 20 && x <= listX + 140 && y >= itemY + 35 && y <= itemY + 60) {
						nowSelected = i;
						selectTrack(nowSelected);
						Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
						buttonMusic.start();
						gameStart(nowSelected, "Easy");
					}
					// Hard 버튼 ?�역 ?�릭
					else if (x >= listX + 150 && x <= listX + 270 && y >= itemY + 35 && y <= itemY + 60) {
						nowSelected = i;
						selectTrack(nowSelected);
						Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
						buttonMusic.start();
						gameStart(nowSelected, "Hard");
					}
					// �??�목 ?�역 ?�릭 (�??�택�?
					else {
						nowSelected = i;
						selectTrack(nowSelected);
						Music buttonMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);
						buttonMusic.start();
					}
					break;
				}
			}
		}
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics(); // 그래??객체 ?�어?�기
		screenDraw((Graphics2D) screenGraphic);// ?�로?�에 그림기리�?Graphics2 ?�로 ?��???		g.drawImage(screenImage, 0, 0, null);// 0,0???�치??그림??기려준??	}

	public void screenDraw(Graphics2D g) {// backgroud?��?지를screenImage?�는??		if (!isMainScreen && !isGameScreen && !isSettingsScreen) {
			// 초기 ?�면 (?�트�??�면)
			drawIntroScreen(g);
		} else {
			g.drawImage(background, 0, 0, null);// 0,0???�치??그림??기려준??			if (isMainScreen) {// true경우 출력
				drawTrackListScreen(g);
			}
			if (isGameScreen) {
				game.screenDraw(g);// 게임 ?�래??			}
			if (isSettingsScreen) {
				drawSettingsScreen(g);// ?�정 ?�면 그리�?			}
		}
		paintComponents(g);// background?�외?�JLabel�?추�??�주??부분을 보여준??
		// ?�면 ?�조??		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.repaint();// ?�로그램 반복?�면???�려준??	}

	/**
	 * ?�련???�트�??�면??그립?�다
	 */
	private void drawIntroScreen(Graphics2D g) {
		// ?�두???�주 배경
		g.setColor(new Color(10, 10, 20));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		// 별들 그리�?(반짝?�는 ?�과)
		drawStars(g);

		// ?�쪽 ?�레???�역
		drawCredits(g);

		// ?�른�??�아???�역
		drawPianoArea(g);

		// 중앙 ?�?��?
		drawMainTitle(g);
	}

	/**
	 * 별들??그립?�다
	 */
	private void drawStars(Graphics2D g) {
		// 고정??별들 ?�치 (매번 ?�르�?보이지 ?�도�?
		int[][] starPositions = {
				{ 100, 50 }, { 200, 80 }, { 300, 120 }, { 400, 60 }, { 500, 100 },
				{ 600, 40 }, { 700, 90 }, { 800, 70 }, { 900, 110 }, { 1000, 50 },
				{ 150, 200 }, { 250, 180 }, { 350, 220 }, { 450, 160 }, { 550, 200 },
				{ 650, 140 }, { 750, 190 }, { 850, 170 }, { 950, 210 }, { 1050, 150 },
				{ 80, 300 }, { 180, 280 }, { 280, 320 }, { 380, 260 }, { 480, 300 },
				{ 580, 240 }, { 680, 290 }, { 780, 270 }, { 880, 310 }, { 980, 250 }
		};

		for (int[] star : starPositions) {
			// 별의 밝기�??�간???�라 변??(반짝?�는 ?�과)
			int brightness = (int) (150 + 100 * Math.sin(System.currentTimeMillis() * 0.005 + star[0] * 0.01));
			brightness = Math.max(100, Math.min(255, brightness));

			g.setColor(new Color(255, 255, 255, brightness));
			g.fillOval(star[0], star[1], 3, 3);

			// ??별들?� ??밝게
			if ((star[0] + star[1]) % 7 == 0) {
				g.fillOval(star[0] - 1, star[1] - 1, 5, 5);
			}
		}
	}

	/**
	 * ?�쪽 ?�레???�보�?그립?�다
	 */
	private void drawCredits(Graphics2D g) {
		// ?�레???�목
		g.setColor(new Color(200, 180, 120));
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		g.drawString("??MUSIC PRODUCER", 80, 120);

		// ?�레??목록
		g.setColor(new Color(180, 180, 180));
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 16));

		String[] credits = {
				"Java Max Team",
				"Music Selection",
				"Sound Design",
				"Game Development",
				"UI/UX Design"
		};

		for (int i = 0; i < credits.length; i++) {
			g.drawString(credits[i], 80, 160 + (i * 30));
		}

		// 추�? ?�보
		g.setColor(new Color(150, 150, 150));
		g.setFont(getKoreanFont(java.awt.Font.ITALIC, 14));
		g.drawString("Rhythm Game Experience", 80, 320);
		g.drawString("Powered by Java", 80, 340);
	}

	/**
	 * ?�른�??�아???�역??그립?�다
	 */
	private void drawPianoArea(Graphics2D g) {
		// ?�아???�루??그리�?		g.setColor(new Color(40, 40, 60));
		g.fillRect(750, 300, 400, 200);
		g.fillRect(750, 250, 50, 50); // ?�아???�단

		// ?�아??건반
		g.setColor(Color.WHITE);
		for (int i = 0; i < 20; i++) {
			g.fillRect(750 + (i * 20), 450, 18, 50);
			g.setColor(Color.BLACK);
			g.drawRect(750 + (i * 20), 450, 18, 50);
			g.setColor(Color.WHITE);
		}

		// 검?� 건반
		g.setColor(Color.BLACK);
		for (int i = 0; i < 19; i++) {
			if (i % 7 != 2 && i % 7 != 6) { // 미�? ???�이?�는 검?� 건반???�음
				g.fillRect(760 + (i * 20), 450, 10, 30);
			}
		}

		// ?�주???�루??		g.setColor(new Color(80, 60, 100));
		// 머리
		g.fillOval(680, 200, 80, 80);
		// �?		g.fillRect(700, 280, 40, 120);
		// ??		g.fillRect(650, 300, 100, 20);
		g.fillRect(720, 320, 80, 20);

		// ?�?�잔
		g.setColor(new Color(150, 100, 50));
		g.fillRect(900, 280, 3, 30);
		g.fillOval(895, 270, 13, 15);
		g.setColor(new Color(120, 50, 50));
		g.fillOval(897, 272, 9, 8);
	}

	/**
	 * 메인 ?�?��???그립?�다
	 */
	private void drawMainTitle(Graphics2D g) {
		// 그라?�이???�과�??�한 ?�?��?
		g.setColor(new Color(255, 215, 0));
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 48));
		g.drawString("JAVA MAX", 450, 150);

		// ?�브?�?��?
		g.setColor(new Color(200, 200, 200));
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 20));
		g.drawString("리듬 게임???�로??경험", 500, 180);

		// ?�단 ?�명
		g.setColor(new Color(150, 150, 150));
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString("?�악�??�께?�는 ?�벽???�?�밍???�계�?, 480, 550);
	}

	/**
	 * �?목록 ?�면??그립?�다
	 */
	private void drawTrackListScreen(Graphics2D g) {
		// 배경???�정 (?�두??배경)
		g.setColor(new Color(0, 0, 0, 180));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		// ?�쪽 ?�역: ?�택??�??�보
		drawSelectedTrackInfo(g);

		// ?�른�??�역: �?목록 리스??		drawTrackList(g);
	}

	/**
	 * ?�쪽 ?�역???�택??�??�보�?그립?�다
	 */
	private void drawSelectedTrackInfo(Graphics2D g) {
		if (trackList.size() > nowSelected) {
			Track selectedTrack = trackList.get(nowSelected);

			// ?�범 ?�트 ?�역
			int albumX = 50;
			int albumY = 100;
			int albumWidth = 300;
			int albumHeight = 250;

			// ?�범 ?�트 배경
			g.setColor(new Color(50, 50, 50, 200));
			g.fillRoundRect(albumX, albumY, albumWidth, albumHeight, 15, 15);

			// ?�범 ?�트 ?��?지
			if (titleImage != null) {
				g.drawImage(titleImage, albumX + 10, albumY + 10, albumWidth - 20, albumHeight - 20, null);
			}

			// �??�목
			g.setColor(Color.WHITE);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
			g.drawString(selectedTrack.getTitleName(), albumX, albumY + albumHeight + 40);

			// 최고 ?�수 ?�보
			String trackName = selectedTrack.getTitleName();
			int easyScore = dbManager.getBestScore(trackName + " (Easy)");
			int hardScore = dbManager.getBestScore(trackName + " (Hard)");

			g.setFont(getKoreanFont(java.awt.Font.PLAIN, 16));
			g.setColor(new Color(100, 255, 100));
			g.drawString("Easy: " + easyScore + "??, albumX, albumY + albumHeight + 70);

			g.setColor(new Color(255, 100, 100));
			g.drawString("Hard: " + hardScore + "??, albumX, albumY + albumHeight + 95);
		}
	}

	/**
	 * ?�른�??�역??�?목록??그립?�다
	 */
	private void drawTrackList(Graphics2D g) {
		int listX = 450;
		int listY = 80;
		int listWidth = 750;
		int itemHeight = 80;

		// ?�목 ?�더
		g.setColor(new Color(70, 70, 70, 220));
		g.fillRoundRect(listX, listY - 40, listWidth, 35, 10, 10);
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 18));
		g.drawString("ALL TRACK", listX + 20, listY - 18);

		// �?곡을 리스?�로 ?�시
		for (int i = 0; i < trackList.size(); i++) {
			Track track = trackList.get(i);
			int itemY = listY + (i * (itemHeight + 10));

			// ?�택??�??�이?�이??			if (i == nowSelected) {
				g.setColor(new Color(100, 150, 255, 180));
			} else {
				g.setColor(new Color(50, 50, 50, 150));
			}
			g.fillRoundRect(listX, itemY, listWidth, itemHeight, 10, 10);

			// �??�목
			g.setColor(Color.WHITE);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 18));
			g.drawString(track.getTitleName(), listX + 20, itemY + 25);

			// 최고 ?�수 ?�시
			String trackName = track.getTitleName();
			int easyScore = dbManager.getBestScore(trackName + " (Easy)");
			int hardScore = dbManager.getBestScore(trackName + " (Hard)");

			// Easy ?�이??버튼
			g.setColor(new Color(100, 255, 100, 200));
			g.fillRoundRect(listX + 20, itemY + 35, 120, 25, 5, 5);
			g.setColor(Color.BLACK);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 12));
			g.drawString("Easy: " + easyScore, listX + 25, itemY + 50);

			// Hard ?�이??버튼
			g.setColor(new Color(255, 100, 100, 200));
			g.fillRoundRect(listX + 150, itemY + 35, 120, 25, 5, 5);
			g.setColor(Color.BLACK);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 12));
			g.drawString("Hard: " + hardScore, listX + 155, itemY + 50);

			// ?�레??가???�시
			g.setColor(new Color(200, 200, 200));
			g.setFont(getKoreanFont(java.awt.Font.PLAIN, 12));
			g.drawString("PLAYABLE", listX + listWidth - 80, itemY + 25);

			// 별점?�나 ?�벨 ?�시
			g.setColor(Color.YELLOW);
			for (int star = 0; star < 3; star++) {
				g.fillOval(listX + listWidth - 120 + (star * 15), itemY + 35, 10, 10);
			}

			// ?�버 ?�과�??�한 경계??			if (i == nowSelected) {
				g.setColor(new Color(255, 255, 255, 100));
				g.drawRoundRect(listX, itemY, listWidth, itemHeight, 10, 10);
			}
		}
	}

	public void selectTrack(int nowSelected) {// ?�재 ?�택??곡의 번호 ?�어???�택??것을 ?�려준??		if (selectedMusic != null)// selectedMusic가 ?�다�?종료
			selectedMusic.close();// ?�떤 곡이 ?�행 ?�고 ?�다�?종료
		// ?�재 ?�택??곡의 ?�?��???가?��????�어준??
		// ?�악 ?��?지
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);// ?�택??�??�이?�트 부�?무안 ?�생
		selectedMusic.start();// ?�재 ?�택??곡의 ?�?��???가?��????�어준??
	}

	// ?�래 ?�택 ?�존버튼 ?�른�?버튼 ?�렸?�때 ?�벤??	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;// 0�?�?곡을 ?�는 가???�른�?곡으�?간다
		else
			nowSelected--;// ?�닐경우 1??뺸다
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)// 마�?막곡?�라�?0번�? 곡을 ?�는 간다
			nowSelected = 0;
		else
			nowSelected++;// ?�닐경우 1?????�다.
		selectTrack(nowSelected);
	}

	public void backMain() {
		// 게임 종료 ???�수 ?�??		if (game != null && !game.isGameEnded()) {
			game.saveScore();
		}

		// ?�정 ?�면?�서 ?�아?�는 경우
		if (isSettingsScreen) {
			isSettingsScreen = false;
			// ?�정?�서 ?�아???�는 ?�작 ?�면?�로 ?�아가�?			isMainScreen = false;

			// ?�작 ?�면 버튼???�시 보이�?			startButton.setVisible(true);
			quitButton.setVisible(true);
			settingsButton.setVisible(true);

			// ?�로가�?버튼 ?�기�?			backButton.setVisible(false);

			// 배경 변�?			background = new ImageIcon(Main.class.getResource("../images/backgrounds/introBackground.jpg")).getImage();

			// ?�트�??�악 ?�시??			introMusic = new Music("background/introMusic.mp3", true);
			introMusic.start();
			return;
		}

		isMainScreen = true;// 메인?�로 ?�아??
		// 버튼?�이 보이?�록 ?�정 (?�로??UI??맞게 조정)
		leftButton.setVisible(false); // ?�로??UI?�서???�요 ?�음
		rightButton.setVisible(false); // ?�로??UI?�서???�요 ?�음
		easyButton.setVisible(false); // ?�로??UI?�서???�요 ?�음
		hardButton.setVisible(false); // ?�로??UI?�서???�요 ?�음
		scoreButton.setVisible(true);
		settingsButton.setVisible(false); // 메인 ?�면?�서???�정 버튼 ?��?

		background = new ImageIcon(Main.class.getResource("../images/backgrounds/mainBackground.jpg"))
				.getImage();
		backButton.setVisible(false);// ?�로가�??�기�?		selectTrack(nowSelected);// ?�이?�이??부�??�생
		isGameScreen = false;
		if (game != null) {
			game.close();// �???종료 ?�으�????�른 곡을 ?�택 ?�수 ?�도�??�기
		}
	}

	public void gameStart(int nowSelected, String difficulty) {// 모드???�보�?받는??		if (selectedMusic != null)// ?�악???�행중이?�면
			selectedMusic.close();// ?�악 종료

		isMainScreen = false;// 메인 ?�면???�니?�고 ?�정

		// 메인 ?�면 버튼???��?
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		scoreButton.setVisible(false);
		settingsButton.setVisible(false); // 게임 중에???�정 버튼 ?��?

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();// ?�재 ?�택????게임???��?지�?불러?�다
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());// ?�재?�태??곡을
		game.start();// ?�함???�행 (?�트 ?�성)
		setFocusable(true);// ?�보???�커?��? 맞춤
	}

	public void enterMain() {
		// Music selectedMusic = new Music("Wellerman.mp3",true);//?�어 ?�떄 ?�택??�??�생(?�이?�이??		// 곡재??30�?
		// selectedMusic.start();//?�작
		// 게임?�작 ?�벤??메인 ?�면?�로 ?�동)
		startButton.setVisible(false);// ?�작버튼 ??보이?�록 ?�기
		quitButton.setVisible(false);// 종료버튼 ??보이?�록 ?�기
		settingsButton.setVisible(false);// ?�정 버튼 ?�기�?
		background = new ImageIcon(Main.class.getResource("../images/backgrounds/mainBackground.jpg")).getImage();// 게임
																													// ?�작
																													// 배경

		isMainScreen = true;// ?�택�??��?지�?변�?
		// ?�악 ?�택 버튼 ?�기�?(?�로??UI?�서???�요 ?�음)
		leftButton.setVisible(false);// ?�악?�택 ?�쪽 버튼 ?�기�?		rightButton.setVisible(false);// ?�악?�택 ?�른�?버튼 ?�기�?
		// 모드 버튼 ?�기�?(?�로??UI?�서??리스?�에??직접 ?�택)
		easyButton.setVisible(false);// ?��? 모드 ?�기�?		hardButton.setVisible(false);// ?�드 모드 ?�기�?		scoreButton.setVisible(true);// ?�수보드 버튼

		// ?�트�??�악 ?��?
		if (introMusic != null) {
			introMusic.close();
		}

		selectTrack(0);// 0번째 곡을 ?�택?�라
	}

	/**
	 * ?��???지?�하???�트�?반환?�니??
	 */
	private java.awt.Font getKoreanFont(int style, int size) {
		// ?��???지?�하???�트???�서?��??�도
		String[] koreanFonts = {
				"맑�? 고딕", // Windows 기본 ?��? ?�트
				"굴림", // Windows 구버???��? ?�트
				"Apple SD Gothic Neo", // macOS ?��? ?�트
				"Noto Sans CJK KR", // Linux ?��? ?�트
				"Dialog" // ?�스??기본 ?�트 (fallback)
		};

		for (String fontName : koreanFonts) {
			try {
				java.awt.Font font = new java.awt.Font(fontName, style, size);
				// ?�트가 ?��???지?�하?��? ?�인
				if (font.canDisplay('??)) {
					return font;
				}
			} catch (Exception e) {
				// ?�트 ?�성 ?�패 ???�음 ?�트 ?�도
				continue;
			}
		}

		// 모든 ?�트가 ?�패??경우 기본 Dialog ?�트 반환
		return new java.awt.Font(java.awt.Font.DIALOG, style, size);
	}

	/**
	 * ?�재 ?�정 ?�면?��? ?�인?�니??	 */
	public boolean isSettingsScreen() {
		return isSettingsScreen;
	}

	/**
	 * ?�정값을 변경합?�다
	 */
	public void changeSettings(String type, Object value) {
		if (settings == null) {
			settings = Settings.getInstance();
		}

		switch (type) {
			case "volume":
				settings.setVolume((Float) value);
				break;
			case "keyCount":
				settings.setKeyCount((Integer) value);
				break;
			case "difficulty":
				settings.setDifficulty((String) value);
				break;
		}
	}

	/**
	 * ?�정 ?�면?�로 ?�동?�니??	 */
	public void enterSettings() {
		// ?�재 ?�생 중인 모든 ?�악 ?��?
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		if (introMusic != null) {
			introMusic.close();
		}

		isMainScreen = false;
		isSettingsScreen = true;

		// 메인 ?�면 버튼???�기�?		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		scoreButton.setVisible(false);
		settingsButton.setVisible(false);

		// ?�작 ?�면 버튼???�기�?		startButton.setVisible(false);
		quitButton.setVisible(false);

		// ?�로가�?버튼 보이�?		backButton.setVisible(true);

		// ?�정 ?�면 배경 변�?		background = new ImageIcon(Main.class.getResource("../images/backgrounds/introBackground.jpg")).getImage();

		// ?�정 객체 초기??		if (settings == null) {
			settings = Settings.getInstance();
		}
	}

	/**
	 * ?�정 ?�면 ?�릭 처리
	 */
	private void handleSettingsClick(int x, int y) {
		// GAME SETTINGS ?�션 ?�릭
		if (y >= 130 && y <= 160) { // 게임 ?�어 ??			if (x >= 650 && x <= 750) {
				gameLanguage = gameLanguage.equals("KOREAN") ? "ENGLISH" : "KOREAN";
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 160 && y <= 190) { // FREESTYLE ?�정 ??			if (x >= 650 && x <= 750) {
				freestyleMode = !freestyleMode;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 190 && y <= 220) { // 리듬�?방향?�정 ??			// ?�쪽 ?�살??			if (x >= 620 && x <= 640) {
				rhythmDirection = false;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
			// ?�른�??�살??			else if (x >= 730 && x <= 750) {
				rhythmDirection = true;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 220 && y <= 250) { // ?�그 ??			if (x >= 650 && x <= 750) {
				String[] tags = { "PERFECT", "GREAT", "GOOD" };
				for (int i = 0; i < tags.length; i++) {
					if (rhythmTag.equals(tags[i])) {
						rhythmTag = tags[(i + 1) % tags.length];
						break;
					}
				}
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		}

		// SOUND SETTINGS ?�션 ?�릭
		else if (y >= 310 && y <= 340) { // 마스???�량 ?�라?�더
			if (x >= 200 && x <= 500) {
				masterVolume = (int) ((x - 200) / 3.0);
				masterVolume = Math.max(0, Math.min(100, masterVolume));
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 340 && y <= 370) { // 명령???�량 ?�라?�더
			if (x >= 200 && x <= 500) {
				commandVolume = (int) ((x - 200) / 3.0);
				commandVolume = Math.max(0, Math.min(100, commandVolume));
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 370 && y <= 400) { // 배경???�량 ?�라?�더
			if (x >= 200 && x <= 500) {
				backgroundVolume = (int) ((x - 200) / 3.0);
				backgroundVolume = Math.max(0, Math.min(100, backgroundVolume));
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		}

		// DISPLAY SETTINGS ?�션 ?�릭
		else if (y >= 480 && y <= 510) { // ?�상???�정
			if (x >= 650 && x <= 750) {
				resolution = resolution.equals("PLAYABLE") ? "FULLSCREEN" : "PLAYABLE";
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 510 && y <= 540) { // 모드
			if (x >= 650 && x <= 750) {
				displayMode = !displayMode;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 540 && y <= 570) { // ?�동?�생 ?�정
			if (x >= 650 && x <= 750) {
				autoPlay = autoPlay.equals("TYPE A") ? "TYPE B" : "TYPE A";
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		}
	}

	/**
	 * ?�정 ?�면??그립?�다
	 */
	private void drawSettingsScreen(Graphics2D g) {
		// ?�두??배경
		g.setColor(new Color(20, 20, 30));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		// GAME SETTINGS ?�션
		drawSettingSection(g, "GAME SETTINGS", 50, 100);
		drawSettingRow(g, "게임 ?�어", gameLanguage, 50, 130, true);
		drawSettingRow(g, "FREESTYLE 무선 ?�정", freestyleMode ? "ON" : "OFF", 50, 160, true);
		drawSettingRowWithArrows(g, "리듬�?방향?�정", rhythmDirection ? "ON" : "OFF", 50, 190);
		drawSettingRow(g, "?�그", rhythmTag, 50, 220, true);

		// SOUND SETTINGS ?�션
		drawSettingSection(g, "SOUND SETTINGS", 50, 280);
		drawSliderRow(g, "마스???�량", masterVolume, 50, 310);
		drawSliderRow(g, "명령???�량", commandVolume, 50, 340);
		drawSliderRow(g, "배경???�량", backgroundVolume, 50, 370);
		drawSettingRow(g, "?�과???�정", soundEffect, 50, 400, false);

		// DISPLAY SETTINGS ?�션
		drawSettingSection(g, "DISPLAY SETTINGS", 50, 450);
		drawSettingRow(g, "?�상???�정", resolution, 50, 480, true);
		drawSettingRow(g, "모드", displayMode ? "ON" : "OFF", 50, 510, true);
		drawSettingRow(g, "?�동?�생 ?�정", autoPlay, 50, 540, true);
		drawSettingRow(g, "카메??, camera, 50, 570, false);

		// ?�단 버튼??		g.setColor(new Color(100, 100, 100));
		g.fillRect(50, 620, 120, 40);
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 16));
		g.drawString("?�로 가�?, 70, 645);

		g.setColor(new Color(150, 150, 150));
		g.fillRect(200, 620, 60, 40);
		g.setColor(Color.BLACK);
		g.drawString("L2", 220, 645);
	}

	/**
	 * ?�정 ?�션 ?�목??그립?�다
	 */
	private void drawSettingSection(Graphics2D g, String title, int x, int y) {
		g.setColor(new Color(200, 180, 100));
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 18));
		g.drawString(title, x, y);

		// 구분??		g.setColor(new Color(100, 100, 100));
		g.drawLine(x, y + 5, x + 700, y + 5);
	}

	/**
	 * ?�정 ?�을 그립?�다
	 */
	private void drawSettingRow(Graphics2D g, String label, String value, int x, int y, boolean clickable) {
		// 배경
		g.setColor(new Color(60, 60, 70));
		g.fillRect(x, y, 700, 25);

		// ?�벨
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString(label, x + 10, y + 18);

		// �?배경
		if (clickable) {
			g.setColor(new Color(120, 100, 80));
		} else {
			g.setColor(new Color(80, 80, 80));
		}
		g.fillRect(x + 600, y + 2, 95, 21);

		// �?		g.setColor(Color.WHITE);
		g.drawString(value, x + 610, y + 18);
	}

	/**
	 * ?�살?��? ?�는 ?�정 ?�을 그립?�다
	 */
	private void drawSettingRowWithArrows(Graphics2D g, String label, String value, int x, int y) {
		// 배경
		g.setColor(new Color(60, 60, 70));
		g.fillRect(x, y, 700, 25);

		// ?�벨
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString(label, x + 10, y + 18);

		// ?�쪽 ?�살??		g.setColor(new Color(150, 150, 150));
		g.fillRect(x + 570, y + 5, 20, 15);
		g.setColor(Color.BLACK);
		g.drawString("?�", x + 575, y + 16);

		// �?배경
		g.setColor(new Color(120, 100, 80));
		g.fillRect(x + 600, y + 2, 95, 21);
		g.setColor(Color.WHITE);
		g.drawString(value, x + 630, y + 18);

		// ?�른�??�살??		g.setColor(new Color(150, 150, 150));
		g.fillRect(x + 705, y + 5, 20, 15);
		g.setColor(Color.BLACK);
		g.drawString("??, x + 710, y + 16);
	}

	/**
	 * ?�라?�더 ?�을 그립?�다
	 */
	private void drawSliderRow(Graphics2D g, String label, int value, int x, int y) {
		// 배경
		g.setColor(new Color(60, 60, 70));
		g.fillRect(x, y, 700, 25);

		// ?�벨
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString(label, x + 10, y + 18);

		// ?�라?�더 배경
		g.setColor(new Color(40, 40, 40));
		g.fillRect(x + 200, y + 8, 300, 10);

		// ?�라?�더 �?		g.setColor(new Color(100, 150, 255));
		g.fillRect(x + 200, y + 8, (int) (300 * value / 100.0), 10);

		// ?�센???�시
		g.setColor(Color.WHITE);
		g.drawString(value + "%", x + 520, y + 18);

		// ?�라?�더 ?�들
		int handleX = x + 200 + (int) (300 * value / 100.0) - 5;
		g.setColor(Color.WHITE);
		g.fillOval(handleX, y + 6, 10, 14);
	}
}
