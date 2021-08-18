package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var str = reader.readLine();
        boolean result = str.isEmpty() ? Boolean.TRUE : isCorrectBracketSeq(str);
        System.out.println(result ? "True" : "False");
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static boolean isCorrectBracketSeq(String str) {
        int lenP, lenC;
        var tmp = str.replaceAll("[^()\\[\\]{}]", "");
        do {
            lenP = tmp.length();
            tmp = tmp.replaceAll("\\(\\)", "");
            tmp = tmp.replaceAll("\\[\\]", "");
            tmp = tmp.replaceAll("\\{\\}", "");
            lenC = tmp.length();
        } while (lenP != lenC);
        return (lenC == 0);
    }
}