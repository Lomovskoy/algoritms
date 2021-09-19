package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class H {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        char[] string1 = reader.readLine().toCharArray();
        char[] string2 = reader.readLine().toCharArray();
        if (string1.length != string2.length) {
            System.out.println("NO");
        } else {
            String result = "YES";
            Map<Character, Character> map = new HashMap<>();

            for (int i = 0; i < string1.length; i++) {
                if (map.get(string1[i]) == null) {
                    map.put(string1[i], string2[i]);
                    for (Map.Entry<Character, Character> val : map.entrySet()) {
                        if (val.getValue() == string2[i] && val.getKey() != string1[i]) {
                            System.out.println("NO");
                            return;
                        }
                    }
                } else {
                    if (map.get(string1[i]) != string2[i]) {
                        result = "NO";
                        break;
                    }
                }
            }
            System.out.println(result);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

}