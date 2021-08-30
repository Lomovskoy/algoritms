package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] arr = getStringArray(reader, size);
        countingSort(arr);

        StringBuilder stringBuilder = new StringBuilder();
        for (int v : arr) {
            stringBuilder.append(v).append(" ");
        }
        System.out.println(stringBuilder);
    }

    public static void countingSort(int[] array) {
        int[] patternArr = new int[3];
        for (int k : array) patternArr[k] += 1;

        int index = 0;
        for (int i = 0; i < patternArr.length; i++) {
            for (int j = 0; j < patternArr[i]; j++) {
                array[index++] = i;
            }
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static int[] getStringArray(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }
}