package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/*
-- ПРИНЦИП РАБОТЫ --
С начала входные данные считываются в dataMap -
который состоит из ключей которыми являются уникальные слова и значений.
Значениями то же является Map, ключ - номер строки, в которой встречается это слово.
Значение, это количество раз которое оно встречается в этой строке.
Потом перебираются строки которые нужны для поиска соответствий.
Каждая строка разбивается по словам, и помещается в Set, что бы избежать повторов.
Затем для каждого элемента из Set осуществляется поиск в dataMap.
Результаты поиска по ключу помечается в новый Map из ключа,
который является номером строки, где найдено слово, и значение, это количество вхождений.
Потом осуществляется сортировка по количеству вхождений, и повторная сортировка по номеру строки.
После сортировки номера строк помещаются в поток вывода.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Данный алгоритм проходит все тесты и все краевые условия.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временна сложность построение dataMap O(n^2) - если судить по количеству строк,
но если судить по количеству слов то O(n)
Временна сложность responsePrint O(n^3) - если судить по количеству строк,
но если судить по количеству слов то O(n^2)
Временна сложность print + в ней сортировки 2 шт - двойная сортировка, думаю это O(n + log(n)) * 2.
Печать это O(n)
Если смотреть по количеству слов то общая сложность сортировки будет
O(n) + O(n^2) + O(n + log(n)) * 2 + O(n) ~= O(n * 2) + O(n^2) + O(n * 2 + log(n * 2)) ~= O((n * 4 + log(n * 2))^2) ~=
O(n * 6) - на самом деле я не знаю как правильно посчитать

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
        responsePrint(requestsCount, reader, dataMap);

    }

    private static void responsePrint(int requestsCount, BufferedReader reader, Map<String, Map<Integer, Integer>> dataMap) throws IOException {
        Map<Integer, Integer> responseMap = new HashMap<>();
        for (int i = 0; i < requestsCount; i++) {
            for (String string : new LinkedHashSet<>(List.of(reader.readLine().split(" ")))) {
                var subMap = dataMap.get(string);
                if (subMap != null) {
                    for (var val : subMap.entrySet()) {
                        responseMap.merge(val.getKey(), val.getValue(), Integer::sum);
                    }
                }
            }
            print(responseMap);
        }
    }

    private static void print(Map<Integer, Integer> responseMap) {
        responseMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry::getKey)).limit(LIMIT)
                .forEach(in -> System.out.print((in.getKey() + 1) + " "));
        responseMap.clear();
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