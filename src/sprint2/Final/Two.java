package sprint2.Final;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Two {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var arr = reader.readLine().split(" ");
        double result = 0;
        var stack = new Stack<Double>();
        boolean flag = true;
        for (var val : arr) {
            if (val.matches("[-+]?\\d+")) {
                stack.push(Double.parseDouble(val));
            } else {
                flag = false;
                double first;
                double second;
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
                        result = Math.floor(second / first);
                        stack.push(result);
                        break;
                }
            }
        }
        if (flag) {
            System.out.println(stack.pop().intValue());
        } else {
            System.out.println((int) result);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}