package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = getReader();
        int[] first = getArray(reader, 2);
        int money = first[1];
        int[] homeArr = getArray(reader, first[0]);
        sortArray(homeArr);

        int sum = 0;
        for (Integer integer : homeArr) {
            if (integer <= money) {
                sum++;
                money -= integer;
            }
            else {
                break;
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

    public static void sortArray(int[] array) {

        if (array.length < 2) {
            return;
        }

        int[] arrayB = new int[array.length / 2];
        System.arraycopy(array, 0, arrayB, 0, array.length / 2);

        int[] arrayC = new int[array.length - arrayB.length];
        System.arraycopy(array, arrayB.length, arrayC, 0, array.length - arrayB.length);

        sortArray(arrayB);
        sortArray(arrayC);

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
            } else if (arrayB[positionB] < arrayC[positionC]) {
                array[c] = arrayB[positionB];
                positionB++;
            } else {
                array[c] = arrayC[positionC];
                positionC++;
            }
        }
    }
}