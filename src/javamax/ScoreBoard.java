package javamax;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * 점수 보드를 표시하는 클래스
 * 최고 점수와 최근 기록들을 보여줍니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class ScoreBoard extends JFrame {
    private DatabaseManager dbManager;
    private JPanel mainPanel;
    private JScrollPane scrollPane;

    public ScoreBoard() {
        dbManager = DatabaseManager.getInstance();
        initializeUI();
        loadScores();
    }

    /**
     * UI를 초기화합니다.
     */
    private void initializeUI() {
        setTitle("JavaMax - 점수 보드");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 메인 패널 설정
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        // 제목 추가
        JLabel titleLabel = new JLabel("점수 보드", JLabel.CENTER);
        titleLabel.setFont(getKoreanFont(Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // 스크롤 패널 설정
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane);

        // 닫기 버튼 추가
        JButton closeButton = new JButton("닫기");
        closeButton.setFont(getKoreanFont(Font.BOLD, 16));
        closeButton.setPreferredSize(new Dimension(100, 40));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * 점수 데이터를 로드하고 표시합니다.
     */
    private void loadScores() {
        try {
            // 전체 상위 점수 가져오기
            List<Map<String, Object>> topScores = dbManager.getTopScores(20);

            if (topScores.isEmpty()) {
                JLabel noScoreLabel = new JLabel("아직 저장된 점수가 없습니다.", JLabel.CENTER);
                noScoreLabel.setFont(getKoreanFont(Font.PLAIN, 18));
                noScoreLabel.setForeground(Color.GRAY);
                noScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainPanel.add(noScoreLabel);
            } else {
                // 상위 점수들 표시
                JLabel topScoresLabel = new JLabel("전체 상위 점수", JLabel.CENTER);
                topScoresLabel.setFont(getKoreanFont(Font.BOLD, 20));
                topScoresLabel.setForeground(Color.YELLOW);
                topScoresLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainPanel.add(topScoresLabel);
                mainPanel.add(Box.createVerticalStrut(10));

                // 점수 목록 표시
                for (int i = 0; i < topScores.size(); i++) {
                    Map<String, Object> scoreEntry = topScores.get(i);
                    JPanel scorePanel = createScorePanel(i + 1, scoreEntry);
                    mainPanel.add(scorePanel);
                    mainPanel.add(Box.createVerticalStrut(5));
                }
            }

            // 트랙별 최고 점수 표시
            mainPanel.add(Box.createVerticalStrut(20));
            JLabel trackBestLabel = new JLabel("트랙별 최고 점수", JLabel.CENTER);
            trackBestLabel.setFont(getKoreanFont(Font.BOLD, 20));
            trackBestLabel.setForeground(Color.CYAN);
            trackBestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(trackBestLabel);
            mainPanel.add(Box.createVerticalStrut(10));

            // 트랙별 최고 점수 표시
            String[] trackNames = {
                    "Goodbye Mr. My 머리카락",
                    "Nathan Evans Wellerman",
                    "Super Shy 뉴진스"
            };

            for (String trackName : trackNames) {
                int bestScore = dbManager.getBestScore(trackName);
                JPanel trackPanel = createTrackBestScorePanel(trackName, bestScore);
                mainPanel.add(trackPanel);
                mainPanel.add(Box.createVerticalStrut(5));
            }

        } catch (Exception e) {
            JLabel errorLabel = new JLabel("점수 로드 중 오류가 발생했습니다.", JLabel.CENTER);
            errorLabel.setFont(getKoreanFont(Font.PLAIN, 18));
            errorLabel.setForeground(Color.RED);
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(errorLabel);

            System.err.println("점수 로드 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // UI 업데이트
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * 개별 점수 패널을 생성합니다.
     */
    private JPanel createScorePanel(int rank, Map<String, Object> scoreEntry) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setMaximumSize(new Dimension(750, 60));
        panel.setPreferredSize(new Dimension(750, 60));

        // 순위 표시
        JLabel rankLabel = new JLabel(String.valueOf(rank), JLabel.CENTER);
        rankLabel.setFont(getKoreanFont(Font.BOLD, 18));
        rankLabel.setForeground(getRankColor(rank));
        rankLabel.setPreferredSize(new Dimension(50, 60));
        panel.add(rankLabel, BorderLayout.WEST);

        // 정보 패널
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(Color.DARK_GRAY);

        // 첫 번째 줄: 플레이어, 점수
        String playerName = (String) scoreEntry.get("playerName");
        Integer score = (Integer) scoreEntry.get("score");
        JLabel playerScoreLabel = new JLabel(playerName + " - " + score + "점");
        playerScoreLabel.setFont(getKoreanFont(Font.BOLD, 16));
        playerScoreLabel.setForeground(Color.WHITE);

        // 두 번째 줄: 곡명, 난이도, 날짜
        String trackName = (String) scoreEntry.get("trackName");
        String difficulty = (String) scoreEntry.get("difficulty");
        String date = (String) scoreEntry.get("date");

        // 날짜 포맷 간소화
        String formattedDate = date;
        if (date != null && date.length() > 10) {
            formattedDate = date.substring(0, 10); // yyyy-MM-dd 부분만
        }

        JLabel detailLabel = new JLabel(trackName + " (" + difficulty + ") - " + formattedDate);
        detailLabel.setFont(getKoreanFont(Font.PLAIN, 12));
        detailLabel.setForeground(Color.LIGHT_GRAY);

        infoPanel.add(playerScoreLabel);
        infoPanel.add(detailLabel);

        panel.add(infoPanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * 트랙별 최고 점수 패널을 생성합니다.
     */
    private JPanel createTrackBestScorePanel(String trackName, int bestScore) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
        panel.setMaximumSize(new Dimension(750, 40));
        panel.setPreferredSize(new Dimension(750, 40));

        // 트랙 이름
        JLabel trackLabel = new JLabel(trackName);
        trackLabel.setFont(getKoreanFont(Font.BOLD, 14));
        trackLabel.setForeground(Color.WHITE);
        trackLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // 최고 점수
        JLabel scoreLabel = new JLabel(bestScore + "점");
        scoreLabel.setFont(getKoreanFont(Font.BOLD, 14));
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        panel.add(trackLabel, BorderLayout.WEST);
        panel.add(scoreLabel, BorderLayout.EAST);

        return panel;
    }

    /**
     * 순위에 따른 색상을 반환합니다.
     */
    private Color getRankColor(int rank) {
        switch (rank) {
            case 1:
                return Color.YELLOW; // 금색
            case 2:
                return Color.LIGHT_GRAY; // 은색
            case 3:
                return new Color(205, 127, 50); // 동색
            default:
                return Color.WHITE;
        }
    }

    /**
     * 한글을 지원하는 폰트를 반환합니다.
     */
    private Font getKoreanFont(int style, int size) {
        // 한글을 지원하는 폰트들 순서대로 시도
        String[] koreanFonts = {
                "맑은 고딕", // Windows 기본 한글 폰트
                "굴림", // Windows 구버전 한글 폰트
                "Apple SD Gothic Neo", // macOS 한글 폰트
                "Noto Sans CJK KR", // Linux 한글 폰트
                "Dialog" // 시스템 기본 폰트 (fallback)
        };

        for (String fontName : koreanFonts) {
            try {
                Font font = new Font(fontName, style, size);
                // 폰트가 한글을 지원하는지 확인
                if (font.canDisplay('한')) {
                    return font;
                }
            } catch (Exception e) {
                // 폰트 생성 실패 시 다음 폰트 시도
                continue;
            }
        }

        // 모든 폰트가 실패한 경우 기본 Dialog 폰트 반환
        return new Font(Font.DIALOG, style, size);
    }

    /**
     * 점수 보드를 표시합니다.
     */
    public void showScoreBoard() {
        setVisible(true);
    }
}