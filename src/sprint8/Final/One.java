package sprint8.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
ID 60938889

-- ПРИНЦИП РАБОТЫ --
    Сначала мы распаковываем текущую строку с помощью рекурсии.
    Затем ищем наибольший общий префикс в этих строках,
    алгоритмом посимвольного сравнения строк.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Рассмотрим сравнение строк на позиции i, где образец S[O, m − 1] сопоставляется с частью текста T[i, i + m − 1].
    Предположим, что первое несовпадение произошло между T [i + j] и S [j], где 1 < j < m.
    Тогда T[i, i + j − 1] = S[O, j − 1] = P и a = T[i + j] ≠ S [j] = b.

    При сдвиге вполне можно ожидать, что префикс (начальные символы) образца S сойдется с каким-нибудь суффиксом
    (конечные символы) текста P. Длина наиболее длинного префикса, являющегося одновременно суффиксом,
    есть значение префикс-функции от строки S для индекса j.

    Это приводит нас к следующему алгоритму: пусть π [j] — значение префикс-функции от строки S[0, m − 1] для индекса j.
    Тогда после сдвига мы можем возобновить сравнения с места T [i + j] и S [π[j]] без потери возможного местонахождения образца.
    Можно показать, что таблица π может быть вычислена (амортизационно) за O(m) сравнений перед началом поиска.
    А поскольку строка T будет пройдена ровно один раз, суммарное время работы алгоритма будет равно O(m + n),
    где n — длина текста T.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Рекурсивная распаковка строк: O(n)
    Поиск наибольшего общего префикса: O(m + n)
    Общая сложность:  O(m + n * 2)
    Где n — длина текста T, m - количество сравнений перед началом поиска.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    O(n) Где n — длина текста

 */
public class One {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int size = Integer.parseInt(reader.readLine());
        String[] strings = getArray(reader, size);

        String answer = "";

        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i++) {
                if (i == 1) {
                    answer = longCommonPrefix(unpack(strings[i - 1]), unpack(strings[i]));
                } else {
                    answer = longCommonPrefix(answer, unpack(strings[i]));
                }
            }
        } else {
            answer = unpack(strings[0]);
        }

        System.out.println(answer);
    }

    public static String longCommonPrefix(String firstStr, String secondStr) {
        int minLength = Math.min(firstStr.length(), secondStr.length());
        for (int i = 0; i < minLength; i++) {
            if (firstStr.charAt(i) != secondStr.charAt(i)) {
                return firstStr.substring(0, i);
            }
        }
        return firstStr.substring(0, minLength);
    }

    private static String unpack(String string) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < string.length();) {
            Pair pair = unpackInternal(i, string);
            resultString.append(pair.getString());
            i = pair.getIndex();
        }
        return resultString.toString();
    }

    private static Pair unpackInternal(int index, String string) {
        if (string.charAt(index) == '[' || string.charAt(index) == ']') {
            return new Pair(++index, "");
        }

        StringBuilder subStringBuilder = new StringBuilder();

        while ((index < string.length()) && (string.charAt(index) != ']')) {
            if (Character.isLetter(string.charAt(index))) {
                subStringBuilder.append(string.charAt(index++));
            } else {
                int multiplier = Integer.parseInt(String.valueOf(string.charAt(index)));
                index += 2;
                Pair pair = unpackInternal(index, string);
                for (int i = 0; i < multiplier; i++) {
                    subStringBuilder.append(pair.getString());
                }
                index = pair.getIndex() + 1;
            }
        }
        return new Pair(index, subStringBuilder.toString());
    }

    private static String[] getArray(BufferedReader reader, int size) throws IOException {
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = reader.readLine();
        }
        return strings;
    }

    private static class Pair {
        private final int index;
        private final String string;

        private Pair(int index, String string) {
            this.index = index;
            this.string = string;
        }

        public int getIndex() {
            return index;
        }

        public String getString() {
            return string;
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}