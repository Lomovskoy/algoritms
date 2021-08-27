package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        String string = reader.readLine();
        if (string.isEmpty()) {
            System.out.println("True");
            return;
        }
        char[] pattern = getStringArray(string);
        string = reader.readLine();
        char[] target = getStringArray(string);
        System.out.println(contains(pattern, target));
    }

    private static String contains(char[] pattern, char[] string){
        int size = pattern.length;
        int swap = 0;
        for (char s : pattern) {
            for (int j = swap; j < string.length; j++) {
                if (string[j] == s) {
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

    private static char[] getStringArray(String string) throws IOException {
        return string.toCharArray();
    }

}