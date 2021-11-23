package sprint7.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//O(3^n) complexity.
public class One {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        String stringOne = reader.readLine();
        String stringTwo = reader.readLine();

        int distance = calculateDistance(stringOne, stringTwo);
        System.out.println(distance);
    }

    static int calculateDistance(String stringOne, String stringTwo) {
        int[][] dp = new int[stringOne.length() + 1][stringTwo.length() + 1];

        for (int i = 0; i <= stringOne.length(); i++) {
            for (int j = 0; j <= stringTwo.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + costOfSubstitution(stringOne.charAt(i - 1), stringTwo.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[stringOne.length()][stringTwo.length()];
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}