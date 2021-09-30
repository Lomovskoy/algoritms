package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class One {

    private static final int LIMIT = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int storageSize = Integer.parseInt(reader.readLine());
        Map<String, Map<Integer, Integer>> dataMap = getDataMap(storageSize, reader);

        int requestsCount = Integer.parseInt(reader.readLine());
        responsePrint(requestsCount, reader, dataMap);

    }

    private static void responsePrint(int requestsCount, BufferedReader reader, Map<String, Map<Integer, Integer>> dataMap) throws IOException {
        Map<Integer, Integer> responseMap = new HashMap<>();
        for (int i = 0; i < requestsCount; i++) {
            for (String string : new LinkedHashSet<>(List.of(reader.readLine().split(" ")))) {
                var subMap = dataMap.get(string);
                if (subMap != null) {
                    for (var val : subMap.entrySet()) {
                        responseMap.merge(val.getKey(), val.getValue(), Integer::sum);
                    }
                }
            }
            print(responseMap);
        }
    }

    private static void print(Map<Integer, Integer> responseMap) {
        responseMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry::getKey)).limit(LIMIT)
                .forEach(in -> System.out.print((in.getKey() + 1) + " "));
        responseMap.clear();
        System.out.println();
    }

    private static Map<String, Map<Integer, Integer>> getDataMap(int storageSize, BufferedReader reader) throws IOException {
        Map<String, Map<Integer, Integer>> dataMap = new HashMap<>();
        for (int i = 0; i < storageSize; i++) {
            for (String str : reader.readLine().split(" ")) {
                if (dataMap.get(str) == null) {
                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(i, 1);
                    dataMap.put(str, map);
                } else {
                    Map<Integer, Integer> map = dataMap.get(str);
                    if (map.get(i) == null) {
                        map.put(i, 1);
                    } else {
                        map.put(i, map.get(i) + 1);
                    }
                }
            }
        }
        return dataMap;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}