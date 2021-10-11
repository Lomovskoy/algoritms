package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class G {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        if (size > 0) {
            int[] array = getArray(reader, size);
            int[] newArray = new int[array.length];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = array[i] == 0 ? -1 : array[i];
            }
            int len = getMaxElement(newArray);
            System.out.println(len);
        } else {
            System.out.println(size);
        }
    }

    private static int getMaxElement(int[] data) {
        Map<Integer, Integer> sumToPos = new HashMap<>();
        int total = 0;
        int maxLen = 0;
        for (int i = 0; i < data.length; ++i) {
            total += data[i];
            if (total == 0) {
                maxLen = i + 1;
            } else if (sumToPos.get(total) != null) {
                maxLen = Math.max(maxLen, i - sumToPos.get(total));
            } else {
                sumToPos.put(total, i);
            }
        }
        return maxLen;
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