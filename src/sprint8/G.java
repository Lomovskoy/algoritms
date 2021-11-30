package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int sizeText = Integer.parseInt(reader.readLine());
        int[] arrayText = getArray(reader, sizeText);

        int sizePattern = Integer.parseInt(reader.readLine());
        int[] pattern = getArray(reader, sizePattern);

        int count = 0;
        int constant = 0;
        for (int i = 0; i < sizeText; i++) {
            for (int j = 0; j < sizePattern; j++) {
                if ( j == 0 && arrayText[i] != pattern[j]) {
                    constant = Math.abs(arrayText[i] - pattern[j]);
                }

                if (i + j < sizeText) {
                    int min = Math.min(arrayText[i + j], pattern[j]);
                    int max = Math.max(arrayText[i + j], pattern[j]);

                    if (min + constant == max) {
                        count++;
                    } else {
                        j = sizePattern;
                    }
                } else {
                    break;
                }
            }
            if (count == sizePattern) {
                System.out.print(i + 1 + " ");
            }
            count = 0;
            constant = 0;
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