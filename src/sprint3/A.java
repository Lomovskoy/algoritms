package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        genBraceSequence(size,0, 0, "");
    }

    private static void genBraceSequence(int n, int counterOpen, int counterClose, String answer) {
        if (counterOpen + counterClose == 2 * n) {
            System.out.println(answer);
            return;
        }
        if (counterOpen < n) {
            genBraceSequence(n, counterOpen + 1, counterClose, answer + "(");
        }
        if (counterOpen > counterClose) {
            genBraceSequence(n, counterOpen, counterClose + 1, answer + ")");
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
