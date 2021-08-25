package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class J {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var size = Integer.parseInt(reader.readLine());
        var resultArr = getStringArray(reader);
        sorted(size, resultArr);
    }

    private static void sorted(int size, int[] resultArr) {
        int swap;
        boolean sorted;
        boolean sortedNon = true;
        for (int i = 0; i < size; i++) {
            sorted = true;
            for (int j = 1; j < size - i; j++) {
                if (resultArr[j - 1] > resultArr[j]) {
                    swap = resultArr[j - 1];
                    resultArr[j - 1] = resultArr[j];
                    resultArr[j] = swap;
                    sorted = false;
                    sortedNon = false;
                }
            }
            if (sorted) break;
            System.out.println(getStringBuilder(resultArr));
        }
        if (sortedNon) System.out.println(getStringBuilder(resultArr));
    }

    private static StringBuilder getStringBuilder(int[] resultArr) {
        var str = new StringBuilder();
        for (var var : resultArr) {
            str.append(var).append(" ");
        }
        return str;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getStringArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}