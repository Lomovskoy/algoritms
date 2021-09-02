package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class M {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int firstSize = Integer.parseInt(reader.readLine());
        int secondSize = Integer.parseInt(reader.readLine());

        int[] firstArray = getArray(reader, firstSize);
        int[] secondArray = getArray(reader, secondSize);

        List<Integer> list = new ArrayList<>();

        for (int i : firstArray) list.add(i);
        for (int j : secondArray) list.add(j);

        Collections.sort(list);

        if (list.size() % 2 == 1) {
            System.out.println(list.get(list.size() / 2));
        } else {
            double number = 0.5 * (list.get(list.size() / 2 - 1) + list.get(list.size() / 2));
            if(number % 1 == 0) {
                System.out.println((int)number);
            }else {
                System.out.println(number);
            }
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