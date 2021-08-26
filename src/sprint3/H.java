package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var size = Integer.parseInt(reader.readLine());
        var resultArr = getStringArray(reader);
        sorted(size, resultArr);
        var sb = new StringBuilder();
        for (var s : resultArr) {
            sb.append(s);
        }
        System.out.println(sb);
    }

    private static void sorted(int size, String[] resultArr) {
        String swap;
        boolean sorted;
        for (int i = 0; i < size; i++) {
            sorted = true;
            for (int j = 1; j < size - i; j++) {
                if ((resultArr[j - 1] + resultArr[j]).compareTo(resultArr[j] + resultArr[j - 1]) < 0) {
                    swap = resultArr[j - 1];
                    resultArr[j - 1] = resultArr[j];
                    resultArr[j] = swap;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static String[] getStringArray(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

}