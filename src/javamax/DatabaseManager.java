package javamax;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JSON 파일을 이용한 게임 데이터베이스 관리 클래스
 * 사용자 점수, 게임 기록, 설정 등을 관리합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class DatabaseManager {
    private static final String DATA_DIRECTORY = "data/";
    private static final String SCORES_FILE = DATA_DIRECTORY + "scores.json";
    private static final String SETTINGS_FILE = DATA_DIRECTORY + "settings.json";
    private static final String TRACKS_FILE = DATA_DIRECTORY + "tracks.json";

    private static DatabaseManager instance;

    /**
     * 싱글톤 패턴으로 DatabaseManager 인스턴스를 반환합니다.
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private DatabaseManager() {
        initializeDataDirectory();
        initializeFiles();
    }

    /**
     * 데이터 디렉토리를 초기화합니다.
     */
    private void initializeDataDirectory() {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    /**
     * 필요한 JSON 파일들을 초기화합니다.
     */
    private void initializeFiles() {
        createFileIfNotExists(SCORES_FILE, "[]");
        createFileIfNotExists(SETTINGS_FILE, "{}");
        createFileIfNotExists(TRACKS_FILE, createDefaultTracksJson());
    }

    /**
     * 파일이 존재하지 않으면 기본 내용으로 생성합니다.
     */
    private void createFileIfNotExists(String filePath, String defaultContent) {
        File file = new File(filePath);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.print(defaultContent);
            } catch (IOException e) {
                System.err.println("파일 생성 실패: " + filePath);
                e.printStackTrace();
            }
        }
    }

    /**
     * 기본 트랙 정보를 JSON 형태로 생성합니다.
     */
    private String createDefaultTracksJson() {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        json.append("  {\n");
        json.append("    \"id\": 1,\n");
        json.append("    \"titleImage\": \"Goodbye_mr_my.png\",\n");
        json.append("    \"startImage\": \"hellomo.png\",\n");
        json.append("    \"gameImage\": \"introBackground.jpg\",\n");
        json.append("    \"startMusic\": \"hellomy.mp3\",\n");
        json.append("    \"gameMusic\": \"hellomy.mp3\",\n");
        json.append("    \"titleName\": \"Goodbye Mr. My 머리카락\",\n");
        json.append("    \"difficulty\": [\"Easy\", \"Hard\"],\n");
        json.append("    \"bestScore\": 0\n");
        json.append("  },\n");
        json.append("  {\n");
        json.append("    \"id\": 2,\n");
        json.append("    \"titleImage\": \"Nathan_Evans_Wellerman.png\",\n");
        json.append("    \"startImage\": \"Energy Start Image.png\",\n");
        json.append("    \"gameImage\": \"introBackground.jpg\",\n");
        json.append("    \"startMusic\": \"Wellerman.mp3\",\n");
        json.append("    \"gameMusic\": \"Wellerman.mp3\",\n");
        json.append("    \"titleName\": \"Nathan Evans Wellerman\",\n");
        json.append("    \"difficulty\": [\"Easy\", \"Hard\"],\n");
        json.append("    \"bestScore\": 0\n");
        json.append("  },\n");
        json.append("  {\n");
        json.append("    \"id\": 3,\n");
        json.append("    \"titleImage\": \"Super_Shy_NewJeans.png\",\n");
        json.append("    \"startImage\": \"Super Shy.gif\",\n");
        json.append("    \"gameImage\": \"introBackground.jpg\",\n");
        json.append("    \"startMusic\": \"Super Shy.mp3\",\n");
        json.append("    \"gameMusic\": \"Super Shy.mp3\",\n");
        json.append("    \"titleName\": \"Super Shy 뉴진스\",\n");
        json.append("    \"difficulty\": [\"Easy\", \"Hard\"],\n");
        json.append("    \"bestScore\": 0\n");
        json.append("  }\n");
        json.append("]");
        return json.toString();
    }

    /**
     * 게임 점수를 저장합니다.
     */
    public void saveScore(String playerName, String trackName, String difficulty, int score) {
        try {
            List<Map<String, Object>> scores = readScores();

            Map<String, Object> scoreEntry = new HashMap<>();
            scoreEntry.put("playerName", playerName);
            scoreEntry.put("trackName", trackName);
            scoreEntry.put("difficulty", difficulty);
            scoreEntry.put("score", score);
            scoreEntry.put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            scores.add(scoreEntry);

            // 점수 순으로 정렬 (내림차순)
            scores.sort((a, b) -> Integer.compare((Integer) b.get("score"), (Integer) a.get("score")));

            writeScores(scores);

            // 트랙별 최고 점수 업데이트
            updateBestScore(trackName, difficulty, score);

        } catch (Exception e) {
            System.err.println("점수 저장 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 저장된 점수 목록을 읽어옵니다.
     */
    private List<Map<String, Object>> readScores() {
        try {
            String content = readFile(SCORES_FILE);
            if (content.trim().isEmpty() || content.equals("[]")) {
                return new ArrayList<>();
            }
            return parseJsonArray(content);
        } catch (Exception e) {
            System.err.println("점수 읽기 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * 점수 목록을 파일에 저장합니다.
     */
    private void writeScores(List<Map<String, Object>> scores) {
        try {
            String json = mapListToJson(scores);
            writeFile(SCORES_FILE, json);
        } catch (Exception e) {
            System.err.println("점수 쓰기 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 트랙별 최고 점수를 업데이트합니다.
     */
    private void updateBestScore(String trackName, String difficulty, int newScore) {
        try {
            List<Map<String, Object>> tracks = readTracks();

            for (Map<String, Object> track : tracks) {
                if (trackName.equals(track.get("titleName"))) {
                    int currentBest = (Integer) track.getOrDefault("bestScore", 0);
                    if (newScore > currentBest) {
                        track.put("bestScore", newScore);
                        writeTracks(tracks);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("최고 점수 업데이트 실패: " + e.getMessage());
        }
    }

    /**
     * 트랙 목록을 읽어옵니다.
     */
    public List<Map<String, Object>> readTracks() {
        try {
            String content = readFile(TRACKS_FILE);
            return parseJsonArray(content);
        } catch (Exception e) {
            System.err.println("트랙 읽기 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * 트랙 목록을 파일에 저장합니다.
     */
    private void writeTracks(List<Map<String, Object>> tracks) {
        try {
            String json = mapListToJson(tracks);
            writeFile(TRACKS_FILE, json);
        } catch (Exception e) {
            System.err.println("트랙 쓰기 실패: " + e.getMessage());
        }
    }

    /**
     * 특정 트랙의 최고 점수를 반환합니다.
     */
    public int getBestScore(String trackName) {
        List<Map<String, Object>> tracks = readTracks();
        for (Map<String, Object> track : tracks) {
            if (trackName.equals(track.get("titleName"))) {
                return (Integer) track.getOrDefault("bestScore", 0);
            }
        }
        return 0;
    }

    /**
     * 상위 N개의 점수를 반환합니다.
     */
    public List<Map<String, Object>> getTopScores(int limit) {
        List<Map<String, Object>> scores = readScores();
        return scores.subList(0, Math.min(limit, scores.size()));
    }

    /**
     * 특정 트랙의 상위 점수를 반환합니다.
     */
    public List<Map<String, Object>> getTopScoresByTrack(String trackName, int limit) {
        List<Map<String, Object>> allScores = readScores();
        List<Map<String, Object>> trackScores = new ArrayList<>();

        for (Map<String, Object> score : allScores) {
            if (trackName.equals(score.get("trackName"))) {
                trackScores.add(score);
            }
        }

        return trackScores.subList(0, Math.min(limit, trackScores.size()));
    }

    /**
     * 설정을 저장합니다.
     */
    public void saveSetting(String key, Object value) {
        try {
            Map<String, Object> settings = readSettings();
            settings.put(key, value);
            writeSettings(settings);
        } catch (Exception e) {
            System.err.println("설정 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 설정을 읽어옵니다.
     */
    public Object getSetting(String key, Object defaultValue) {
        try {
            Map<String, Object> settings = readSettings();
            return settings.getOrDefault(key, defaultValue);
        } catch (Exception e) {
            System.err.println("설정 읽기 실패: " + e.getMessage());
            return defaultValue;
        }
    }

    /**
     * 설정 맵을 읽어옵니다.
     */
    private Map<String, Object> readSettings() {
        try {
            String content = readFile(SETTINGS_FILE);
            if (content.trim().isEmpty() || content.equals("{}")) {
                return new HashMap<>();
            }
            return parseJsonObject(content);
        } catch (Exception e) {
            System.err.println("설정 읽기 실패: " + e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * 설정을 파일에 저장합니다.
     */
    private void writeSettings(Map<String, Object> settings) {
        try {
            String json = mapToJson(settings);
            writeFile(SETTINGS_FILE, json);
        } catch (Exception e) {
            System.err.println("설정 쓰기 실패: " + e.getMessage());
        }
    }

    // 유틸리티 메서드들

    /**
     * 파일 내용을 읽어옵니다.
     */
    private String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * 파일에 내용을 씁니다.
     */
    private void writeFile(String filePath, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(content);
        }
    }

    /**
     * JSON 배열 파싱 (SimpleJsonParser 사용)
     */
    private List<Map<String, Object>> parseJsonArray(String json) {
        return SimpleJsonParser.parseArray(json);
    }

    /**
     * JSON 객체 파싱 (SimpleJsonParser 사용)
     */
    private Map<String, Object> parseJsonObject(String json) {
        return SimpleJsonParser.parseObject(json);
    }

    /**
     * Map을 JSON 문자열로 변환 (SimpleJsonParser 사용)
     */
    private String mapToJson(Map<String, Object> map) {
        return SimpleJsonParser.toJson(map);
    }

    /**
     * Map 리스트를 JSON 배열 문자열로 변환 (SimpleJsonParser 사용)
     */
    private String mapListToJson(List<Map<String, Object>> list) {
        return SimpleJsonParser.toJsonArray(list);
    }
}