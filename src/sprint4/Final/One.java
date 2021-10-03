package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
-- ПРИНЦИП РАБОТЫ --
С начала входные данные считываются в dataMap -
который состоит из ключей которыми являются уникальные слова и значений.
Значениями то же является Map, ключ - номер строки, в которой встречается это слово.
Значение, это количество раз которое оно встречается в этой строке.
Потом перебираются строки которые нужны для поиска соответствий.
Каждая строка разбивается по словам, и помещается в Set, что бы избежать повторов.
Затем для каждого элемента из Set осуществляется поиск в dataMap.
Результаты поиска по ключу помечается в новый Array из ключа индекс массива,
который является номером строки, где найдено слово, и значение, это количество вхождений.
Потом извлекается 5 самых индексов по самым большим значениям,
если значения одинаковы будут извлечены индексы по порядку, если значение не 0.
После сортировки номера строк помещаются в поток вывода.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Данный алгоритм проходит все тесты и все краевые условия.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Пускай будет n документов,
в среднем по k слов и m запросов в среднем по j слов.

Временна сложность построение dataMap если судить по количеству слов то O(k)
Временна сложность responsePrint если судить по количеству слов то O(k) * n
Временна сложность print это O(k) * 5.
Печать это O(n)

Если смотреть общую сложность будет:
O(k) + O(k) * n + O(k) * 5 + O(n) =
O(k) + O(k) * (n + 5) + O(n) =
O(k + n) + O(k) * (n + 5)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность алгоритма после переработки получается не очень большой
в dataMap сохраняются все лишь слова без повторов, и все повторы обозначаются строка > количество повторений
думаю это не больше чем ~ O(log(n)).
Функция же responsePrint берёт всего лишь 1 строку из входных данных.
И результаты поиска помешает в мап, после печати очищает его.
Думаю это занимает O(1) если смотреть по строкам или O(k/n)
k - количество всех слов, n - количество строк, если смотреть по словам.
Общая сложность будет O(log(n)).

*/
public class One {

    private static final int LIMIT = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int storageSize = Integer.parseInt(reader.readLine());
        Map<String, Map<Integer, Integer>> dataMap = getDataMap(storageSize, reader);

        int requestsCount = Integer.parseInt(reader.readLine());
        responsePrint(requestsCount, reader, dataMap, storageSize);

    }

    private static void responsePrint(int requestsCount, BufferedReader reader, Map<String, Map<Integer, Integer>> dataMap, int storageSize) throws IOException {

        for (int i = 0; i < requestsCount; i++) {
            Set<String> stringSet = new LinkedHashSet<>(Arrays.asList(reader.readLine().split(" ")));
            Integer[] arrayDocument = getIntegers(storageSize);
            for (String string : stringSet) {
                Map<Integer, Integer> subMap = dataMap.get(string);
                if (subMap != null) {
                    for (Map.Entry<Integer, Integer> val : subMap.entrySet()) {
                        if (arrayDocument[val.getKey()] == null) {
                            arrayDocument[val.getKey()] = val.getValue();
                        } else {
                            arrayDocument[val.getKey()] = arrayDocument[val.getKey()] + val.getValue();
                        }
                    }
                }
            }
            print(arrayDocument);
        }
    }

    private static Integer[] getIntegers(int storageSize) {
        Integer[] arrayDocument = new Integer[storageSize];
        for (int i = 0; i < storageSize; i++) {
            arrayDocument[i] = 0;
        }
        return arrayDocument;
    }

    private static void print(Integer[] arrayDocument) {
        int maxIndex = -1;

        for (int j = 0; j < LIMIT; j++) {
            for (int i = 0; i < arrayDocument.length; i++) {
                if (maxIndex == -1 && arrayDocument[i] > 0) {
                    maxIndex = i;
                } else {
                    if (maxIndex >= 0 && arrayDocument[i] > arrayDocument[maxIndex]) {
                        maxIndex = i;
                    }
                }
            }
            if (maxIndex >= 0) {
                if (arrayDocument[maxIndex] > 0) {
                    System.out.print(maxIndex + 1 + " ");
                    arrayDocument[maxIndex] = 0;
                }
            }
        }
        System.out.println();

    }

    private static Map<String, Map<Integer, Integer>> getDataMap(int storageSize, BufferedReader reader) throws IOException {
        Map<String, Map<Integer, Integer>> dataMap = new HashMap<>();
        for (int i = 0; i < storageSize; i++) {
            for (String str : reader.readLine().split(" ")) {
                if (dataMap.get(str) == null) {
                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(i, 1);
                    dataMap.put(str, map);
                } else {
                    Map<Integer, Integer> map = dataMap.get(str);
                    if (map.get(i) == null) {
                        map.put(i, 1);
                    } else {
                        map.put(i, map.get(i) + 1);
                    }
                }
            }
        }
        return dataMap;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}