package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    public static void main(String[] args) throws IOException {
        var inputStr = getReader().readLine();
        var arr = inputStr.split(" ");
        int a = Integer.parseInt(arr[0]);
        int x = Integer.parseInt(arr[1]);
        int b = Integer.parseInt(arr[2]);
        int c = Integer.parseInt(arr[3]);
        System.out.println((a * (x * x)) + (b * x) + c);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}