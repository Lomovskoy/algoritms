package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class D {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int size = Integer.parseInt(reader.readLine());

        Set<String> strings = getNameScoresMap(reader, size);

        StringBuffer stringBuffer = new StringBuffer();
        strings.forEach(s -> stringBuffer.append(s).append("\n"));
        System.out.println(stringBuffer);

    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static Set<String> getNameScoresMap(BufferedReader reader, int size) throws IOException {
        Set<String> strings = new LinkedHashSet<>();
        for (int i = 0; i < size; i++) {
            strings.add(reader.readLine());
        }
        return strings;
    }
}