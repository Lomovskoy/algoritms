package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        String[] stringArr = reader.readLine().split(" ");
        reversArray(stringArr);

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringArr) {
            stringBuilder.append(s).append(" ");
        }
        System.out.println(stringBuilder);
    }

    static void reversArray(String[] arr) {
        String temp;
        for (int i = arr.length - 1, j = 0; i >= arr.length / 2; i--, j++) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}