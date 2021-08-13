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
            var resultArray = getResultArray(sequence, length);
            return getFinalString(length, resultArray);
        } else {
            return String.valueOf(sequence.get(ZERO_INDEX));
        }
    }

    private static int[] getResultArray(List<Integer> sequence, int length) {
        var indicesZero = new ArrayList<Integer>();
        var resultArray = getPreBuildArray(length, sequence, indicesZero);
        building(length, indicesZero, resultArray);
        return resultArray;
    }

    private static String getFinalString(int length, int[] resultArray) {
        var sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(resultArray[i]).append(" ");
        return sb.toString();
    }

    private static int[] getPreBuildArray(int length, List<Integer> sequence, List<Integer> indicesZero) {
        var resultArray = new int[length];
        for (int i = 0; i < length; i++) {
            if (sequence.get(i) != ZERO_INDEX) {
                resultArray[i] = -1;
            } else {
                indicesZero.add(i);
            }
        }
        return resultArray;
    }

    private static void building(int length, ArrayList<Integer> indicesZero, int[] resultArray) {
        for (var index : indicesZero) {
            int left = index - 1, right = index + 1;
            if (left >= 0) {
                leftIteratorPass(resultArray, left);
            }
            if (right <= length - 1) {
                rightIteratorPass(length, resultArray, right);
            }
        }
    }

    private static void rightIteratorPass(int length, int[] resultArray, int right) {
        int zeroDistanceIndex = 1;
        for (int i = right; i < length; i++) {
            if (resultArray[i] == ZERO_INDEX) {
                return;
            } else if (resultArray[i] == -1) {
                resultArray[i] = zeroDistanceIndex++;
            } else if (resultArray[i] > zeroDistanceIndex) {
                resultArray[i] = zeroDistanceIndex++;
            } else {
                return;
            }
        }
    }

    private static void leftIteratorPass(int[] resultArray, int left) {
        int zeroDistanceIndex= 1;
        for (int i = left; i >= 0; i--) {
            if (resultArray[i] == ZERO_INDEX) {
                return;
            } else if (resultArray[i] == -1) {
                resultArray[i] = zeroDistanceIndex++;
            } else if (resultArray[i] > zeroDistanceIndex) {
                resultArray[i] = zeroDistanceIndex++;
            } else {
                return;
            }
        }
    }

}