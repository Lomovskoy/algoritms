package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var decimal = Integer.parseInt(reader.readLine());
        var binary  = getBinary(decimal);
        System.out.println(binary);
    }

    static String getBinary(int number) {
        var sb = new StringBuilder();
        var size = bitsInNumber(number);
        for (int i = 0; i < size; i++) {
            sb.append(number & 1);
            number >>= 1;
        }
        return sb.reverse().toString();
    }

    public static int bitsInNumber(int n) {
        int res = 0;
        while (n > 0) {
            n >>= 1;
            res++;
        }
        return res;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
