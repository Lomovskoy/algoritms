package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        var arr = getStringArray(reader);
        int stopDay = Integer.parseInt(reader.readLine());
        int firstDay = binarySearch(arr, stopDay, 0, size - 1);
        int secondDay = binarySearch(arr, stopDay + stopDay, 0, size - 1);
        if (firstDay >= 0)  ++firstDay;
        if (secondDay >= 0) ++secondDay;
        System.out.println(firstDay + " " + secondDay);
    }

    private static int binarySearch(List<Integer> arr, int sought, int left, int right) {
        if (right <= left) return -1;

        int mid = (left + right) / 2;
        if (arr.get(mid) >= sought) {
            return mid;
        } else if (sought < arr.get(mid)) {
            return binarySearch(arr, sought, left, mid);
        } else {
            return binarySearch(arr, sought, mid + 1, right);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<Integer> getStringArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

}