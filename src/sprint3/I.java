package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class I {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] array = getArray(reader, size);
        int length = Integer.parseInt(reader.readLine());
        var map = getIntegerIntegerMap(size, array);
        var sort = sorted(length, map);
        var stringBuffer = new StringBuffer();
        for (var i : sort.entrySet()) stringBuffer.append(i.getKey()).append(" ");
        System.out.println(stringBuffer);
    }

    private static LinkedHashMap<Integer, Integer> sorted(int length, Map<Integer, Integer> map) {
        return map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(length)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static Map<Integer, Integer> getIntegerIntegerMap(int size, int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            if (map.get(array[i]) == null) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i]) + 1);
            }
        }
        return map;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getArray(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }
}