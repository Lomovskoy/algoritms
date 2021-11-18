package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] array = getArray(reader, size);

        int purchaseIndex = -1;
        int sum = 0;
        boolean bought = false;
        for (int i = 1; i < size; i++) {
            if (array[i] > array[i - 1]) {
                if (!bought) {
                    purchaseIndex = i - 1;
                    bought = true;
                }
                if (i == size - 1) {
                    sum += (array[i] - array[purchaseIndex]);
                }
            } else if (array[i] < array[i - 1]) {
                if (bought) {
                    sum += (array[i - 1] - array[purchaseIndex]);
                    purchaseIndex = -1;
                    bought = false;
                }
            }
        }
        System.out.println(sum);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getArray(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return array;
    }
}