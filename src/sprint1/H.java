package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {

    private static final String ONE = "1";
    private static final String ZERO = "0";

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var first = reader.readLine();
        var second  = reader.readLine();
        if (first.length() != second.length()){
            first = getLongestStr(first, second);
            second = getLongestStr(second, first);
        }
        var result = addition(first, second);
        System.out.println(result);
    }

    private static String getLongestStr(String first, String second) {
        if (first.length() < second.length()){
            var builder = new StringBuilder();
            var length = second.length() - first.length();
            builder.append("0".repeat(length));
            builder.append(first);
            return builder.toString();
        }
        return first;
    }

    private static String addition(String first, String second) {
        var firstArr = first.split("");
        var secondArr = second.split("");
        return buildBinaryValue(firstArr, secondArr);
    }

    private static String buildBinaryValue(String[] firstArr, String[] secondArr) {
        var sb = new StringBuilder();
        var buffer = 0;
        for (int i = firstArr.length - 1; i >= 0; i--) {
            if (zeroAndOneOrOneAndZero(firstArr[i], secondArr[i]))
                concatenateOneOrZero(sb, buffer);

            if (compare(firstArr[i], secondArr[i], ONE))
                buffer = concatenateOneOrZeroAndBufferEqOne(sb, buffer);

            if (compare(firstArr[i], secondArr[i], ZERO))
                buffer = concatenateOneOrZeroAndBufferEqZero(sb, buffer);
        }
        sb.reverse();
        addFirstValue(sb, buffer);
        return sb.toString();
    }

    private static int concatenateOneOrZeroAndBufferEqZero(StringBuilder sb, int buffer) {
        if (buffer == 1) {
            sb.append(buffer);
            buffer = 0;
        } else {
            sb.append(0);
        }
        return buffer;
    }

    private static int concatenateOneOrZeroAndBufferEqOne(StringBuilder sb, int buffer) {
        if (buffer == 1) {
            sb.append(buffer);
        } else {
            sb.append(0);
            buffer = 1;
        }
        return buffer;
    }

    private static void concatenateOneOrZero(StringBuilder sb, int buffer) {
        if (buffer == 1) sb.append(0);
        else sb.append(1);
    }

    private static boolean compare(String s1, String s2, String s3) {
        return s1.equals(s3) && s2.equals(s3);
    }

    private static boolean compare(String s1, String s2, String s3, String s4) {
        return s1.equals(s3) && s2.equals(s4);
    }

    private static boolean zeroAndOneOrOneAndZero(String s1, String s2) {
        return (compare(s1, s2, ZERO, ONE)) || (compare(s1, s2, ONE, ZERO));
    }

    private static void addFirstValue(StringBuilder sb, int buffer) {
        if (buffer == 1) sb.insert(0, buffer);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}