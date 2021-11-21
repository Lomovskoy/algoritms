package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class F {

    private final static long CONST = (long) Math.pow(10, 9) + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int[] pair = getIntegerList(reader);

        int targetStage = pair[0];
        int maxJumpWidth = pair[1];

        long[] ladder = new long[targetStage + 1];
        ladder[1] = 1;

        for (int i = 2; i <= targetStage; i++) {
            if (maxJumpWidth >= i) {
                for (int j = 1; j < i; j++) {
                    ladder[i] = (ladder[i] + ladder[j]) % CONST;
                }
            } else {
                for (int j = i - maxJumpWidth; j < i; j++) {
                    ladder[i] = (ladder[i] + ladder[j]) % CONST;
                }
            }
        }

        System.out.println(ladder[targetStage]);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getIntegerList(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }
}