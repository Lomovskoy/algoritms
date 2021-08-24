package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class L {

    private static int index = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] arr = getStringArray(reader, size);
        int stopDay = Integer.parseInt(reader.readLine());
        int firstDay = binarySearch(arr, stopDay, 0, size - 1);
        int secondDay = binarySearch(arr, stopDay + stopDay, 0, size - 1);
        if (firstDay >= 0)  ++firstDay;
        if (secondDay >= 0) ++secondDay;
        System.out.println(firstDay + " " + secondDay);
    }

    private static int binarySearch(int[] arr, int sought, int left, int right) {
        if (right <= left && index == Integer.MAX_VALUE && arr[right] < sought) return -1;

        int mid = (left + right) / 2;
        if (arr[mid] >= sought) {
            if (index > mid) {
                index = mid;
                return binarySearch(arr, sought, left, mid);
            } else {
                int result = index;
                index = Integer.MAX_VALUE;
                return result;
            }
        } else if (sought < arr[mid]) {
            return binarySearch(arr, sought, left, mid);
        } else {
            return binarySearch(arr, sought, mid + 1, right);
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