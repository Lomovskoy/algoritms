package sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C {
    private static long base;
    private static long mod;

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        base = Integer.parseInt(reader.readLine());
        mod = Integer.parseInt(reader.readLine());
        var string = reader.readLine();
        int size = Integer.parseInt(reader.readLine());
        long[] prefixHashSet = getPrefixHashSet(string);
        long[] powers = getPower(string);
        printHash(reader, size, prefixHashSet, powers);
    }

    private static void printHash(BufferedReader reader, int size, long[] prefixHashSet, long[] powers) throws IOException {
        for (int i = 0; i < size; i++) {
            int[] array = getArray(reader);
            int start = array[0];
            int end = array[1];
            long hash = (prefixHashSet[end] + mod - (prefixHashSet[start - 1] * powers[end - start + 1]) % mod) % mod;
            System.out.println(hash);
        }
    }

    private static long[] getPrefixHashSet(String string) {
        long[] prefixHashSet = new long[string.length() + 1];
        for (int i = 1; i <= string.length(); ++i) {
            prefixHashSet[i] = (prefixHashSet[i - 1] * base % mod + string.charAt(i - 1)) % mod;
        }
        return prefixHashSet;
    }

    private static long[] getPower(String string) {
        long[] powers = new long[string.length() + 1];
        powers[0] = 1;
        for (int i = 1; i < string.length(); ++i) {
            powers[i] = (powers[i - 1] * base) % mod;
        }
        return powers;
    }

    private static int[] getArray(BufferedReader reader) throws IOException {
        var tokenizer = new StringTokenizer(reader.readLine());
        return new int[]{Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}