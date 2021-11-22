package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class K {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int firstSize = Integer.parseInt(reader.readLine());
        int[] firstArray = getArray(reader, firstSize + 1);

        int secondSize = Integer.parseInt(reader.readLine());
        int[] secondArray = getArray(reader, secondSize + 1);

        int[][] dp = new int[firstSize + 1][secondSize + 1];

        buildDP(firstSize, firstArray, secondSize, secondArray, dp);

        int[] firstAnswer = new int[Math.min(firstSize, secondSize)];
        int[] secondAnswer = new int[Math.min(firstSize, secondSize)];

        Arrays.fill(firstAnswer, -1);
        Arrays.fill(secondAnswer, -1);

        int index = 0;
        index = getIndex(firstArray, secondArray, dp, firstAnswer, secondAnswer, firstSize, secondSize, index);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dp[firstSize][secondSize]);

        buildString(firstAnswer, secondAnswer, index, stringBuilder);
        System.out.println(stringBuilder);
    }

    private static void buildString(int[] firstAnswer, int[] secondAnswer, int index, StringBuilder stringBuilder) {
        if (index > 0) {
            stringBuilder.append("\n");

            for (int k = index - 1; k >= 0; k--) {
                stringBuilder.append(firstAnswer[k]).append(" ");
            }

            stringBuilder.append("\n");

            for (int k = index - 1; k >= 0; k--) {
                stringBuilder.append(secondAnswer[k]).append(" ");
            }
        }
    }

    private static int getIndex(int[] firstArray, int[] secondArray, int[][] dp, int[] firstAnswer, int[] secondAnswer, int i, int j, int index) {
        while (!(i == 0 || j == 0)) {
            if(firstArray[i] == secondArray[j]) {
                firstAnswer[index] = i;
                secondAnswer[index] = j;
                index++;
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j] && (i - 1 > 0)) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1] && (j - 1 > 0)) {
                j--;
            } else {
                i--;
                j--;
            }
        }
        return index;
    }

    private static void buildDP(int firstSize, int[] firstArray, int secondSize, int[] secondArray, int[][] dp) {
        for (int i = 1; i <= firstSize; i++) {
            for (int j = 1; j <= secondSize; j++) {
                if (firstArray[i] == secondArray[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getArray(BufferedReader reader, int size) throws IOException {
        int[] array = new int[size];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < size; i++) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return array;
    }
}