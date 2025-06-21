/******************************************************************************
	?ë°”?„ë¡œ?œíŠ¸ JavaMax

	update : 2024-02-20
	
	ê´€ë¦¬ì : PNJ
	
  ?ë°”?„ë¡œ?œíŠ¸ ë§¤ë‰´??
  - 
  
  ?ë°”?„ë¡œ?œíŠ¸ Music ë§¤ë‰´??:
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

	// ë²„í¼?´ë?ì§€ ?¤ì •
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/exitButtonEntered.png"));// ?˜ê¸°ê¸?	// ?´ë?ì§€
	private ImageIcon exitButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/exitButtonBasic.png"));// ?˜ê¸°ê¸?	// ?´ë?ì§€
	// ëª…ë„50
	// ?´ë?ì§€ ë²„íŠ¼?¤ì? ?ìŠ¤??ë²„íŠ¼?¼ë¡œ ë³€ê²½ë˜??ì£¼ì„ ì²˜ë¦¬
	// private ImageIcon startButtonEnteredImage = new
	// ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	// private ImageIcon startButtonBasicImage = new
	// ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	// private ImageIcon quitButtonEnteredImage = new
	// ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	// private ImageIcon quitButtonBasicImage = new
	// ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/leftButtonEntered.png"));// ?¸ë˜
	// <=
	// ? íƒë²„íŠ¼
	private ImageIcon leftButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/leftButtonBasic.png"));// ?¸ë˜
	// <=
	// ? íƒë²„íŠ¼
	// 50
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/rightButtonEntered.png"));// ?¸ë˜ => ? íƒë²„íŠ¼
	private ImageIcon rightButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/rightButtonBasic.png"));// ?¸ë˜
	// =>
	// ? íƒë²„íŠ¼
	// 50
	private ImageIcon easyButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/easyButtonEntered.png"));// ?´ì?
	// ë²„íŠ¼
	private ImageIcon easyButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/easyButtonBasic.png"));// ?´ì?
	// ë²„íŠ¼
	private ImageIcon hardButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/hardButtonEntered.png"));// ?˜ë“œ
	// ë²„íŠ¼
	private ImageIcon hardButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/hardButtonBasic.png"));// ?˜ë“œ
	// ë²„íŠ¼
	// 50
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/backButtonEntered.png"));// ?¤ë¡œê°€ê¸?	// ë²„íŠ¼
	private ImageIcon backButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/ui/buttons/backButtonBasic.png"));// ?¤ë¡œê°€ê¸°ë²„??	// 50
	// private Image titleImage = new
	// ImageIcon(Main.class.getResource("../images/Chilituna.png")).getImage();
	// private Image selectedImage = new
	// ImageIcon(Main.class.getResource("../images/Mighty Love Title
	// Image.png")).getImage();

	private Image background = new ImageIcon(Main.class.getResource("../images/backgrounds/introBackground.jpg"))
			.getImage();// ë°°ê²½

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/ui/game/menuBar.png")));// ë©”ë‰´ë°?																												// ?´ë?ì§€
	private JButton exitButton = new JButton(exitButtonBasicImage);// ?„ë¡œê·¸ë¨ ì¢…ë£Œ ë²„íŠ¼
	private JButton startButton = new JButton("?œì‘?˜ê¸°");// ?œì‘ ë²„íŠ¼
	private JButton quitButton = new JButton("ì¢…ë£Œ?˜ê¸°");// ì¢…ë£Œ ë²„íŠ¼
	private JButton leftButton = new JButton(leftButtonBasicImage);// ?¼ì?ë²„íŠ¼
	private JButton rightButton = new JButton(rightButtonBasicImage);// ?¤ë¥¸ìª½ë²„??	private JButton easyButton = new JButton(easyButtonBasicImage);// ?´ì? ë²„íŠ¼
	private JButton hardButton = new JButton(hardButtonBasicImage);// ?˜ë“œ ë²„íŠ¼
	private JButton backButton = new JButton(backButtonBasicImage);// ?¤ë¡œê°€ê¸?ë²„íŠ¼
	private JButton scoreButton = new JButton("?ìˆ˜ë³´ë“œ");// ?ìˆ˜ë³´ë“œ ë²„íŠ¼
	private JButton settingsButton = new JButton("?¤ì •");// ?¤ì • ë²„íŠ¼

	private int mouseX, mouseY;

	private boolean isMainScreen = false;// ë©”ì¸ ?¤í¬ë¦°ìœ¼ë¡??˜ì–´ ?”ëŠ”ì§€ ?•ì¸?œëŠ ë³€??	private boolean isGameScreen = false;// ê²Œì„ ?¤í¬ë¦°ìœ¼ë¡??˜ì–´ ?”ëŠ”ì§€ ?•ì¸?˜ëŠ” ë³€??	private boolean isSettingsScreen = false;// ?¤ì • ?¤í¬ë¦°ìœ¼ë¡??˜ì–´ ?”ëŠ”ì§€ ?•ì¸?˜ëŠ” ë³€??
	ArrayList<Track> trackList = new ArrayList<Track>();// ?´ë–¤ ë³€?˜ë? ?´ì„ ???ˆëŠ” ë°°ì—´
	private DatabaseManager dbManager;// ?°ì´?°ë² ?´ìŠ¤ ë§¤ë‹ˆ?€

	private Image titleImage;// ?„ì¬ ? íƒ??ê³¡ì˜ ?´ë?ì§€
	private Image selectedImage;// ?„ì¬ ? íƒ??ê³¡ì˜ ?´ë¦„ ?´ë?ì§€
	private Music selectedMusic;// ?„ì¬ ? íƒ??ê³?	private Music introMusic = new Music("background/introMusic.mp3", true);// ë°˜ë³µ?¤í–‰
	private int nowSelected = 0;// ?„ì¬ ? íƒ??ê³?ë²ˆí˜¸

	public static Game game;// gameê°ì²´ ?ì„±
	private Settings settings;// ?¤ì • ê°ì²´

	// ?¤ì • ?”ë©´ ê´€??ë³€?˜ë“¤
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
		// ?°ì´?°ë² ?´ìŠ¤ ë§¤ë‹ˆ?€ ì´ˆê¸°??		dbManager = DatabaseManager.getInstance();

		// Track?¼ë¡œ ë³€??ì´ˆê¸°???œì„œ?ìœ¼ë¡?ê°’ì´ ?¤ì–´ê°„ë‹¤ (?œëª©,ê²Œì„ ? íƒ ì°½í‘œì§€,?¤í–‰?ˆì„???œì?, ?˜ì´?¼ì´?? ?´ë‹¹ê³?ê³¡ì œëª? ?¬ê¸°??DBë¡?		// ?…ì´?????ˆì •
		trackList.add(new Track("album_covers/Goodbye_mr_my.png", "album_covers/hellomo.png",
				"backgrounds/introBackground.jpg", "game_music/hellomy.mp3", "game_music/hellomy.mp3",
				"Goodbye Mr. My ë¨¸ë¦¬ì¹´ë½"));
		trackList.add(new Track("album_covers/Nathan_Evans_Wellerman.png", "album_covers/Energy Start Image.png",
				"backgrounds/introBackground.jpg", "game_music/Wellerman.mp3", "game_music/Wellerman.mp3",
				"Nathan Evans Wellerman"));
		trackList.add(new Track("album_covers/Super_Shy_NewJeans.png", "album_covers/Super Shy.gif",
				"backgrounds/introBackground.jpg", "game_music/Super Shy.mp3", "game_music/Super Shy.mp3",
				"Super Shy ?´ì§„??));

		setUndecorated(true);// ë©”ë‰´ ë°”ê? ë³´ì´ì§€ ?ŠëŠ”??		setTitle("JavaMax");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);// ?¬ìš©?ê? ?¬ê¸°ì¡°ì ˆ ê¸ˆì?
		setLocationRelativeTo(null);// ì»´í“¨???•ì¤‘???•ë ¬
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ?„ë¡œê·¸ë¨ ì¢…ë£Œ
		setVisible(true);// ê²Œì„ì°?ë³´ì—¬ì£¼ê¸°
		setBackground(new Color(0, 0, 0, 0));// ?”ì´???‰ìœ¼ë¡?		setLayout(null);// JLabel?•í™•???„ì¹˜ë¡?
		addKeyListener(new javamax.KeyListener(this));// ?¤ì…???´ë²¤??ì²˜ë¦¬

		introMusic.start();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);

		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				exitButton.setIcon(exitButtonEnteredImage);// ì¢…ë£Œ ë²„íŠ¼ ë°”ê¾¸ê¸?				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				exitButton.setIcon(exitButtonBasicImage);// ì¢…ë£Œë²„íŠ¼ ë°”ê¾¸ê¸?				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?”ëª°??ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);// 1ì´??€ê¸?				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);// ?„ë¡œê·¸ë¨ ì¢…ë£Œ ë²„íŠ¼ ?ì„±

		// ?œì‘ ë²„íŠ¼
		startButton.setBounds(450, 200, 400, 80);
		startButton.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		startButton.setBackground(new Color(100, 150, 250));
		startButton.setForeground(Color.WHITE);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(true);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				startButton.setBackground(new Color(120, 170, 255));// ë²„íŠ¼ ?‰ìƒ ë³€ê²?				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				startButton.setBackground(new Color(100, 150, 250));// ?ë˜ ?‰ìƒ?¼ë¡œ ?˜ëŒë¦¬ê¸°
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?”ëª°??ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				enterMain();// ë©”ì¸?¨ìˆ˜ ?¤ì–´ê°€??
			}
		});
		add(startButton);// ê²Œì„ ?œì‘ ë²„íŠ¼ ?ì„±

		// ì¢…ë£Œë²„íŠ¼
		quitButton.setBounds(450, 290, 400, 80);
		quitButton.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		quitButton.setBackground(new Color(200, 100, 100));
		quitButton.setForeground(Color.WHITE);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(true);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				quitButton.setBackground(new Color(220, 120, 120));// ë²„íŠ¼ ?‰ìƒ ë³€ê²?				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				quitButton.setBackground(new Color(200, 100, 100));// ?ë˜ ?‰ìƒ?¼ë¡œ ?˜ëŒë¦¬ê¸°
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?”ëª°??ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);// 1ì´??€ê¸?				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);// ì¢…ë£Œ ë²„íŠ¼

		// ?Œì•… ? íƒ ?¼ìª½ ë²„íŠ¼
		leftButton.setVisible(false);// ë³´ì´?„ë¡ ?˜ê¸°
		leftButton.setBounds(140, 310, 60, 60);// ?„ì¹˜
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				leftButton.setIcon(leftButtonEnteredImage);// ?Œì•…? íƒ ?¼ìª½ ë°”ê¾¸ê¸?				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				leftButton.setIcon(leftButtonBasicImage);// ?Œì•…? íƒ ?¼ìª½ ë²„íŠ¼ ë°”ê¾¸ê¸?				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?”ëª°??ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		add(leftButton);// ?Œì•…? íƒ ?¼ìª½ ë²„íŠ¼

		// ?Œí•™ ? íƒ ?¼ìª½ ë²„íŠ¼
		rightButton.setVisible(false);// ë³´ì´?„ë¡ ?˜ê¸°
		rightButton.setBounds(1080, 310, 60, 60);// ?„ì¹˜
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				rightButton.setIcon(rightButtonEnteredImage);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë°”ê¾¸ê¸?				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				rightButton.setIcon(rightButtonBasicImage);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë²„íŠ¼ ë°”ê¾¸ê¸?				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?”ëª°??ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				selectRight();
			}
		});
		add(rightButton);// ?Œì•…? íƒ ?¼ìª½ ë²„íŠ¼

		// ?´ì?
		easyButton.setVisible(false);// ë³´ì´?„ë¡ ?˜ê¸°
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë²„íŠ¼ ë°”ê¾¸ê¸?				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				easyButton.setIcon(easyButtonBasicImage);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë°”ê¾¸ê¸?				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ?¼ì„ ?´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);/// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);// ?´ì? ë²„íŠ¼

		// ?˜ë“œ
		hardButton.setVisible(false);// ë³´ì´?„ë¡ ?˜ê¸°
		hardButton.setBounds(655, 580, 250, 67);// ?„ì¹˜
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				hardButton.setIcon(hardButtonEnteredImage);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë°”ê¾¸ê¸?				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				hardButton.setIcon(hardButtonBasicImage);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë²„íŠ¼ ë°”ê¾¸ê¸?				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);/// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);// ?˜ë“œ ë²„íŠ¼

		// ?¤ë¡œê°€ê¸?ë²„íŠ¼
		backButton.setVisible(false);// ë³´ì´?„ë¡ ?˜ê¸°
		backButton.setBounds(20, 50, 60, 60);// ?„ì¹˜
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ë§ˆìš°?¤ë? ?¬ë¼ê°ˆë–„ ? ë–„
				backButton.setIcon(backButtonEnteredImage);// ?¤ë¡œê°€ê¸?				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?ê???ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??				Music buttonEnteredMusic = new Music("sound_effects/buttonEnteredMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {// ë§ˆìš°?¤ê? ?˜ì™”?„ë•Œ
				backButton.setIcon(backButtonBasicImage);// ?¤ë¡œê°€ê¸?ë²„íŠ¼ ë°”ê¾¸ê¸?				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));// ë§ˆìš°???¬ë¼ê°ˆë•Œ ?”ëª°??ëª¨ì–‘?¼ë¡œ ë°”ê¾¼??			}

			@Override
			// ë©”ì¸ ?”ë©´?¼ë¡œ ?Œì•„ê°€???´ë²¤??			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???´ë¦­? ë•Œ
				Music buttonEnteredMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);// ë²„íŠ¼ ?´ë¦­??				buttonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);// ?¤ë¡œê°€ê¸?ë²„íŠ¼

		menuBar.setBounds(0, 0, 1280, 30);// ?„ì¹˜?€ ?¬ê¸°
		menuBar.addMouseListener(new MouseAdapter() {// ë§ˆìš°???µëª… ê¸€?˜ìŠ¤
			@Override
			public void mousePressed(MouseEvent e) {// ë²„íŠ¼???Œë ¸?„ë–„
				mouseX = e.getX();// ë§ˆìš°?¤x?„ì¹˜
				mouseY = e.getY();// ë§ˆìš°?¤Y?„ì¹˜
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {// ?œë˜ê·?? ë–„ ?´ë²¤??ë°œìƒ
				int x = e.getXOnScreen();// ?¤í¬ë¦?xì¢Œí‘œ ê°€?¸ì˜¤ê¸?				int y = e.getYOnScreen();// ?¤í¬ë¦?yì£„í‘œ ê°€?¸ì˜¤ê¸?				setLocation(x - mouseX, y - mouseY);// ?„ì¹˜ë¥?ë°”ê¾¼??			}
		});
		add(menuBar);// ë©”ë‰´ë°”ì¶”ê°€

		// ?ìˆ˜ë³´ë“œ ë²„íŠ¼ ?¤ì •
		scoreButton.setBounds(50, 650, 120, 40);
		scoreButton.setVisible(false); // ì´ˆê¸°?ëŠ” ?¨ê?
		scoreButton.setFont(getKoreanFont(java.awt.Font.BOLD, 14));
		scoreButton.setBackground(Color.DARK_GRAY);
		scoreButton.setForeground(Color.WHITE);
		scoreButton.setBorderPainted(true);
		scoreButton.setFocusPainted(false);
		scoreButton.addActionListener(e -> {
			// ?ìˆ˜ë³´ë“œ ì°??´ê¸°
			ScoreBoard scoreBoard = new ScoreBoard();
			scoreBoard.showScoreBoard();
		});
		add(scoreButton);

		// ?¤ì • ë²„íŠ¼ ?¤ì • (ì´ˆê¸° ?”ë©´?ì„œë§?ë³´ì„)
		settingsButton.setBounds(450, 380, 400, 80);
		settingsButton.setVisible(true); // ì´ˆê¸° ?”ë©´?ì„œ ë³´ì„
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

		// ê³?ëª©ë¡ ?´ë¦­ ?´ë²¤??ì¶”ê?
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
	 * ê³?ëª©ë¡ ?´ë¦­ ì²˜ë¦¬
	 */
	private void handleTrackListClick(int x, int y) {
		int listX = 450;
		int listY = 80;
		int listWidth = 750;
		int itemHeight = 80;

		// ê³?ëª©ë¡ ?ì—­ ?´ë¦­ ?•ì¸
		if (x >= listX && x <= listX + listWidth) {
			for (int i = 0; i < trackList.size(); i++) {
				int itemY = listY + (i * (itemHeight + 10));

				if (y >= itemY && y <= itemY + itemHeight) {
					// Easy ë²„íŠ¼ ?ì—­ ?´ë¦­
					if (x >= listX + 20 && x <= listX + 140 && y >= itemY + 35 && y <= itemY + 60) {
						nowSelected = i;
						selectTrack(nowSelected);
						Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
						buttonMusic.start();
						gameStart(nowSelected, "Easy");
					}
					// Hard ë²„íŠ¼ ?ì—­ ?´ë¦­
					else if (x >= listX + 150 && x <= listX + 270 && y >= itemY + 35 && y <= itemY + 60) {
						nowSelected = i;
						selectTrack(nowSelected);
						Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
						buttonMusic.start();
						gameStart(nowSelected, "Hard");
					}
					// ê³??œëª© ?ì—­ ?´ë¦­ (ê³?? íƒë§?
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
		screenGraphic = screenImage.getGraphics(); // ê·¸ë˜??ê°ì²´ ?»ì–´?¤ê¸°
		screenDraw((Graphics2D) screenGraphic);// ?œë¡œ?°ì— ê·¸ë¦¼ê¸°ë¦¬ê¸?Graphics2 ?¼ë¡œ ?•ë???		g.drawImage(screenImage, 0, 0, null);// 0,0???„ì¹˜??ê·¸ë¦¼??ê¸°ë ¤ì¤€??	}

	public void screenDraw(Graphics2D g) {// backgroud?´ë?ì§€ë¥¼screenImage?£ëŠ”??		if (!isMainScreen && !isGameScreen && !isSettingsScreen) {
			// ì´ˆê¸° ?”ë©´ (?¸íŠ¸ë¡??”ë©´)
			drawIntroScreen(g);
		} else {
			g.drawImage(background, 0, 0, null);// 0,0???„ì¹˜??ê·¸ë¦¼??ê¸°ë ¤ì¤€??			if (isMainScreen) {// trueê²½ìš° ì¶œë ¥
				drawTrackListScreen(g);
			}
			if (isGameScreen) {
				game.screenDraw(g);// ê²Œì„ ?´ë˜??			}
			if (isSettingsScreen) {
				drawSettingsScreen(g);// ?¤ì • ?”ë©´ ê·¸ë¦¬ê¸?			}
		}
		paintComponents(g);// background?´ì™¸?JLabelë¥?ì¶”ê??´ì£¼??ë¶€ë¶„ì„ ë³´ì—¬ì¤€??
		// ?”ë©´ ?¬ì¡°??		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.repaint();// ?„ë¡œê·¸ë¨ ë°˜ë³µ?˜ë©´???Œë ¤ì¤€??	}

	/**
	 * ?¸ë ¨???¸íŠ¸ë¡??”ë©´??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawIntroScreen(Graphics2D g) {
		// ?´ë‘???°ì£¼ ë°°ê²½
		g.setColor(new Color(10, 10, 20));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		// ë³„ë“¤ ê·¸ë¦¬ê¸?(ë°˜ì§?´ëŠ” ?¨ê³¼)
		drawStars(g);

		// ?¼ìª½ ?¬ë ˆ???ì—­
		drawCredits(g);

		// ?¤ë¥¸ìª??¼ì•„???ì—­
		drawPianoArea(g);

		// ì¤‘ì•™ ?€?´í?
		drawMainTitle(g);
	}

	/**
	 * ë³„ë“¤??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawStars(Graphics2D g) {
		// ê³ ì •??ë³„ë“¤ ?„ì¹˜ (ë§¤ë²ˆ ?¤ë¥´ê²?ë³´ì´ì§€ ?Šë„ë¡?
		int[][] starPositions = {
				{ 100, 50 }, { 200, 80 }, { 300, 120 }, { 400, 60 }, { 500, 100 },
				{ 600, 40 }, { 700, 90 }, { 800, 70 }, { 900, 110 }, { 1000, 50 },
				{ 150, 200 }, { 250, 180 }, { 350, 220 }, { 450, 160 }, { 550, 200 },
				{ 650, 140 }, { 750, 190 }, { 850, 170 }, { 950, 210 }, { 1050, 150 },
				{ 80, 300 }, { 180, 280 }, { 280, 320 }, { 380, 260 }, { 480, 300 },
				{ 580, 240 }, { 680, 290 }, { 780, 270 }, { 880, 310 }, { 980, 250 }
		};

		for (int[] star : starPositions) {
			// ë³„ì˜ ë°ê¸°ë¥??œê°„???°ë¼ ë³€??(ë°˜ì§?´ëŠ” ?¨ê³¼)
			int brightness = (int) (150 + 100 * Math.sin(System.currentTimeMillis() * 0.005 + star[0] * 0.01));
			brightness = Math.max(100, Math.min(255, brightness));

			g.setColor(new Color(255, 255, 255, brightness));
			g.fillOval(star[0], star[1], 3, 3);

			// ??ë³„ë“¤?€ ??ë°ê²Œ
			if ((star[0] + star[1]) % 7 == 0) {
				g.fillOval(star[0] - 1, star[1] - 1, 5, 5);
			}
		}
	}

	/**
	 * ?¼ìª½ ?¬ë ˆ???•ë³´ë¥?ê·¸ë¦½?ˆë‹¤
	 */
	private void drawCredits(Graphics2D g) {
		// ?¬ë ˆ???œëª©
		g.setColor(new Color(200, 180, 120));
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
		g.drawString("??MUSIC PRODUCER", 80, 120);

		// ?¬ë ˆ??ëª©ë¡
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

		// ì¶”ê? ?•ë³´
		g.setColor(new Color(150, 150, 150));
		g.setFont(getKoreanFont(java.awt.Font.ITALIC, 14));
		g.drawString("Rhythm Game Experience", 80, 320);
		g.drawString("Powered by Java", 80, 340);
	}

	/**
	 * ?¤ë¥¸ìª??¼ì•„???ì—­??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawPianoArea(Graphics2D g) {
		// ?¼ì•„???¤ë£¨??ê·¸ë¦¬ê¸?		g.setColor(new Color(40, 40, 60));
		g.fillRect(750, 300, 400, 200);
		g.fillRect(750, 250, 50, 50); // ?¼ì•„???ë‹¨

		// ?¼ì•„??ê±´ë°˜
		g.setColor(Color.WHITE);
		for (int i = 0; i < 20; i++) {
			g.fillRect(750 + (i * 20), 450, 18, 50);
			g.setColor(Color.BLACK);
			g.drawRect(750 + (i * 20), 450, 18, 50);
			g.setColor(Color.WHITE);
		}

		// ê²€?€ ê±´ë°˜
		g.setColor(Color.BLACK);
		for (int i = 0; i < 19; i++) {
			if (i % 7 != 2 && i % 7 != 6) { // ë¯¸ì? ???¬ì´?ëŠ” ê²€?€ ê±´ë°˜???†ìŒ
				g.fillRect(760 + (i * 20), 450, 10, 30);
			}
		}

		// ?°ì£¼???¤ë£¨??		g.setColor(new Color(80, 60, 100));
		// ë¨¸ë¦¬
		g.fillOval(680, 200, 80, 80);
		// ëª?		g.fillRect(700, 280, 40, 120);
		// ??		g.fillRect(650, 300, 100, 20);
		g.fillRect(720, 320, 80, 20);

		// ?€?¸ì”
		g.setColor(new Color(150, 100, 50));
		g.fillRect(900, 280, 3, 30);
		g.fillOval(895, 270, 13, 15);
		g.setColor(new Color(120, 50, 50));
		g.fillOval(897, 272, 9, 8);
	}

	/**
	 * ë©”ì¸ ?€?´í???ê·¸ë¦½?ˆë‹¤
	 */
	private void drawMainTitle(Graphics2D g) {
		// ê·¸ë¼?°ì´???¨ê³¼ë¥??„í•œ ?€?´í?
		g.setColor(new Color(255, 215, 0));
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 48));
		g.drawString("JAVA MAX", 450, 150);

		// ?œë¸Œ?€?´í?
		g.setColor(new Color(200, 200, 200));
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 20));
		g.drawString("ë¦¬ë“¬ ê²Œì„???ˆë¡œ??ê²½í—˜", 500, 180);

		// ?˜ë‹¨ ?¤ëª…
		g.setColor(new Color(150, 150, 150));
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString("?Œì•…ê³??¨ê»˜?˜ëŠ” ?„ë²½???€?´ë°???¸ê³„ë¡?, 480, 550);
	}

	/**
	 * ê³?ëª©ë¡ ?”ë©´??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawTrackListScreen(Graphics2D g) {
		// ë°°ê²½???¤ì • (?´ë‘??ë°°ê²½)
		g.setColor(new Color(0, 0, 0, 180));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		// ?¼ìª½ ?ì—­: ? íƒ??ê³??•ë³´
		drawSelectedTrackInfo(g);

		// ?¤ë¥¸ìª??ì—­: ê³?ëª©ë¡ ë¦¬ìŠ¤??		drawTrackList(g);
	}

	/**
	 * ?¼ìª½ ?ì—­??? íƒ??ê³??•ë³´ë¥?ê·¸ë¦½?ˆë‹¤
	 */
	private void drawSelectedTrackInfo(Graphics2D g) {
		if (trackList.size() > nowSelected) {
			Track selectedTrack = trackList.get(nowSelected);

			// ?¨ë²” ?„íŠ¸ ?ì—­
			int albumX = 50;
			int albumY = 100;
			int albumWidth = 300;
			int albumHeight = 250;

			// ?¨ë²” ?„íŠ¸ ë°°ê²½
			g.setColor(new Color(50, 50, 50, 200));
			g.fillRoundRect(albumX, albumY, albumWidth, albumHeight, 15, 15);

			// ?¨ë²” ?„íŠ¸ ?´ë?ì§€
			if (titleImage != null) {
				g.drawImage(titleImage, albumX + 10, albumY + 10, albumWidth - 20, albumHeight - 20, null);
			}

			// ê³??œëª©
			g.setColor(Color.WHITE);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 24));
			g.drawString(selectedTrack.getTitleName(), albumX, albumY + albumHeight + 40);

			// ìµœê³  ?ìˆ˜ ?•ë³´
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
	 * ?¤ë¥¸ìª??ì—­??ê³?ëª©ë¡??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawTrackList(Graphics2D g) {
		int listX = 450;
		int listY = 80;
		int listWidth = 750;
		int itemHeight = 80;

		// ?œëª© ?¤ë”
		g.setColor(new Color(70, 70, 70, 220));
		g.fillRoundRect(listX, listY - 40, listWidth, 35, 10, 10);
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 18));
		g.drawString("ALL TRACK", listX + 20, listY - 18);

		// ê°?ê³¡ì„ ë¦¬ìŠ¤?¸ë¡œ ?œì‹œ
		for (int i = 0; i < trackList.size(); i++) {
			Track track = trackList.get(i);
			int itemY = listY + (i * (itemHeight + 10));

			// ? íƒ??ê³??˜ì´?¼ì´??			if (i == nowSelected) {
				g.setColor(new Color(100, 150, 255, 180));
			} else {
				g.setColor(new Color(50, 50, 50, 150));
			}
			g.fillRoundRect(listX, itemY, listWidth, itemHeight, 10, 10);

			// ê³??œëª©
			g.setColor(Color.WHITE);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 18));
			g.drawString(track.getTitleName(), listX + 20, itemY + 25);

			// ìµœê³  ?ìˆ˜ ?œì‹œ
			String trackName = track.getTitleName();
			int easyScore = dbManager.getBestScore(trackName + " (Easy)");
			int hardScore = dbManager.getBestScore(trackName + " (Hard)");

			// Easy ?œì´??ë²„íŠ¼
			g.setColor(new Color(100, 255, 100, 200));
			g.fillRoundRect(listX + 20, itemY + 35, 120, 25, 5, 5);
			g.setColor(Color.BLACK);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 12));
			g.drawString("Easy: " + easyScore, listX + 25, itemY + 50);

			// Hard ?œì´??ë²„íŠ¼
			g.setColor(new Color(255, 100, 100, 200));
			g.fillRoundRect(listX + 150, itemY + 35, 120, 25, 5, 5);
			g.setColor(Color.BLACK);
			g.setFont(getKoreanFont(java.awt.Font.BOLD, 12));
			g.drawString("Hard: " + hardScore, listX + 155, itemY + 50);

			// ?Œë ˆ??ê°€???œì‹œ
			g.setColor(new Color(200, 200, 200));
			g.setFont(getKoreanFont(java.awt.Font.PLAIN, 12));
			g.drawString("PLAYABLE", listX + listWidth - 80, itemY + 25);

			// ë³„ì ?´ë‚˜ ?ˆë²¨ ?œì‹œ
			g.setColor(Color.YELLOW);
			for (int star = 0; star < 3; star++) {
				g.fillOval(listX + listWidth - 120 + (star * 15), itemY + 35, 10, 10);
			}

			// ?¸ë²„ ?¨ê³¼ë¥??„í•œ ê²½ê³„??			if (i == nowSelected) {
				g.setColor(new Color(255, 255, 255, 100));
				g.drawRoundRect(listX, itemY, listWidth, itemHeight, 10, 10);
			}
		}
	}

	public void selectTrack(int nowSelected) {// ?„ì¬ ? íƒ??ê³¡ì˜ ë²ˆí˜¸ ?£ì–´??? íƒ??ê²ƒì„ ?Œë ¤ì¤€??		if (selectedMusic != null)// selectedMusicê°€ ?†ë‹¤ë©?ì¢…ë£Œ
			selectedMusic.close();// ?´ë–¤ ê³¡ì´ ?¤í–‰ ?˜ê³  ?ˆë‹¤ë©?ì¢…ë£Œ
		// ?„ì¬ ? íƒ??ê³¡ì˜ ?€?´í???ê°€?¸ì????£ì–´ì¤€??
		// ?Œì•… ?´ë?ì§€
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);// ? íƒ??ê³??˜ì´?¼íŠ¸ ë¶€ë¶?ë¬´ì•ˆ ?¬ìƒ
		selectedMusic.start();// ?„ì¬ ? íƒ??ê³¡ì˜ ?€?´í???ê°€?¸ì????£ì–´ì¤€??
	}

	// ?¸ë˜ ? íƒ ?¼ì¡´ë²„íŠ¼ ?¤ë¥¸ìª?ë²„íŠ¼ ?Œë ¸?„ë•Œ ?´ë²¤??	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;// 0ë²?ì¨?ê³¡ì„ ?ŒëŠ” ê°€???¤ë¥¸ìª?ê³¡ìœ¼ë¡?ê°„ë‹¤
		else
			nowSelected--;// ?„ë‹ê²½ìš° 1??ëº¸ë‹¤
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)// ë§ˆì?ë§‰ê³¡?´ë¼ë©?0ë²ˆì? ê³¡ì„ ?ŒëŠ” ê°„ë‹¤
			nowSelected = 0;
		else
			nowSelected++;// ?„ë‹ê²½ìš° 1?????œë‹¤.
		selectTrack(nowSelected);
	}

	public void backMain() {
		// ê²Œì„ ì¢…ë£Œ ???ìˆ˜ ?€??		if (game != null && !game.isGameEnded()) {
			game.saveScore();
		}

		// ?¤ì • ?”ë©´?ì„œ ?Œì•„?¤ëŠ” ê²½ìš°
		if (isSettingsScreen) {
			isSettingsScreen = false;
			// ?¤ì •?ì„œ ?Œì•„???ŒëŠ” ?œì‘ ?”ë©´?¼ë¡œ ?Œì•„ê°€ê¸?			isMainScreen = false;

			// ?œì‘ ?”ë©´ ë²„íŠ¼???¤ì‹œ ë³´ì´ê¸?			startButton.setVisible(true);
			quitButton.setVisible(true);
			settingsButton.setVisible(true);

			// ?¤ë¡œê°€ê¸?ë²„íŠ¼ ?¨ê¸°ê¸?			backButton.setVisible(false);

			// ë°°ê²½ ë³€ê²?			background = new ImageIcon(Main.class.getResource("../images/backgrounds/introBackground.jpg")).getImage();

			// ?¸íŠ¸ë¡??Œì•… ?¬ì‹œ??			introMusic = new Music("background/introMusic.mp3", true);
			introMusic.start();
			return;
		}

		isMainScreen = true;// ë©”ì¸?¼ë¡œ ?Œì•„??
		// ë²„íŠ¼?¤ì´ ë³´ì´?„ë¡ ?˜ì • (?ˆë¡œ??UI??ë§ê²Œ ì¡°ì •)
		leftButton.setVisible(false); // ?ˆë¡œ??UI?ì„œ???„ìš” ?†ìŒ
		rightButton.setVisible(false); // ?ˆë¡œ??UI?ì„œ???„ìš” ?†ìŒ
		easyButton.setVisible(false); // ?ˆë¡œ??UI?ì„œ???„ìš” ?†ìŒ
		hardButton.setVisible(false); // ?ˆë¡œ??UI?ì„œ???„ìš” ?†ìŒ
		scoreButton.setVisible(true);
		settingsButton.setVisible(false); // ë©”ì¸ ?”ë©´?ì„œ???¤ì • ë²„íŠ¼ ?¨ê?

		background = new ImageIcon(Main.class.getResource("../images/backgrounds/mainBackground.jpg"))
				.getImage();
		backButton.setVisible(false);// ?¤ë¡œê°€ê¸??¨ê¸°ê¸?		selectTrack(nowSelected);// ?˜ì´?¼ì´??ë¶€ë¶??¬ìƒ
		isGameScreen = false;
		if (game != null) {
			game.close();// ê³???ì¢…ë£Œ ?¨ìœ¼ë¡????¤ë¥¸ ê³¡ì„ ? íƒ ? ìˆ˜ ?ˆë„ë¡??˜ê¸°
		}
	}

	public void gameStart(int nowSelected, String difficulty) {// ëª¨ë“œ???•ë³´ë¥?ë°›ëŠ”??		if (selectedMusic != null)// ?Œì•…???¤í–‰ì¤‘ì´?¼ë©´
			selectedMusic.close();// ?Œì•… ì¢…ë£Œ

		isMainScreen = false;// ë©”ì¸ ?”ë©´???„ë‹ˆ?¼ê³  ?¤ì •

		// ë©”ì¸ ?”ë©´ ë²„íŠ¼???¨ê?
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		scoreButton.setVisible(false);
		settingsButton.setVisible(false); // ê²Œì„ ì¤‘ì—???¤ì • ë²„íŠ¼ ?¨ê?

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();// ?„ì¬ ? íƒ????ê²Œì„???´ë?ì§€ë¥?ë¶ˆëŸ¬?¨ë‹¤
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());// ?„ì¬? íƒœ??ê³¡ì„
		game.start();// ?°í•¨???¤í–‰ (?¸íŠ¸ ?ì„±)
		setFocusable(true);// ?¤ë³´???¬ì»¤?¤ë? ë§ì¶¤
	}

	public void enterMain() {
		// Music selectedMusic = new Music("Wellerman.mp3",true);//?˜ì–´ ?¬ë–„ ? íƒ??ê³??¬ìƒ(?˜ì´?¼ì´??		// ê³¡ì¬??30ì´?
		// selectedMusic.start();//?œì‘
		// ê²Œì„?œì‘ ?´ë²¤??ë©”ì¸ ?”ë©´?¼ë¡œ ?´ë™)
		startButton.setVisible(false);// ?œì‘ë²„íŠ¼ ??ë³´ì´?„ë¡ ?˜ê¸°
		quitButton.setVisible(false);// ì¢…ë£Œë²„íŠ¼ ??ë³´ì´?„ë¡ ?˜ê¸°
		settingsButton.setVisible(false);// ?¤ì • ë²„íŠ¼ ?¨ê¸°ê¸?
		background = new ImageIcon(Main.class.getResource("../images/backgrounds/mainBackground.jpg")).getImage();// ê²Œì„
																													// ?œì‘
																													// ë°°ê²½

		isMainScreen = true;// ? íƒê³??´ë?ì§€ë¡?ë³€ê²?
		// ?Œì•… ? íƒ ë²„íŠ¼ ?¨ê¸°ê¸?(?ˆë¡œ??UI?ì„œ???„ìš” ?†ìŒ)
		leftButton.setVisible(false);// ?Œì•…? íƒ ?¼ìª½ ë²„íŠ¼ ?¨ê¸°ê¸?		rightButton.setVisible(false);// ?Œì•…? íƒ ?¤ë¥¸ìª?ë²„íŠ¼ ?¨ê¸°ê¸?
		// ëª¨ë“œ ë²„íŠ¼ ?¨ê¸°ê¸?(?ˆë¡œ??UI?ì„œ??ë¦¬ìŠ¤?¸ì—??ì§ì ‘ ? íƒ)
		easyButton.setVisible(false);// ?´ì? ëª¨ë“œ ?¨ê¸°ê¸?		hardButton.setVisible(false);// ?˜ë“œ ëª¨ë“œ ?¨ê¸°ê¸?		scoreButton.setVisible(true);// ?ìˆ˜ë³´ë“œ ë²„íŠ¼

		// ?¸íŠ¸ë¡??Œì•… ?•ì?
		if (introMusic != null) {
			introMusic.close();
		}

		selectTrack(0);// 0ë²ˆì§¸ ê³¡ì„ ? íƒ?´ë¼
	}

	/**
	 * ?œê???ì§€?í•˜???°íŠ¸ë¥?ë°˜í™˜?©ë‹ˆ??
	 */
	private java.awt.Font getKoreanFont(int style, int size) {
		// ?œê???ì§€?í•˜???°íŠ¸???œì„œ?€ë¡??œë„
		String[] koreanFonts = {
				"ë§‘ì? ê³ ë”•", // Windows ê¸°ë³¸ ?œê? ?°íŠ¸
				"êµ´ë¦¼", // Windows êµ¬ë²„???œê? ?°íŠ¸
				"Apple SD Gothic Neo", // macOS ?œê? ?°íŠ¸
				"Noto Sans CJK KR", // Linux ?œê? ?°íŠ¸
				"Dialog" // ?œìŠ¤??ê¸°ë³¸ ?°íŠ¸ (fallback)
		};

		for (String fontName : koreanFonts) {
			try {
				java.awt.Font font = new java.awt.Font(fontName, style, size);
				// ?°íŠ¸ê°€ ?œê???ì§€?í•˜?”ì? ?•ì¸
				if (font.canDisplay('??)) {
					return font;
				}
			} catch (Exception e) {
				// ?°íŠ¸ ?ì„± ?¤íŒ¨ ???¤ìŒ ?°íŠ¸ ?œë„
				continue;
			}
		}

		// ëª¨ë“  ?°íŠ¸ê°€ ?¤íŒ¨??ê²½ìš° ê¸°ë³¸ Dialog ?°íŠ¸ ë°˜í™˜
		return new java.awt.Font(java.awt.Font.DIALOG, style, size);
	}

	/**
	 * ?„ì¬ ?¤ì • ?”ë©´?¸ì? ?•ì¸?©ë‹ˆ??	 */
	public boolean isSettingsScreen() {
		return isSettingsScreen;
	}

	/**
	 * ?¤ì •ê°’ì„ ë³€ê²½í•©?ˆë‹¤
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
	 * ?¤ì • ?”ë©´?¼ë¡œ ?´ë™?©ë‹ˆ??	 */
	public void enterSettings() {
		// ?„ì¬ ?¬ìƒ ì¤‘ì¸ ëª¨ë“  ?Œì•… ?•ì?
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		if (introMusic != null) {
			introMusic.close();
		}

		isMainScreen = false;
		isSettingsScreen = true;

		// ë©”ì¸ ?”ë©´ ë²„íŠ¼???¨ê¸°ê¸?		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		scoreButton.setVisible(false);
		settingsButton.setVisible(false);

		// ?œì‘ ?”ë©´ ë²„íŠ¼???¨ê¸°ê¸?		startButton.setVisible(false);
		quitButton.setVisible(false);

		// ?¤ë¡œê°€ê¸?ë²„íŠ¼ ë³´ì´ê¸?		backButton.setVisible(true);

		// ?¤ì • ?”ë©´ ë°°ê²½ ë³€ê²?		background = new ImageIcon(Main.class.getResource("../images/backgrounds/introBackground.jpg")).getImage();

		// ?¤ì • ê°ì²´ ì´ˆê¸°??		if (settings == null) {
			settings = Settings.getInstance();
		}
	}

	/**
	 * ?¤ì • ?”ë©´ ?´ë¦­ ì²˜ë¦¬
	 */
	private void handleSettingsClick(int x, int y) {
		// GAME SETTINGS ?¹ì…˜ ?´ë¦­
		if (y >= 130 && y <= 160) { // ê²Œì„ ?¸ì–´ ??			if (x >= 650 && x <= 750) {
				gameLanguage = gameLanguage.equals("KOREAN") ? "ENGLISH" : "KOREAN";
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 160 && y <= 190) { // FREESTYLE ?¤ì • ??			if (x >= 650 && x <= 750) {
				freestyleMode = !freestyleMode;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 190 && y <= 220) { // ë¦¬ë“¬ê°?ë°©í–¥?¤ì • ??			// ?¼ìª½ ?”ì‚´??			if (x >= 620 && x <= 640) {
				rhythmDirection = false;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
			// ?¤ë¥¸ìª??”ì‚´??			else if (x >= 730 && x <= 750) {
				rhythmDirection = true;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 220 && y <= 250) { // ?œê·¸ ??			if (x >= 650 && x <= 750) {
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

		// SOUND SETTINGS ?¹ì…˜ ?´ë¦­
		else if (y >= 310 && y <= 340) { // ë§ˆìŠ¤???ŒëŸ‰ ?¬ë¼?´ë”
			if (x >= 200 && x <= 500) {
				masterVolume = (int) ((x - 200) / 3.0);
				masterVolume = Math.max(0, Math.min(100, masterVolume));
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 340 && y <= 370) { // ëª…ë ¹???ŒëŸ‰ ?¬ë¼?´ë”
			if (x >= 200 && x <= 500) {
				commandVolume = (int) ((x - 200) / 3.0);
				commandVolume = Math.max(0, Math.min(100, commandVolume));
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 370 && y <= 400) { // ë°°ê²½???ŒëŸ‰ ?¬ë¼?´ë”
			if (x >= 200 && x <= 500) {
				backgroundVolume = (int) ((x - 200) / 3.0);
				backgroundVolume = Math.max(0, Math.min(100, backgroundVolume));
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		}

		// DISPLAY SETTINGS ?¹ì…˜ ?´ë¦­
		else if (y >= 480 && y <= 510) { // ?´ìƒ???¤ì •
			if (x >= 650 && x <= 750) {
				resolution = resolution.equals("PLAYABLE") ? "FULLSCREEN" : "PLAYABLE";
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 510 && y <= 540) { // ëª¨ë“œ
			if (x >= 650 && x <= 750) {
				displayMode = !displayMode;
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		} else if (y >= 540 && y <= 570) { // ?ë™?¬ìƒ ?¤ì •
			if (x >= 650 && x <= 750) {
				autoPlay = autoPlay.equals("TYPE A") ? "TYPE B" : "TYPE A";
				Music buttonMusic = new Music("sound_effects/buttonPressedMusic.mp3", false);
				buttonMusic.start();
			}
		}
	}

	/**
	 * ?¤ì • ?”ë©´??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawSettingsScreen(Graphics2D g) {
		// ?´ë‘??ë°°ê²½
		g.setColor(new Color(20, 20, 30));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		// GAME SETTINGS ?¹ì…˜
		drawSettingSection(g, "GAME SETTINGS", 50, 100);
		drawSettingRow(g, "ê²Œì„ ?¸ì–´", gameLanguage, 50, 130, true);
		drawSettingRow(g, "FREESTYLE ë¬´ì„  ?¤ì •", freestyleMode ? "ON" : "OFF", 50, 160, true);
		drawSettingRowWithArrows(g, "ë¦¬ë“¬ê°?ë°©í–¥?¤ì •", rhythmDirection ? "ON" : "OFF", 50, 190);
		drawSettingRow(g, "?œê·¸", rhythmTag, 50, 220, true);

		// SOUND SETTINGS ?¹ì…˜
		drawSettingSection(g, "SOUND SETTINGS", 50, 280);
		drawSliderRow(g, "ë§ˆìŠ¤???ŒëŸ‰", masterVolume, 50, 310);
		drawSliderRow(g, "ëª…ë ¹???ŒëŸ‰", commandVolume, 50, 340);
		drawSliderRow(g, "ë°°ê²½???ŒëŸ‰", backgroundVolume, 50, 370);
		drawSettingRow(g, "?¨ê³¼???¤ì •", soundEffect, 50, 400, false);

		// DISPLAY SETTINGS ?¹ì…˜
		drawSettingSection(g, "DISPLAY SETTINGS", 50, 450);
		drawSettingRow(g, "?´ìƒ???¤ì •", resolution, 50, 480, true);
		drawSettingRow(g, "ëª¨ë“œ", displayMode ? "ON" : "OFF", 50, 510, true);
		drawSettingRow(g, "?ë™?¬ìƒ ?¤ì •", autoPlay, 50, 540, true);
		drawSettingRow(g, "ì¹´ë©”??, camera, 50, 570, false);

		// ?˜ë‹¨ ë²„íŠ¼??		g.setColor(new Color(100, 100, 100));
		g.fillRect(50, 620, 120, 40);
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 16));
		g.drawString("?¤ë¡œ ê°€ê¸?, 70, 645);

		g.setColor(new Color(150, 150, 150));
		g.fillRect(200, 620, 60, 40);
		g.setColor(Color.BLACK);
		g.drawString("L2", 220, 645);
	}

	/**
	 * ?¤ì • ?¹ì…˜ ?œëª©??ê·¸ë¦½?ˆë‹¤
	 */
	private void drawSettingSection(Graphics2D g, String title, int x, int y) {
		g.setColor(new Color(200, 180, 100));
		g.setFont(getKoreanFont(java.awt.Font.BOLD, 18));
		g.drawString(title, x, y);

		// êµ¬ë¶„??		g.setColor(new Color(100, 100, 100));
		g.drawLine(x, y + 5, x + 700, y + 5);
	}

	/**
	 * ?¤ì • ?‰ì„ ê·¸ë¦½?ˆë‹¤
	 */
	private void drawSettingRow(Graphics2D g, String label, String value, int x, int y, boolean clickable) {
		// ë°°ê²½
		g.setColor(new Color(60, 60, 70));
		g.fillRect(x, y, 700, 25);

		// ?¼ë²¨
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString(label, x + 10, y + 18);

		// ê°?ë°°ê²½
		if (clickable) {
			g.setColor(new Color(120, 100, 80));
		} else {
			g.setColor(new Color(80, 80, 80));
		}
		g.fillRect(x + 600, y + 2, 95, 21);

		// ê°?		g.setColor(Color.WHITE);
		g.drawString(value, x + 610, y + 18);
	}

	/**
	 * ?”ì‚´?œê? ?ˆëŠ” ?¤ì • ?‰ì„ ê·¸ë¦½?ˆë‹¤
	 */
	private void drawSettingRowWithArrows(Graphics2D g, String label, String value, int x, int y) {
		// ë°°ê²½
		g.setColor(new Color(60, 60, 70));
		g.fillRect(x, y, 700, 25);

		// ?¼ë²¨
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString(label, x + 10, y + 18);

		// ?¼ìª½ ?”ì‚´??		g.setColor(new Color(150, 150, 150));
		g.fillRect(x + 570, y + 5, 20, 15);
		g.setColor(Color.BLACK);
		g.drawString("?€", x + 575, y + 16);

		// ê°?ë°°ê²½
		g.setColor(new Color(120, 100, 80));
		g.fillRect(x + 600, y + 2, 95, 21);
		g.setColor(Color.WHITE);
		g.drawString(value, x + 630, y + 18);

		// ?¤ë¥¸ìª??”ì‚´??		g.setColor(new Color(150, 150, 150));
		g.fillRect(x + 705, y + 5, 20, 15);
		g.setColor(Color.BLACK);
		g.drawString("??, x + 710, y + 16);
	}

	/**
	 * ?¬ë¼?´ë” ?‰ì„ ê·¸ë¦½?ˆë‹¤
	 */
	private void drawSliderRow(Graphics2D g, String label, int value, int x, int y) {
		// ë°°ê²½
		g.setColor(new Color(60, 60, 70));
		g.fillRect(x, y, 700, 25);

		// ?¼ë²¨
		g.setColor(Color.WHITE);
		g.setFont(getKoreanFont(java.awt.Font.PLAIN, 14));
		g.drawString(label, x + 10, y + 18);

		// ?¬ë¼?´ë” ë°°ê²½
		g.setColor(new Color(40, 40, 40));
		g.fillRect(x + 200, y + 8, 300, 10);

		// ?¬ë¼?´ë” ê°?		g.setColor(new Color(100, 150, 255));
		g.fillRect(x + 200, y + 8, (int) (300 * value / 100.0), 10);

		// ?¼ì„¼???œì‹œ
		g.setColor(Color.WHITE);
		g.drawString(value + "%", x + 520, y + 18);

		// ?¬ë¼?´ë” ?¸ë“¤
		int handleX = x + 200 + (int) (300 * value / 100.0) - 5;
		g.setColor(Color.WHITE);
		g.fillOval(handleX, y + 6, 10, 14);
	}
}
