package sprint7.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Java-программа на основе динамического программирования для разбиения на разделы
public class Two {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int size = Integer.parseInt(reader.readLine());
        int[] arr = getArray(reader, size);

        if (findPartition(arr, size))
            System.out.println("True");
        else
            System.out.println("False");

    }

    // Возвращает истину, если arr[] можно разделить на две части
    // подмножества с одинаковой суммой, иначе false
    static boolean findPartition(int[] arr, int n) {
        int sum = 0;
        int i, j;

        // Вычислить сумму всех элементов
        for (i = 0; i < n; i++)
            sum += arr[i];

        if (sum % 2 != 0)
            return false;

        boolean[][] part = new boolean[sum / 2 + 1][n + 1];

        // инициализируем верхнюю строку как истину
        for (i = 0; i <= n; i++)
            part[0][i] = true;

        // инициализируем крайний левый столбец, кроме части [0] [0], как 0
        for (i = 1; i <= sum / 2; i++)
            part[i][0] = false;

        // Заполняем таблицу разделов снизу вверх
        for (i = 1; i <= sum / 2; i++) {
            for (j = 1; j <= n; j++) {
                part[i][j] = part[i][j - 1];
                if (i >= arr[j - 1])
                    part[i][j]
                            = part[i][j]
                            || part[i - arr[j - 1]][j - 1];
            }
        }

        return part[sum / 2][n];
    }

    private static int[] getArray(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}