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
            buildsequence(sequence, length);
            return getFinalString(length, sequence);
        } else {
            return String.valueOf(sequence.get(ZERO_INDEX));
        }
    }

    private static void buildsequence(List<Integer> sequence, int length) {
        var indicesZero = new ArrayList<Integer>();
        preBuildArray(length, sequence, indicesZero);
        building(length, indicesZero, sequence);
    }

    private static String getFinalString(int length, List<Integer> sequence) {
        var sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(sequence.get(i)).append(" ");
        return sb.toString();
    }

    private static void preBuildArray(int length, List<Integer> sequence, List<Integer> indicesZero) {
        for (int i = 0; i < length; i++) {
            if (sequence.get(i) != ZERO_INDEX) {
                sequence.set(i, null);
            } else {
                indicesZero.add(i);
            }
        }
    }

    private static void building(int length, ArrayList<Integer> indicesZero, List<Integer> sequence) {
        for (var index : indicesZero) {
            int left = index - 1, right = index + 1;
            if (left >= 0) {
                leftIteratorPass(sequence, left);
            }
            if (right <= length - 1) {
                rightIteratorPass(length, sequence, right);
            }
        }
    }

    private static void rightIteratorPass(int length, List<Integer> sequence, int right) {
        int zeroDistanceIndex = 1;
        for (int i = right; i < length; i++) {
            if (sequence.get(i) == null) {
                sequence.set(i, zeroDistanceIndex++);
            } else if (sequence.get(i) == ZERO_INDEX) {
                return;
            } else if (sequence.get(i) > zeroDistanceIndex) {
                sequence.set(i, zeroDistanceIndex++);
            } else {
                return;
            }
        }
    }

    private static void leftIteratorPass(List<Integer> sequence, int left) {
        int zeroDistanceIndex= 1;
        for (int i = left; i >= 0; i--) {
            if (sequence.get(i) == null) {
                sequence.set(i, zeroDistanceIndex++);
            } else if (sequence.get(i) == ZERO_INDEX) {
                return;
            } else if (sequence.get(i) > zeroDistanceIndex) {
                sequence.set(i, zeroDistanceIndex++);
            } else {
                return;
            }
        }
    }
}