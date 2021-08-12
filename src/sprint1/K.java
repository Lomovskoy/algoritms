package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var length = Integer.parseInt(reader.readLine());
        var value = getIntegerValue(reader);
        var variable =  Integer.parseInt(reader.readLine());
        var result = Integer.toString(value + variable);
        var buffer = getResultStringBuffer(result);
        System.out.println(buffer);
    }

    private static StringBuffer getResultStringBuffer(String result) {
        var arr = result.split("");
        var buffer = new StringBuffer();
        for (var str : arr) buffer.append(str).append(" ");
        return buffer;
    }

    private static int getIntegerValue(BufferedReader reader) throws IOException {
        var arr = reader.readLine().split(" ");
        var builder = new StringBuilder();
        for (var str : arr) builder.append(str);
        return Integer.parseInt(builder.toString());
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
