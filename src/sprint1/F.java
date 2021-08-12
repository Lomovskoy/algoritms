package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class F {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var stringClear = reader.readLine().replaceAll("[^\\da-zA-Z]", "").toLowerCase(Locale.ROOT);
        var stringReverse = new StringBuilder(stringClear).reverse();
        System.out.println(stringClear.contentEquals(stringReverse) ? "True" : "False");
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
