package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class С {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        String[] pattern = getStringArray(reader);
        if (pattern.length == 1 && pattern[0].isEmpty()) {
            System.out.println("True");
            return;
        }
        String[] string = getStringArray(reader);
        System.out.println(contains(pattern, string));
    }

    private static String contains(String[] pattern, String[] string){
        int size = pattern.length;
        int swap = 0;
        for (String s : pattern) {
            for (int j = swap; j < string.length; j++) {
                if (string[j].equals(s)) {
                    --size;
                    if (size == 0) {
                        return "True";
                    }
                    swap = ++j;
                    break;
                }
                if (size > string.length - swap) return "False";
            }
        }
        return "False";
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static String[] getStringArray(BufferedReader reader) throws IOException {
        return reader.readLine().split("");
    }

}