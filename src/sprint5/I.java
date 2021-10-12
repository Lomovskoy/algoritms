package sprint5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int n = Integer.parseInt(reader.readLine());
        CatalnNumber cn = new CatalnNumber();
        System.out.println(cn.catalan(n));
    }

    static class CatalnNumber {

        // Рекурсивная функция для поиска n-го каталонского числа
        int catalan(int n) {
            int res = 0;
            // Базовый вариант
            if (n <= 1) {
                return 1;
            }
            for (int i = 0; i < n; i++) {
                res += catalan(i) * catalan(n - i - 1);
            }
            return res;
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}