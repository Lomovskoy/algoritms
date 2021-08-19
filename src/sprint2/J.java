package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class J {
    private static final String SIZE = "size";
    private static final String PUT = "put";
    private static final String GET = "get";

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var numberOfCommand = Integer.parseInt(reader.readLine());
        var list = getMap(reader, numberOfCommand);

        var queue = new ListQueue<Integer>();

        for (var map : list) {
            for (var key : map.keySet()) {
                switch (key) {
                    case SIZE: {
                        var result = queue.size();
                        System.out.println(result);
                        break;
                    }
                    case GET: {
                        var result = queue.get();
                        System.out.println(result == null ? "error" : result);
                        break;
                    }
                    case PUT: {
                        queue.put(map.get(key));
                        break;
                    }
                }
            }
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static ArrayList<Map<String, Integer>> getMap(BufferedReader reader, int numberOfRepetitions) throws IOException {
        var list = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < numberOfRepetitions; i++) {
            var str = reader.readLine();
            if (str.contains(SIZE)) {
                list.add(Map.of(SIZE, 0));
            } else if (str.contains(GET)) {
                list.add(Map.of(GET, 0));
            } else if (str.contains(PUT)) {
                var arr = str.split(" ");
                list.add(Map.of(arr[0], Integer.parseInt(arr[1])));
            }
        }
        return list;
    }
}

class ListQueue<V> {

    private V value;
    private ListQueue<V> next;
    private ListQueue<V> head;
    private ListQueue<V> tail;
    private int size = 0;

    public ListQueue() {}

    public ListQueue(V value, ListQueue<V> next) {
        this.value = value;
        this.next = next;
    }

    public V get() {
        if (size == 0) return null;
        var val = head.value;
        head = head.next;
        size--;
        return val;
    }

    public void put(V val) {
        if (size == 0) {
            value = val;
            tail = this;
            head = this;
            size++;
        } else {
            tail.next = new ListQueue<>(val, null);
            tail = tail.next;
            size++;
        }
    }

    public Integer size() {
        return size;
    }
}