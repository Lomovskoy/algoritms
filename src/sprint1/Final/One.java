package sprint1.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class One {

    private static final int ZERO_INDEX = 0;

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var length = Integer.parseInt(reader.readLine());
        var sequence = getIntegerList(reader);
        var result = getResultString(length, sequence);
        System.out.println(result);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<Integer> getIntegerList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static String getResultString(int length, List<Integer> sequence) {
        if (length > 1) {
            buildSequence(sequence, length);
            return getFinalString(length, sequence);
        } else {
            return String.valueOf(sequence.get(ZERO_INDEX));
        }
    }

    private static void buildSequence(List<Integer> sequence, int length) {
        firstPass(length, sequence);
        secondPass(length, sequence);
    }

    private static String getFinalString(int length, List<Integer> sequence) {
        var sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(sequence.get(i)).append(" ");
        return sb.toString();
    }

    private static void firstPass(int length, List<Integer> sequence) {
        boolean flag = false;
        int index = 1;
        for (int i = 0; i < length; i++) {
            if (sequence.get(i) == ZERO_INDEX) {
                flag = true;
                index = 1;
            } else if (flag && sequence.get(i) != ZERO_INDEX) {
                sequence.set(i, index++);
            } else if (!flag) {
                sequence.set(i, Integer.MAX_VALUE);
            }
        }
    }

    private static void secondPass(int length, List<Integer> sequence) {
        boolean flag = false;
        int index = 1;
        for (int i = length - 1; i >= 0; i--) {
            if (sequence.get(i) == ZERO_INDEX) {
                flag = true;
                index = 1;
            } else if (flag && sequence.get(i) != ZERO_INDEX) {
                if (sequence.get(i) > index)
                    sequence.set(i, index++);
            }
        }
    }
}