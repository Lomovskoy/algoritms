package sprint2.Final;

import java.util.*;
import java.lang.*;
import java.io.*;

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал Обратный польский калькулятор на Stack.

Алгоритм работает на стеке:
1. Получить очередную часть выражения
2. Если это число, положить его в стек
3. Если это операция, то достать из стека 2 числа,
сделать с ними эту операцию, и результат положить назад в стек.

После обработки всего выражения результат будет лежать на вершине стека.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Мы идем по списку символов, если встречаем цифру кладем её в стек,
если встречаем символ, достаем 2 предыдущих символа из стека и
применяем к ним это действие, а ответ кладем отдельно в стек.
Если следовать этому алгоритму, то мы успешно обработаем все нужные варианты и получим корректный отчет.

Stack -- это порядок LIFO.
Стек инвертирует порядок элементов: первые становятся последними.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Амортизированная сложность алгоритма составляет O(n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Если стек содержит n элементов, то пространственная сложность будет O(n + 3) - так как есть
result, first и second, в среднем получается O(n)
*/
public class Two {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var arr = reader.readLine().split(" ");
        Integer result = null;
        var stack = new Stack<Integer>();
        boolean flag = true;
        for (var val : arr) {
            if (val.matches("[-+]?\\d+")) {
                stack.push(Integer.parseInt(val));
            } else {
                flag = false;
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
                        result = (int) Math.floor((double) second / (double) first);
                        stack.push(result);
                        break;
                }
            }
        }
        if (flag) {
            System.out.println(stack.pop());
        } else {
            System.out.println(result);
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}