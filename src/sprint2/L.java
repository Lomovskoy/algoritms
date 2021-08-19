package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L {

    private static final double[] cache = new double[1000002];
    private static final int coefficient = 10;

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var array = getStringArray(reader);
        int value = array.get(0);
        double module = Math.pow(coefficient, array.get(1));
        cache[0] = 1;
        cache[1] = 1;
        double result = fibo(value + 1, module);
        double moduleResult = result % module;
        System.out.println((int) moduleResult);
    }

    public static double fibo(int n, double module) {
        for (int i = 2; i <= n; i++) {
            cache[i] = (cache[i - 1] % module) + (cache[i - 2] % module);
        }
        return cache[n - 1];
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<Integer> getStringArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

}