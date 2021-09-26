package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class O {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] array = getArray(reader, size);
        int target = Integer.parseInt(reader.readLine());
        Arrays.sort(array);
        int trashIndex = getTrashIndex(array, target);
        System.out.println(trashIndex);
    }

    private static int getTrashIndex(int[] data, int k) {
        int left = 0;
        int right = data[data.length - 1] - data[0];
        while (left < right) {
            int middle = (right + left) / 2;
            if (checkIndex(data, middle, k)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private static boolean checkIndex(int[] data, int pos, int k) {
        int left = 0;
        int count = 0;
        for (int right = 1; right < data.length; ++right) {
            while (data[right] - data[left] > pos) {
                left++;
            }
            count += right - left;
            if (count >= k) {
                return true;
            }
        }
        return false;
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

}