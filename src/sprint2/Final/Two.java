package sprint2.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Two {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var arr = reader.readLine().split(" ");
        int result = 0;
        var stack = new Stack<Integer>();
        for (var val : arr) {
            if (val.matches("[-+]?\\d+")) {
                stack.push(Integer.parseInt(val));
            } else {
                int first;
                int second;
                switch (val) {
                    case "+":
                        first = stack.pop();
                        second = stack.pop();
                        result = second + first;
                        stack.push(result);
                        break;
                    case "-":
                        first = stack.pop();
                        second = stack.pop();
                        result = second - first;
                        stack.push(result);
                        break;
                    case "*":
                        first = stack.pop();
                        second = stack.pop();
                        result = second * first;
                        stack.push(result);
                        break;
                    default:
                        first = stack.pop();
                        second = stack.pop();
                        result = second / first;
                        stack.push(result);
                        break;
                }
            }
        }
        System.out.println(result);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}