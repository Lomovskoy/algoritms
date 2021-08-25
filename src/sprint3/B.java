package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class B {

    private static final Map<Integer, List<String>> mapSequence = Map.of(
            2, List.of("a", "b", "c"),
            3, List.of("d", "e", "f"),
            4, List.of("g", "h", "i"),
            5, List.of("j", "k", "l"),
            6, List.of("m", "n", "o"),
            7, List.of("p", "q", "r", "s"),
            8, List.of("t", "u", "v"),
            9, List.of("w", "x", "y", "z")
    );

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var resultList = getStringArray(reader);
        getCombination(0, resultList, "");
    }

    private static void getCombination(int recursionLevel, List<List<String>> resultList, String str) {
        if (recursionLevel < resultList.size()) {
            for (var sim : resultList.get(recursionLevel)) {
                getCombination(recursionLevel + 1, resultList, str + sim);
            }
        } else {
            System.out.print(str + " ");
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<List<String>> getStringArray(BufferedReader reader) throws IOException {
        var result = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        var resultList = new ArrayList<List<String>>();
        for (var val : result) resultList.add(mapSequence.get(val));
        return resultList;
    }
}