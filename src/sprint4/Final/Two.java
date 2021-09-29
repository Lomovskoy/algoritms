package sprint4.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
                    String result = myHashMap.getKey(tokenizer.nextToken());
                    stringBuilder.append(result == null ? "None" : result).append("\n");
                    break;
                }
                case PUT: {
                    myHashMap.putKeyValue(tokenizer.nextToken(), tokenizer.nextToken());
                    break;
                }
                case DELETE: {
                    String result = myHashMap.deleteKey(tokenizer.nextToken());
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
        private static final int Q = INITIAL_SIZE;
        private final List<List<Pair>> data = new ArrayList<>(INITIAL_SIZE);

        public MyHashMap() {
            updateDataArray(INITIAL_SIZE);
        }

        /**
         * Получение значения по ключу.
         * Если ключа нет в таблице,
         * то вывести «None». Иначе вывести найденное значение.
         *
         * @param key ключ
         * @return значение
         */
        public String getKey(String key) {
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
        public void putKeyValue(String key, String value) {
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
                        int index = indexOf(pairs, pair);
                        if (index < 0) {
                            data.get(hash).set(data.get(hash).size() - 1, pair);
                        } else {
                            data.get(hash).set(index, pair);
                        }
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
        public String deleteKey(String key) {
            int hash = getHash(key);
            if (hash >= data.size() || data.isEmpty() || data.get(hash) == null || data.get(hash).isEmpty()) {
                return null;
            } else {
                Optional<Pair> pairs = getOptionalPair(key, hash);
                if (pairs.isEmpty()) {
                    return null;
                } else {
                    int index = indexOf(data.get(hash), pairs.get());
                    if (index < 0) {
                        return null;
                    }
                    data.get(hash).set(index, null);
                    return pairs.get().getValue();
                }
            }
        }

        private static int getHash(String string) {
            return Integer.parseInt(string) % Q;
        }

        private int indexOf(List<Pair> pairs, Pair pair) {
            int index = 0;
            for (Pair val : pairs) {
                if (val != null && val.getKey() != null && pair != null && pair.getKey() != null) {
                    if (val.getKey().equals(pair.getKey())) {
                        return index;
                    }
                }
                index++;
            }
            return -1;
        }

        private void updateDataArray(int newSize) {
            for (int i = data.size(); i <= newSize; i++) {
                data.add(null);
            }
        }

        private Optional<Pair> getOptionalPair(String key, List<Pair> pairs) {
            for (Pair pair : pairs) {
                if (pair != null && pair.getKey().equals(key)) {
                    return Optional.of(pair);
                }
            }
            return Optional.empty();
        }

        private Optional<Pair> getOptionalPair(String key, int hash) {
            return getOptionalPair(key, data.get(hash));
        }

        private List<Pair> getPairList(String key, List<Pair> pairs) {
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
        private final String key;
        private String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}