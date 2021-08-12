package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J {

    private static int DIVIDER = 2;

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var value = Integer.parseInt(reader.readLine());
        var sqrt = sqrt(value);
        var buffer = new StringBuffer();
        factorize(value, sqrt, buffer);
        System.out.println(buffer);
    }

    private static void factorize(int value, double sqrt, StringBuffer buffer) {
        while (value > 1 && DIVIDER <= sqrt) {
            if (value % DIVIDER == 0) {
                buffer.append(DIVIDER).append(" ");
                value /= DIVIDER;
            } else if (DIVIDER == 2) {
                DIVIDER++;
            } else {
                DIVIDER += 2;
            }
        }
        if (value != 1) buffer.append(value);
    }

    public static double sqrt(int number) {
        double t;
        double squareRoot = number / 2D;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);
        return squareRoot;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
