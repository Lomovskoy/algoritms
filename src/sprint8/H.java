package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class H {

    public static void main(String[] args) throws IOException {
        try {

            BufferedReader reader = getReader();

            String string = reader.readLine();
            String pattern = reader.readLine();
            String replacement = reader.readLine();

            List<Integer> position = search(pattern, string);

            List<String> strings = new ArrayList<>();

            int current = 0;
            for (Integer integer : position) {
                String subStr = string.substring(current, integer);
                current = integer + pattern.length();
                strings.add(subStr);
            }
            if (!position.isEmpty()) {
                if (position.get(position.size() - 1) < string.length() - pattern.length()) {
                    String subStr = string.substring(position.get(position.size() - 1) + pattern.length());
                    strings.add(subStr);
                }
            } else {
                System.out.println(string);
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < strings.size(); i++) {
                if (i < position.size() && position.get(i) == 0) {
                    stringBuilder.append(replacement).append(strings.get(i));
                } else if (i < position.size() && position.get(i) > 0) {
                    stringBuilder.append(strings.get(i)).append(replacement);
                } else {
                    stringBuilder.append(strings.get(i));
                }
            }

            System.out.println(stringBuilder);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    // Функция возвращает все позиции вхождения шаблона в тексте.
    private static List<Integer> search(String pattern, String test) {
        List<Integer> result = new ArrayList<>();
        String string = pattern + '#' + test;

        // Массив длины |p|.
        int[] n = new int[pattern.length()];
        int n_prev = 0;

        for (int i = 1; i < string.length(); i++) {
            int k = n_prev;
            while ((k > 0) && (string.charAt(k) != string.charAt(i))) {
                k = n[k - 1];
            }
            if (string.charAt(k) == string.charAt(i)) {
                k += 1;
            }
            // Запоминаем только первые |p| значений n-функции.
            if (i < n.length) {
                n[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            n_prev = k;

            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == n.length) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                // - чтобы «переместиться» на начало найденного шаблона,
                // - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * n.length);
            }
        }
        return result;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}