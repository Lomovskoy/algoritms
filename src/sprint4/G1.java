package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        if (size > 1) {
            if (size > 2) {
                int[] array = getArray(reader, size);
                int[] differences = new int[size];

                for (int i = 0; i < size; i++) {
                    if (i != 0) {
                        if (array[i] == 0) {
                            differences[i] = differences[i - 1] - 1;
                        } else {
                            differences[i] = differences[i - 1] + 1;
                        }
                    } else {
                        if (array[i] == 0) {
                            differences[i] = -1;
                        } else {
                            differences[i] = 1;
                        }
                    }
                }
                Map<Integer, List<Integer>> map = new HashMap<>();
                for (int i = 0; i < differences.length; i++) {
                    int key = differences[i];
                    if (map.get(key) == null) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        map.put(differences[i], list);
                    } else {
                        map.get(key).add(i);
                    }
                }

                int length = 0;
                for (var list : map.entrySet()) {
                    int max = Collections.max(map.get(list.getKey()));
                    int min = Collections.min(map.get(list.getKey()));
                    int result = max - min;
                    if (result > length) length = result;
                }
                System.out.println(length);
            }
            if (size == 2) {
                System.out.println(2);
            }
        } else {
            System.out.println(0);
        }
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