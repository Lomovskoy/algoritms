package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
-- ПРИНЦИП РАБОТЫ --
MyHashMap имеет 3 метода
1 getKey - ищет по хешу переданного ключа, ячейку со значением.
    Значение является корзиной, то есть лист c парами ключ -> значение.
    Если ключи совпадают, то значение найдено, если нет ищется дальше по листу,
    если не найдено, то null.
    В лучшем случае если не было коллизий по этому ключу, это будет за ~ O(1).
    В худшем случае если все ключи будут коллизиями, то поиск будет за O(n);
2 putKeyValue - делает хеш из ключа. Помещает ключ и значение в пару.
    Вытаскивает корзину с парами, и смотрит если по такому ключу нет пары,
    то добавляет её туда, если же есть, то заменяет значение по паре.
    В лучшем случае если не было коллизий по этому ключу, это будет за ~ O(1).
    В худшем случае если все ключи будут коллизиями, то поиск будет за O(n);
3 deleteKey - делает хеш из ключа. Проверяет:
    больше ли хеш, блинны листа корзин, или лст корзин пуст, или значение по хешу не найдено, или найден пустой список корзин.
    Если любой из этих условий соблюдено, то возвращается Null.
    Затем по хешу и ключу ищется конкретная пара. Если она не найдена возвращается null.
    Определяем по найденной паре её индекс, потом по индексу заменяем пару на null, и возвращаем значение пары.
    В лучшем случае если не было коллизий по этому ключу, это будет за ~ O(1).
    В худшем случае если все ключи будут коллизиями, то поиск будет за O(n);

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
MyHashMap работает как HashMap - разрешение коллизий работает на основе методом цепочек.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В общем у всех методов
В лучшем случае если не было коллизий по этому ключу, это будет за ~ O(1).
В худшем случае если все ключи будут коллизиями, то поиск будет за O(n);
В общем временная сложность очень зависит от сложности хеш функции.
Если там много сложных действий над значениями с плавающей запятой,
то она будет работать очень медленно.
Меняя её можно контролировать сложность.


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В лучшем случае если хеш хорошо распределён то, это будет за ~ O(n).
В худшем случае если хеш плохо распределён, то сложно сказать, так как 2 значения 1 и 2
могут поместиться в ячейки 0 и 1000000 и в итоге на 2 значения будет создан лист в 1000001 ячеек,
но в плохо случае думаю сложность будет экспоненциальна. Мне кажется всё зависит от хеш функции,
меняя её или меняя константы можно контролировать пространственную сложность.

*/
public class Two {

    private static final String DELETE = "delete";
    private static final String PUT = "put";
    private static final String GET = "get";

    public static void main(String[] args) throws IOException {
        MyHashMap myHashMap = new MyHashMap();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = getReader();
        int numberOfCommand = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfCommand; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            switch (tokenizer.nextToken()) {
                case GET: {
                    Integer result = myHashMap.getKey(Integer.parseInt(tokenizer.nextToken()));
                    stringBuilder.append(result == null ? "None" : result).append("\n");
                    break;
                }
                case PUT: {
                    myHashMap.putKeyValue(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
                    break;
                }
                case DELETE: {
                    Integer result = myHashMap.deleteKey(Integer.parseInt(tokenizer.nextToken()));
                    stringBuilder.append(result == null ? "None" : result).append("\n");
                    break;
                }
            }
        }
        System.out.println(stringBuilder);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static class MyHashMap {
        private static final int INITIAL_SIZE = 100000;
        private final List<List<Pair>> data = new ArrayList<>(INITIAL_SIZE);

        public MyHashMap() {
            updateDataArray();
        }

        /**
         * Получение значения по ключу.
         * Если ключа нет в таблице,
         * то вывести «None». Иначе вывести найденное значение.
         *
         * @param key ключ
         * @return значение
         */
        public Integer getKey(Integer key) {
            int hash = getHash(key);
            List<Pair> pairs = data.get(hash);
            if (pairs == null || pairs.isEmpty()) {
                return null;
            }
            Optional<Pair> pair = getOptionalPair(key, pairs);
            if (pair.isEmpty()) {
                return null;
            } else {
                return pair.get().getValue();
            }
        }

        /**
         * Добавление пары ключ-значение.
         * Если заданный ключ уже есть в таблице,
         * то соответствующее ему значение обновляется.
         *
         * @param key   ключ
         * @param value значение
         */
        public void putKeyValue(Integer key, Integer value) {
            int hash = getHash(key);
            Pair pair = new Pair(key, value);
            List<Pair> pairs = data.get(hash);
            if (pairs == null || pairs.isEmpty()) {
                List<Pair> newPairs = new ArrayList<>();
                newPairs.add(pair);
                data.set(hash, newPairs);
            } else {
                List<Pair> pairsKey = getPairList(key, pairs);
                if (pairsKey.isEmpty()) {
                    data.get(hash).add(new Pair(key, value));
                } else {
                    int index = indexOf(pairs, pair.getKey());
                    if (index < 0) {
                        data.get(hash).set(data.get(hash).size() - 1, pair);
                    } else {
                        data.get(hash).set(index, pair);
                    }
                }
            }
        }

        /**
         * Удаление ключа из таблицы.
         * Если такого ключа нет, то вывести «None»,
         * иначе вывести хранимое по данному ключу значение и удалить ключ.
         *
         * @param key ключ
         * @return значение
         */
        public Integer deleteKey(Integer key) {
            int hash = getHash(key);
            if (data.isEmpty() || data.get(hash) == null || data.get(hash).isEmpty()) {
                return null;
            } else {
                int index = indexOf(data.get(hash), key);
                if (index < 0) {
                    return null;
                }
                var value = data.get(hash).get(index).getValue();
                data.get(hash).set(index, null);
                return value;
            }
        }

        private static int getHash(Integer val) {
            return val % INITIAL_SIZE;
        }

        private int indexOf(List<Pair> pairs, Integer key) {
            int index = 0;
            for (Pair val : pairs) {
                if (val != null && val.getKey() != null && key != null) {
                    if (val.getKey().equals(key)) {
                        return index;
                    }
                }
                index++;
            }
            return -1;
        }

        private void updateDataArray() {
            for (int i = data.size(); i <= MyHashMap.INITIAL_SIZE; i++) {
                data.add(null);
            }
        }

        private Optional<Pair> getOptionalPair(Integer key, List<Pair> pairs) {
            for (Pair pair : pairs) {
                if (pair != null && pair.getKey().equals(key)) {
                    return Optional.of(pair);
                }
            }
            return Optional.empty();
        }

        private Optional<Pair> getOptionalPair(Integer key, int hash) {
            return getOptionalPair(key, data.get(hash));
        }

        private List<Pair> getPairList(Integer key, List<Pair> pairs) {
            List<Pair> pairList = new ArrayList<>();
            for (Pair pair : pairs) {
                if (pair != null && pair.getKey().equals(key)) {
                    pairList.add(pair);
                }
            }
            return pairList;
        }
    }

    public static class Pair {
        private final Integer key;
        private Integer value;

        public Pair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

    }
}