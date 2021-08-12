package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var first = getIntegerList(reader);
        var second = getIntegerList(reader);
        var result = getChar(first, second);
        System.out.println(result);
    }

    private static String getChar(List<String> first, List<String> second) {
        for (var s : first) second.remove(s);
        return second.get(0);
    }

    private static List<String> getIntegerList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("")).collect(Collectors.toList());
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
