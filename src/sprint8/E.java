package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class E {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        StringBuilder string = new StringBuilder(reader.readLine());
        int size = Integer.parseInt(reader.readLine());
        Map<Integer, String> map = getMap(reader, size);

        String[] strings = new String[size + 1];

        int i = 0;
        int currentIndex = 0;
        for (Map.Entry<Integer, String> str : map.entrySet()) {
            if (str.getKey() > 0) {
                strings[i] = string.substring(currentIndex, str.getKey());
                currentIndex = str.getKey();
                i++;
            }
            if (i == map.size()) {
                strings[i] = string.substring(currentIndex, string.length());
            }
        }

        i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        boolean first = false;
        for (Map.Entry<Integer, String> str : map.entrySet()) {
            if (i == 0 && str.getKey() == 0) {
                first = true;
            }
            if (first) {
                stringBuffer.append(str.getValue());
                if (strings[i] != null) {
                    stringBuffer.append(strings[i]);
                }
            } else {
                if (strings[i] != null) {
                    stringBuffer.append(strings[i]);
                }
                stringBuffer.append(str.getValue());
            }
            if (i == map.size() - 1) {
                if (strings[i + 1] != null) {
                    stringBuffer.append(strings[i + 1]);
                }
            }
            i++;
        }

        System.out.println(stringBuffer);
    }

    private static Map<Integer, String> getMap(BufferedReader reader, int size) throws IOException {
        Map<Integer, String> map = new TreeMap<>(Comparator.naturalOrder());
        StringTokenizer tokenizer;
        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String str = tokenizer.nextToken();
            int index = Integer.parseInt(tokenizer.nextToken());
            map.put(index, str);
        }
        return map;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}