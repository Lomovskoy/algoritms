package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

    public static void main(String[] args) throws IOException {
        var inputStr = getReader().readLine();
        var arr = inputStr.split(" ");
        boolean a = Integer.parseInt(arr[0]) % 2 == 0;
        boolean b = Integer.parseInt(arr[1]) % 2 == 0;
        boolean c = Integer.parseInt(arr[2]) % 2 == 0;
        System.out.println(a == b && a == c ? "WIN" : "FAIL");
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}