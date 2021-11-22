package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class L {

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int[] pair = getPredicate(reader);

        // число слитков
        int numberIngots  = pair[0];
        // вместимость рюкзака
        int maxCapacity  = pair[1];

        dp = new int[numberIngots];
        int[][] count = new int[numberIngots][maxCapacity + 1];
        buildDp(reader, numberIngots);

        for (int i = 0; i < numberIngots; i++) {
            for (int j = 0; j <= maxCapacity; j++) {
                if (i == 0) {
                    if(dp[i] <= j) {
                        count[i][j] = dp[i];
                    }
                } else {
                    if(dp[i] <= j) {
                        if (dp[i] > count[i - 1][j]) {
                            count[i][j] = Math.max(dp[i] + count[i - 1][j - dp[i]], dp[i]);
                        } else {
                            count[i][j] = Math.max(dp[i] + count[i - 1][j - dp[i]], count[i - 1][j]);
                        }
                    } else {
                        count[i][j] = count[i - 1][j];
                    }
                }
            }
        }
        System.out.println(count[numberIngots - 1][maxCapacity]);

    }

    private static void buildDp(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            dp[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getPredicate(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }
}