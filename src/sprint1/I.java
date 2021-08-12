package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {

    private static final String FALSE = "False";
    private static final String TRUE = "True";

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var value = Integer.parseInt(reader.readLine());
        var result = isPowerOfFour(value);
        System.out.println(result ? TRUE : FALSE);
    }

    public static boolean isPowerOfFour(int num) {
        if (num > 0) {
            while (num % 4 == 0) num /= 4;
        }
        return (num == 1);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
