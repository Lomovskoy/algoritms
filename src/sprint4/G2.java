package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        if (size > 0) {
            int[] array = getArray(reader, size);
            int index = 0;
            int length = 0;
            float sum = 0;

            for (int i = 0; i < size; i++) {
                for (int j = i; j < size; j++) {
                    sum += array[j];
                    if (sum == ((index + 1F) / 2F)) {
                        if (length < index) {
                            length = index;
                        }
                    }
                    index++;
                }
                sum = 0;
                index = 0;
            }

            if (length > 0) {
                System.out.println(length + 1);
            } else {
                System.out.println(length);
            }
        } else {
            System.out.println(size);
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