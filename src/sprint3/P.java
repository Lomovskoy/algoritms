package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P {

    private static int index = 0;
    private static boolean flag = true;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int[] array = getArray(reader, size);
        Set<Integer> copy = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            copy.add(array[i]);
            if (array[i] > i) {
                for (int j = array[i]; j >= 0; j--) {
                    if (!copy.contains(array[j])) {
                        flag = false;
                        break;
                    }
                }
            } else {
                for (int k = 0; k <= i; k++) {
                    if (!copy.contains(k)) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                index++;
            } else {
                flag = true;
            }
        }

        System.out.println(index);
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