package javamax;

import java.util.*;

/**
 * 간단한 JSON 파서 클래스
 * 외부 라이브러리 없이 기본적인 JSON 파싱 기능을 제공합니다.
 * 
 * @author PNJ
 * @version 1.0
 * @since 2024-02-20
 */
public class SimpleJsonParser {

    /**
     * JSON 문자열을 Map으로 파싱합니다.
     */
    public static Map<String, Object> parseObject(String json) {
        Map<String, Object> result = new HashMap<>();
        if (json == null || json.trim().isEmpty() || json.trim().equals("{}")) {
            return result;
        }

        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        String[] pairs = splitJsonPairs(json);
        for (String pair : pairs) {
            if (pair.trim().isEmpty())
                continue;

            int colonIndex = findColonIndex(pair);
            if (colonIndex == -1)
                continue;

            String key = pair.substring(0, colonIndex).trim();
            String value = pair.substring(colonIndex + 1).trim();

            // 키에서 따옴표 제거
            key = removeQuotes(key);

            // 값 파싱
            Object parsedValue = parseValue(value);
            result.put(key, parsedValue);
        }

        return result;
    }

    /**
     * JSON 배열 문자열을 List로 파싱합니다.
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parseArray(String json) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (json == null || json.trim().isEmpty() || json.trim().equals("[]")) {
            return result;
        }

        json = json.trim();
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1);
        }

        List<String> objects = splitJsonObjects(json);
        for (String obj : objects) {
            if (obj.trim().isEmpty())
                continue;
            Map<String, Object> parsedObject = parseObject(obj);
            result.add(parsedObject);
        }

        return result;
    }

    /**
     * 값을 적절한 타입으로 파싱합니다.
     */
    private static Object parseValue(String value) {
        value = value.trim();

        if (value.equals("null")) {
            return null;
        } else if (value.equals("true")) {
            return true;
        } else if (value.equals("false")) {
            return false;
        } else if (value.startsWith("\"") && value.endsWith("\"")) {
            // 문자열
            return value.substring(1, value.length() - 1);
        } else if (value.startsWith("[") && value.endsWith("]")) {
            // 배열 (문자열 배열로 처리)
            return parseStringArray(value);
        } else {
            // 숫자로 시도
            try {
                if (value.contains(".")) {
                    return Double.parseDouble(value);
                } else {
                    return Integer.parseInt(value);
                }
            } catch (NumberFormatException e) {
                // 문자열로 처리
                return value;
            }
        }
    }

    /**
     * 문자열 배열을 파싱합니다.
     */
    private static List<String> parseStringArray(String arrayString) {
        List<String> result = new ArrayList<>();
        if (arrayString.equals("[]")) {
            return result;
        }

        arrayString = arrayString.substring(1, arrayString.length() - 1);
        String[] items = arrayString.split(",");

        for (String item : items) {
            item = item.trim();
            if (item.startsWith("\"") && item.endsWith("\"")) {
                item = item.substring(1, item.length() - 1);
            }
            result.add(item);
        }

        return result;
    }

    /**
     * JSON 문자열에서 key-value 쌍들을 분리합니다.
     */
    private static String[] splitJsonPairs(String json) {
        List<String> pairs = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        int braceLevel = 0;
        int bracketLevel = 0;

        for (char c : json.toCharArray()) {
            if (c == '"' && (current.length() == 0 || current.charAt(current.length() - 1) != '\\')) {
                inQuotes = !inQuotes;
            }

            if (!inQuotes) {
                if (c == '{')
                    braceLevel++;
                else if (c == '}')
                    braceLevel--;
                else if (c == '[')
                    bracketLevel++;
                else if (c == ']')
                    bracketLevel--;
                else if (c == ',' && braceLevel == 0 && bracketLevel == 0) {
                    pairs.add(current.toString());
                    current = new StringBuilder();
                    continue;
                }
            }

            current.append(c);
        }

        if (current.length() > 0) {
            pairs.add(current.toString());
        }

        return pairs.toArray(new String[0]);
    }

    /**
     * JSON 객체들을 분리합니다.
     */
    private static List<String> splitJsonObjects(String json) {
        List<String> objects = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        int braceLevel = 0;

        for (char c : json.toCharArray()) {
            if (c == '"' && (current.length() == 0 || current.charAt(current.length() - 1) != '\\')) {
                inQuotes = !inQuotes;
            }

            if (!inQuotes) {
                if (c == '{') {
                    braceLevel++;
                } else if (c == '}') {
                    braceLevel--;
                    current.append(c);
                    if (braceLevel == 0) {
                        objects.add(current.toString());
                        current = new StringBuilder();
                        continue;
                    }
                } else if (c == ',' && braceLevel == 0) {
                    continue;
                }
            }

            if (!(c == ',' && braceLevel == 0)) {
                current.append(c);
            }
        }

        if (current.length() > 0 && !current.toString().trim().isEmpty()) {
            objects.add(current.toString());
        }

        return objects;
    }

    /**
     * 콜론의 위치를 찾습니다.
     */
    private static int findColonIndex(String pair) {
        boolean inQuotes = false;
        for (int i = 0; i < pair.length(); i++) {
            char c = pair.charAt(i);
            if (c == '"' && (i == 0 || pair.charAt(i - 1) != '\\')) {
                inQuotes = !inQuotes;
            } else if (c == ':' && !inQuotes) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 문자열에서 따옴표를 제거합니다.
     */
    private static String removeQuotes(String str) {
        str = str.trim();
        if (str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    /**
     * Map을 JSON 문자열로 변환합니다.
     */
    public static String toJson(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }

        StringBuilder json = new StringBuilder("{\n");
        boolean first = true;

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                json.append(",\n");
            }

            json.append("  \"").append(entry.getKey()).append("\": ");
            json.append(valueToJson(entry.getValue()));

            first = false;
        }

        json.append("\n}");
        return json.toString();
    }

    /**
     * Map 리스트를 JSON 배열 문자열로 변환합니다.
     */
    public static String toJsonArray(List<Map<String, Object>> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }

        StringBuilder json = new StringBuilder("[\n");

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                json.append(",\n");
            }

            String objectJson = toJson(list.get(i));
            // 들여쓰기 조정
            String[] lines = objectJson.split("\n");
            for (int j = 0; j < lines.length; j++) {
                if (j > 0)
                    json.append("\n");
                json.append("  ").append(lines[j]);
            }
        }

        json.append("\n]");
        return json.toString();
    }

    /**
     * 값을 JSON 형태로 변환합니다.
     */
    @SuppressWarnings("unchecked")
    private static String valueToJson(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + value + "\"";
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Number) {
            return value.toString();
        } else if (value instanceof List) {
            List<String> stringList = (List<String>) value;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < stringList.size(); i++) {
                if (i > 0)
                    sb.append(", ");
                sb.append("\"").append(stringList.get(i)).append("\"");
            }
            sb.append("]");
            return sb.toString();
        } else {
            return "\"" + value.toString() + "\"";
        }
    }
}