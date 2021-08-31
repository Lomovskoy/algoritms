package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = getReader();
        int sizeChildren = Integer.parseInt(reader.readLine());
        List<Integer> arrayChildren = getArray(reader, sizeChildren);
        arrayChildren.sort(Integer::compareTo);

        int sizeCookies = Integer.parseInt(reader.readLine());
        List<Integer> arrayCookies = getArray(reader, sizeCookies);
        arrayCookies.sort(Integer::compareTo);

        int sum = 0;
        int index = 0;
        for (int i = 0; i < sizeChildren; i++) {
            for (int j = index; j < sizeCookies; j++) {
                if (arrayChildren.get(i) <= arrayCookies.get(j)) {
                    sum++;
                    index = ++j;
                    break;
                }
            }
        }
        System.out.println(sum);

    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<Integer> getArray(BufferedReader reader, int size) throws IOException {
        List<Integer> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            list.add(Integer.parseInt(tokenizer.nextToken()));
        }
        return list;
    }
}