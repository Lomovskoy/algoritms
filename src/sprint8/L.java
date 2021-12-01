package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        String string = reader.readLine();
        int[] n = prefixFunction(string);

        StringBuilder stringBuilder = new StringBuilder();
        for (int element : n) {
            stringBuilder.append(element).append(" ");
        }
        System.out.println(stringBuilder);
    }

    // Функция возвращает массив длины |s|
    private static int[] prefixFunction(String string) {
        int[] n = new int[string.length()];

        for (int i = 1; i < string.length(); i++) {
            int k = n[i - 1];
            while ((k > 0) && (string.charAt(k) != string.charAt(i))) {
                k = n[k - 1];
            }

            if (string.charAt(k) == string.charAt(i)) {
                k += 1;
            }

            n[i] = k;
        }
        return n;
    }

    //функция prefix_function(s):
    //    # Функция возвращает массив длины |s|
    //    π = [0, None, None, ...]
    //    для i из [1 .. |s|):
    //        k = π[i - 1]
    //        пока (k > 0) и (s[k] ≠ s[i]):
    //            k = π[k - 1]
    //        если s[k] == s[i], то:
    //            k += 1
    //        π[i] = k
    //    вернуть π
    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}