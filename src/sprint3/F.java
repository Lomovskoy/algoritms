package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class F {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] array = getArray(reader, size);
        reverseSortArray(array);

        int sum = 0;
        for (int i = 0; i < size; i++) {
            if (i + 2 == size) {
                break;
            } else {
                if (array[i] < array[i + 1] + array[i + 2]) {
                    sum = array[i] + array[i + 1] + array[i + 2];
                    break;
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
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }

    public static void reverseSortArray(int[] array) {

        if (array.length < 2) {
            return;
        }

        int[] arrayB = new int[array.length / 2];
        System.arraycopy(array, 0, arrayB, 0, array.length / 2);

        int[] arrayC = new int[array.length - arrayB.length];
        System.arraycopy(array, arrayB.length, arrayC, 0, array.length - arrayB.length);

        reverseSortArray(arrayB);
        reverseSortArray(arrayC);

        mergeArray(array, arrayB, arrayC);

    }

    private static void mergeArray(int[] array, int[] arrayB, int[] arrayC) {

        int positionB = 0;
        int positionC = 0;

        for (int c = 0; c < array.length; c++) {
            if (positionB == arrayB.length) {
                array[c] = arrayC[positionC];
                positionC++;
            } else if (positionC == arrayC.length) {
                array[c] = arrayB[positionB];
                positionB++;
            } else if (arrayB[positionB] > arrayC[positionC]) {
                array[c] = arrayB[positionB];
                positionB++;
            } else {
                array[c] = arrayC[positionC];
                positionC++;
            }
        }
    }
}