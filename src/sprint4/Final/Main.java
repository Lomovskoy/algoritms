package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    private static final String DELETE = "delete";
    private static final String PUT = "put";
    private static final String GET = "get";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int numberOfCommand = Integer.parseInt(reader.readLine());
        ArrayList<Map<String, List<Integer>>> listRequests = getMap(reader, numberOfCommand);
        MyHashMap myHashMap = new MyHashMap();

        for (Map<String, List<Integer>> map : listRequests) {
            for (String key : map.keySet()) {
                switch (key) {
                    case GET: {
                        var result = myHashMap.getKey(map.get(key).get(0));
                        System.out.println(result == null ? "None" : result);
                        break;
                    }
                    case PUT: {
                        myHashMap.putKeyValue(map.get(key).get(0), map.get(key).get(1));
                        break;
                    }
                    case DELETE: {
                        var result = myHashMap.deleteKey(map.get(key).get(0));
                        System.out.println(result == null ? "None" : result);
                        break;
                    }
                }
            }
        }
    }

    private static ArrayList<Map<String, List<Integer>>> getMap(BufferedReader reader, int numberOfRepetitions) throws IOException {
        ArrayList<Map<String, List<Integer>>> list = new ArrayList<Map<String, List<Integer>>>();
        for (int i = 0; i < numberOfRepetitions; i++) {
            var str = reader.readLine();
            List<Integer> listNested  = new ArrayList<>();
            if (str.contains(DELETE)) {
                var arr = str.split(" ");
                listNested.add(Integer.parseInt(arr[1]));
                list.add(Map.of(arr[0], listNested));
            } else if (str.contains(GET)) {
                var arr = str.split(" ");
                listNested.add(Integer.parseInt(arr[1]));
                list.add(Map.of(arr[0], listNested));
            } else if (str.contains(PUT)) {
                var arr = str.split(" ");
                listNested.add(Integer.parseInt(arr[1]));
                listNested.add(Integer.parseInt(arr[2]));
                list.add(Map.of(arr[0], listNested));
            }
        }
        return list;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static class MyHashMap {
        private static final int INITIAL_SIZE = 16;
        private static final int Q = 1000;
        private static final int R = 103;
        private final List<List<Pair>> data = new ArrayList<>(INITIAL_SIZE);

        /**
         * Получение значения по ключу.
         * Если ключа нет в таблице,
         * то вывести «None». Иначе вывести найденное значение.
         *
         * @param key ключ
         * @return значение
         */
        public Integer getKey(int key) {
            int hash = getHash(key);
            if (hash >= data.size()) {
                return null;
            } else {
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
        }

        /**
         * Добавление пары ключ-значение.
         * Если заданный ключ уже есть в таблице,
         * то соответствующее ему значение обновляется.
         *
         * @param key   ключ
         * @param value значение
         */
        public void putKeyValue(int key, int value) {
            int hash = getHash(key);
            Pair pair = new Pair(key, value);
            if (hash >= data.size()) {
                List<Pair> pairs = new ArrayList<>();
                pairs.add(pair);
                updateDataArray(hash);
                data.add(hash, pairs);
            } else {
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
                        int index = pairsKey.indexOf(pair);
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
        public Integer deleteKey(int key) {
            int hash = getHash(key);
            if(hash >= data.size() || data.isEmpty() || data.get(hash) == null || data.get(hash).isEmpty()) {
                return null;
            } else {
                Optional<Pair> pairs = getOptionalPair(key, hash);
                if (pairs.isEmpty()) {
                    return null;
                } else {
                    int index = data.get(hash).indexOf(pairs.get());
                    data.get(hash).set(index, null);
                    return pairs.get().getValue();
                }
            }
        }

        private static int getHash(int string) {
            char[] chars = String.valueOf(string).toCharArray();
            int hash = 0;
            for (int aChar : chars) {
                hash = Math.abs((hash * Q + aChar) % R);
            }
            return hash;
        }

        private void updateDataArray(int newSize) {
            for (int i = data.size(); i <= newSize; i++) {
                data.add(null);
            }
        }

        private Optional<Pair> getOptionalPair(int key, List<Pair> pairs) {
            for (Pair pair : pairs) {
                if (pair != null && pair.getKey().equals(key)) {
                    return Optional.of(pair);
                }
            }
            return Optional.empty();
        }

        private Optional<Pair> getOptionalPair(int key, int hash) {
            return getOptionalPair(key, data.get(hash));
        }

        private List<Pair> getPairList(int key, List<Pair> pairs) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;
            if (this.getKey() == null || pair.getKey() == null) {
                return false;
            }
            return this.getKey().equals(pair.getKey());
        }
    }
}