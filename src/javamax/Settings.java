package javamax;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 게임 설정을 관리하는 클래스
 * 음량, 키 개수, 난이도, 키 매핑 등의 설정을 저장하고 불러옵니다.
 */
public class Settings {
    private static Settings instance;
    private static final String SETTINGS_FILE = "data/settings.json";

    // 기본 설정값
    private float volume = 0.5f; // 음량 (0.0 ~ 1.0)
    private int keyCount = 7; // 키 개수 (4, 6, 7)
    private String difficulty = "Easy"; // 난이도 (Easy, Normal, Hard)

    // 키 매핑 설정 (7키 기본)
    private Map<String, Integer> keyMapping7 = new HashMap<>();
    private Map<String, Integer> keyMapping6 = new HashMap<>();
    private Map<String, Integer> keyMapping4 = new HashMap<>();

    private Settings() {
        initializeDefaultKeyMappings();
        loadSettings();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    /**
     * 기본 키 매핑을 초기화합니다
     */
    private void initializeDefaultKeyMappings() {
        // 7키 매핑 (S, D, F, Space, J, K, L)
        keyMapping7.put("key1", 83); // S
        keyMapping7.put("key2", 68); // D
        keyMapping7.put("key3", 70); // F
        keyMapping7.put("key4", 32); // Space
        keyMapping7.put("key5", 74); // J
        keyMapping7.put("key6", 75); // K
        keyMapping7.put("key7", 76); // L

        // 6키 매핑 (S, D, F, J, K, L)
        keyMapping6.put("key1", 83); // S
        keyMapping6.put("key2", 68); // D
        keyMapping6.put("key3", 70); // F
        keyMapping6.put("key4", 74); // J
        keyMapping6.put("key5", 75); // K
        keyMapping6.put("key6", 76); // L

        // 4키 매핑 (D, F, J, K)
        keyMapping4.put("key1", 68); // D
        keyMapping4.put("key2", 70); // F
        keyMapping4.put("key3", 74); // J
        keyMapping4.put("key4", 75); // K
    }

    /**
     * 설정을 파일에서 불러옵니다
     */
    public void loadSettings() {
        try {
            File file = new File(SETTINGS_FILE);
            if (!file.exists()) {
                saveSettings(); // 기본 설정으로 파일 생성
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            if (content.length() > 2) { // "{}" 보다 길면
                parseSettings(content.toString());
            }
        } catch (IOException e) {
            System.err.println("설정 파일 로드 실패: " + e.getMessage());
        }
    }

    /**
     * JSON 형식의 설정을 파싱합니다
     */
    private void parseSettings(String jsonContent) {
        try {
            // 간단한 JSON 파싱 (SimpleJsonParser 사용)
            Map<String, Object> settings = SimpleJsonParser.parseObject(jsonContent);

            if (settings.containsKey("volume")) {
                this.volume = Float.parseFloat(settings.get("volume").toString());
            }
            if (settings.containsKey("keyCount")) {
                this.keyCount = Integer.parseInt(settings.get("keyCount").toString());
            }
            if (settings.containsKey("difficulty")) {
                this.difficulty = settings.get("difficulty").toString();
            }

            // 키 매핑 파싱
            if (settings.containsKey("keyMapping7")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mapping = (Map<String, Object>) settings.get("keyMapping7");
                for (Map.Entry<String, Object> entry : mapping.entrySet()) {
                    keyMapping7.put(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
                }
            }

        } catch (Exception e) {
            System.err.println("설정 파싱 실패: " + e.getMessage());
        }
    }

    /**
     * 설정을 파일에 저장합니다
     */
    public void saveSettings() {
        try {
            // data 디렉토리가 없으면 생성
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            FileWriter writer = new FileWriter(SETTINGS_FILE);
            writer.write("{\n");
            writer.write("  \"volume\": " + volume + ",\n");
            writer.write("  \"keyCount\": " + keyCount + ",\n");
            writer.write("  \"difficulty\": \"" + difficulty + "\",\n");
            writer.write("  \"keyMapping7\": {\n");

            int count = 0;
            for (Map.Entry<String, Integer> entry : keyMapping7.entrySet()) {
                writer.write("    \"" + entry.getKey() + "\": " + entry.getValue());
                if (++count < keyMapping7.size()) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("  },\n");
            writer.write("  \"keyMapping6\": {\n");

            count = 0;
            for (Map.Entry<String, Integer> entry : keyMapping6.entrySet()) {
                writer.write("    \"" + entry.getKey() + "\": " + entry.getValue());
                if (++count < keyMapping6.size()) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("  },\n");
            writer.write("  \"keyMapping4\": {\n");

            count = 0;
            for (Map.Entry<String, Integer> entry : keyMapping4.entrySet()) {
                writer.write("    \"" + entry.getKey() + "\": " + entry.getValue());
                if (++count < keyMapping4.size()) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("  }\n");
            writer.write("}");
            writer.close();

        } catch (IOException e) {
            System.err.println("설정 파일 저장 실패: " + e.getMessage());
        }
    }

    // Getter 메소드들
    public float getVolume() {
        return volume;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Setter 메소드들
    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume));
        saveSettings();
    }

    public void setKeyCount(int keyCount) {
        if (keyCount == 4 || keyCount == 6 || keyCount == 7) {
            this.keyCount = keyCount;
            saveSettings();
        }
    }

    public void setDifficulty(String difficulty) {
        if (difficulty.equals("Easy") || difficulty.equals("Normal") || difficulty.equals("Hard")) {
            this.difficulty = difficulty;
            saveSettings();
        }
    }

    /**
     * 현재 키 개수에 따른 키 매핑을 반환합니다
     */
    public Map<String, Integer> getCurrentKeyMapping() {
        switch (keyCount) {
            case 4:
                return keyMapping4;
            case 6:
                return keyMapping6;
            case 7:
            default:
                return keyMapping7;
        }
    }

    /**
     * 키 매핑을 설정합니다
     */
    public void setKeyMapping(int keyCount, String key, int keyCode) {
        switch (keyCount) {
            case 4:
                keyMapping4.put(key, keyCode);
                break;
            case 6:
                keyMapping6.put(key, keyCode);
                break;
            case 7:
                keyMapping7.put(key, keyCode);
                break;
        }
        saveSettings();
    }

    /**
     * 키 코드를 키 이름으로 변환합니다
     */
    public String getKeyName(int keyCode) {
        switch (keyCode) {
            case 32:
                return "Space";
            case 65:
                return "A";
            case 66:
                return "B";
            case 67:
                return "C";
            case 68:
                return "D";
            case 69:
                return "E";
            case 70:
                return "F";
            case 71:
                return "G";
            case 72:
                return "H";
            case 73:
                return "I";
            case 74:
                return "J";
            case 75:
                return "K";
            case 76:
                return "L";
            case 77:
                return "M";
            case 78:
                return "N";
            case 79:
                return "O";
            case 80:
                return "P";
            case 81:
                return "Q";
            case 82:
                return "R";
            case 83:
                return "S";
            case 84:
                return "T";
            case 85:
                return "U";
            case 86:
                return "V";
            case 87:
                return "W";
            case 88:
                return "X";
            case 89:
                return "Y";
            case 90:
                return "Z";
            default:
                return "Unknown";
        }
    }
}