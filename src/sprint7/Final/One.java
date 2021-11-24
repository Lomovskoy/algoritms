package sprint7.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
ID 59103223

-- ПРИНЦИП РАБОТЫ --
    Проведём мысленный эксперимент.
    1. Если последние символы префиксов совпадают a(i - 1) == b(j - 1), то в этом случае можно не менять эти последние символы.
        Тогда F(i,j) = F(i - 1, j - 1).
    2. Если a(i - 1) != b(j - 1), то тогда можно потратить 1 операцию на замену символа a(i - 1) на b(j - 1) и также потратить
        F(i - 1, j - 1) операцию на превращение префикса A[:i - 1] в B[:j - 1]. Тогда F(i,j) = F(i - 1, j - 1) + 1.
    3. Символ a(i - 1) был удален при редактировании, тогда необходимо префикс A[:i - 1] превратить в B[:j],
        на что необходимо F(i - 1, j) операций редактирования. В этом случае F(i,j) = F(i - 1, j) + 1.
    4. Символ b(j - 1) был добавлен при редактировании, тогда необходимо префикс A[:i] превратить в B[:j - 1],
        на что необходимо F(i, j - 1) операций редактирования. В этом случае F(i,j) = F(i, j - 1) + 1.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Алгоритм вычисления расстояния Левенштейна между двумя словами аналогичен алгоритму нахождения наибольшей общей
    подпоследовательности. Пусть F(i,j) есть расстояние редактирования между префиксами данных строк A = A[:i] и B = B[:j].
    Затем рассматриваем последние символы этих префиксов a(i - 1) и b(j - 1). Посмотрим, как префикс B = B[:j] мог быть
    получен из префикcа A = A[:i] при помощи разрешенных операций редактирования.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    O(N * (M * 2))

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    O(N * 2)

 */
public class One {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        String stringOne = reader.readLine();
        String stringTwo = reader.readLine();

        int distance = calculateDistance(stringOne, stringTwo);
        System.out.println(distance);
    }

    static int calculateDistance(String stringOne, String stringTwo) {
        int[][] dp = new int[2][stringTwo.length() + 1];

        int index = 1;

        for (int i = 0; i <= stringTwo.length(); i++) {
            dp[0][i] = i;
        }

        int i = 1;
        while (index <= stringOne.length()) {
            for (int j = 0; j <= stringTwo.length(); j++) {
                if (j == 0) {
                    dp[i][j] = index;
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + costOfSubstitution(stringOne.charAt(index - 1), stringTwo.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
            System.arraycopy(dp[i], 0, dp[i - 1], 0, dp[i - 1].length);
            index++;
        }

        return dp[0][stringTwo.length()];
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}