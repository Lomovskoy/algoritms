package sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class N {

    public static void main(String[] args) throws IOException {
        // Получаем ридер для чтения из входного потока.
        BufferedReader reader = getReader();
        // Считываем размер будущего массива
        int size = Integer.parseInt(reader.readLine());
        // Генерируем массив массивов из входных строк int[size][2]
        // вложенный массив всегда будет 2 длинной
        List<List<Integer>> list = getArray(reader, size);

        // Создаем лист для результирующего ответа
        List<List<Integer>> resultList = new ArrayList<>();
        // Добавляем нулевой элемент
        resultList.add(list.get(0));

        // Перебираем массив массивов начиная с первого элемента, а не с нулевого
        for (int i = 1; i < list.size(); i++) {
            // Если 1й элемент текущего массива > первого элемента результирующего массива
            if (list.get(i).get(0) > list.get(resultList.size() - 1).get(1)) {
                // Добавляем полный массив в результирующий список
                resultList.add(list.get(i));
            }
            // Если нулевой элемент текущего массива равен первому элементу результирующего массива
            else if (list.get(i).get(0).equals(list.get(resultList.size() - 1).get(1))) {
                // Добавляем в последний массив результирующего списка последний элемент
                resultList.get(resultList.size() - 1).set(1, list.get(i).get(1));
            }
            // Если нулевой элемент текущего массива меньше первого элемента результирующего массива и
            // первый элемент текущего массива больше первого элемента результирующего массива
            else if (list.get(i).get(0) < list.get(resultList.size() - 1).get(1) &&
                    list.get(i).get(1) > list.get(resultList.size() - 1).get(1)) {
                // Добавляем в последний массив результирующего списка последний элемент
                resultList.get(resultList.size() - 1).set(1, list.get(i).get(1));
            }
        }

        // Создаем StringBuilder для работы с ответом
        StringBuilder stringBuilder = new StringBuilder();

        // Добавляем все значения в stringBuilder через пробел и добавляем переносы строки
        resultList.forEach(currentList->
                stringBuilder.append(currentList.get(0)).append(" ").append(currentList.get(1)).append("\n")
        );

        // Выводим в поток
        System.out.println(stringBuilder);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static List<List<Integer>> getArray(BufferedReader reader, int size) throws IOException {
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            List<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(tokenizer.nextToken()));
            list.add(Integer.parseInt(tokenizer.nextToken()));
            set.add(list);
        }
        ArrayList<List<Integer>> list = new ArrayList<>(set);
        return list.stream().sorted(Comparator.comparing(o -> o.get(0))).collect(Collectors.toList());
    }
}