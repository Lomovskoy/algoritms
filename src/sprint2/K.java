package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {
    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var numberOfCommand = Integer.parseInt(reader.readLine());
        var result = getFibonacciValue(numberOfCommand + 1);
        System.out.println(result);
    }

    public static int getFibonacciValue(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else  {
            return getFibonacciValue(n - 1) + getFibonacciValue(n - 2);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

}