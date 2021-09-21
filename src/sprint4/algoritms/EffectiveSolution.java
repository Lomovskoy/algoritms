package sprint4.algoritms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
8
10
5 2 8 1 1 3 4 4

7
16
6 6 4 4 0 8 10
 */
public class EffectiveSolution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        int a = Integer.parseInt(reader.readLine());
        int[] array = getArray(reader, size);

        Arrays.sort(array);

        Set<Integer> history = new HashSet<Integer>();
        Set<List<Integer>> triples = new HashSet<List<Integer>>();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int target = a - array[i] - array[j];
                if (history.contains(target)) {
                    triples.add(List.of(target, array[i], array[j]));
                }
            }
            history.add(array[i]);
        }

        System.out.println(triples);
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