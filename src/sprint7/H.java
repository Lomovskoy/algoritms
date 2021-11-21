package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class H {

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int[] pair = getPair(reader);

        // n строк
        int n = pair[0] + 1;
        // m столбцов
        int m = pair[1] + 1;

        dp = new int[n][m];

        buildLea(reader, n, m);
        leadWay(n, m);

        System.out.println(dp[0][m - 1]);
    }

    private static void leadWay(int n, int m) {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j < m; j++) {
                int down = dp[i + 1][j];
                int left = dp[i][j - 1];
                int current = dp[i][j];
                dp[i][j] = Math.max(down, left) + current;
            }
        }
    }

    private static void buildLea(BufferedReader reader, int numberOfMatrixRows, int numberOfMatrixColumns) throws IOException {
        for (int i = 0; i < numberOfMatrixRows - 1; i++) {
            var rows = Arrays.stream(reader.readLine().split("")).map(Integer::parseInt).collect(Collectors.toList());
            for (int j = 1; j < numberOfMatrixColumns; j++) {
                dp[i][j] = rows.get(j - 1);
            }
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getPair(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }
}