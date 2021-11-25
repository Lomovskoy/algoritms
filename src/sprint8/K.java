package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        char[] chars1 = reader.readLine().toCharArray();
        char[] chars2 = reader.readLine().toCharArray();

        StringBuilder stringBuilder1 = new StringBuilder();
        for (char c : chars1) {
            if (c % 2 == 0) {
                stringBuilder1.append(c);
            }
        }

        StringBuilder stringBuilder2 = new StringBuilder();
        for (char c : chars2) {
            if (c % 2 == 0) {
                stringBuilder2.append(c);
            }
        }

        int comp = stringBuilder1.compareTo(stringBuilder2);

        if (comp < 0) {
            System.out.println(-1);
        } else if (comp > 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}