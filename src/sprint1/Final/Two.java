package sprint1.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Two {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var simultaneouslyPressedKeys = Integer.parseInt(reader.readLine()) * 2;
        var sequence = getIntegerList(reader);
        var finalStr = getFinalString(simultaneouslyPressedKeys, sequence);
        System.out.println(finalStr);
    }

    private static String getFinalString(int simultaneouslyPressedKeys, List<Integer> sequence) {
        if (!sequence.isEmpty()) {
            var max = Collections.max(sequence);
            var min = Collections.min(sequence);
            var score = getScore(simultaneouslyPressedKeys, sequence, max, min);
            return String.valueOf(score);
        }
        return "0";
    }

    private static int getScore(int simultaneouslyPressedKeys, List<Integer> sequence, Integer max, Integer min) {
        int score = 0;
        int count = 0;
        for (int i = min; i <= max; i++) {
            for (var integer : sequence) {
                if (integer == i) {
                    count++;
                }
            }
            if (count > 0 && count <= simultaneouslyPressedKeys) {
                score++;
            }
            count = 0;
        }
        return score;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<Integer> getIntegerList(BufferedReader reader) throws IOException {
        var sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(reader.readLine());
        }
        var resultStr = sb.toString().replaceAll("[^\\d]", "");
        if (resultStr.isEmpty()) return List.of();
        return Arrays.stream(resultStr.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

}