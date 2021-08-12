package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int stringLength = Integer.parseInt(reader.readLine());
        var stringList  = getStringList(reader);
        var str = "";
        for (var s : stringList) {
            if (s.length() > str.length()) {
                str = s;
            }
        }
        System.out.println(str);
        System.out.println(str.length());
    }


    private static List<String> getStringList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
