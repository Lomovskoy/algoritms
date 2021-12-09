package sprint8.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class One {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int size = Integer.parseInt(reader.readLine());
        String[] unpackStrings = getArray(reader, size);

        System.out.println(longCommonPrefix(unpackStrings));
    }

    public static StringBuilder longCommonPrefix(String[] strings) {
        StringBuilder commonPrefix = new StringBuilder();
        int count = 0, k = 0;

        if (strings.length > 0) {
            for (int i = 0; i < strings[0].length(); i++) {
                int j = 1;
                while (j < strings.length) {
                    if (strings[0].charAt(k) == strings[j].charAt(k)) {
                        count++;
                        j++;
                    } else {
                        break;
                    }
                }
                if (count == strings.length - 1) {
                    commonPrefix.append(strings[0].charAt(k));
                    count = 0;
                    k++;
                } else {
                    return commonPrefix;
                }

            }
        }

        return commonPrefix;
    }

    private static String unpack(String string) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < string.length();) {
            Pair pair = unpackInternal(i, string);
            resultString.append(pair.getString());
            i = pair.getIndex();
        }
        return resultString.toString();
    }

    private static Pair unpackInternal(int index, String string) {
        if (string.charAt(index) == '[' || string.charAt(index) == ']') {
            return new Pair(++index, "");
        }

        StringBuilder subStringBuilder = new StringBuilder();

        while ((index < string.length()) && (string.charAt(index) != ']')) {
            if (Character.isLetter(string.charAt(index))) {
                subStringBuilder.append(string.charAt(index++));
            } else {
                int multiplier = Integer.parseInt(String.valueOf(string.charAt(index)));
                index += 2;
                Pair pair = unpackInternal(index, string);
                for (int i = 0; i < multiplier; i++) {
                    subStringBuilder.append(pair.getString());
                }
                index = pair.getIndex() + 1;
            }
        }
        return new Pair(index, subStringBuilder.toString());
    }

    private static String[] getArray(BufferedReader reader, int size) throws IOException {
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = unpack(reader.readLine());
        }
        return strings;
    }

    private static class Pair {
        private final int index;
        private final String string;

        private Pair(int index, String string) {
            this.index = index;
            this.string = string;
        }

        public int getIndex() {
            return index;
        }

        public String getString() {
            return string;
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}