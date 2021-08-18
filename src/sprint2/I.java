package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class I {
    private static final String SIZE = "size";
    private static final String PEEK = "peek";
    private static final String PUSH = "push";
    private static final String POP = "pop";

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var numberOfCommand = Integer.parseInt(reader.readLine());
        var size = Integer.parseInt(reader.readLine());
        var queue = new MyQueueSized(size);

        var list = getMap(reader, numberOfCommand);
        for (var map : list) {
            for (var key : map.keySet()) {
                switch (key) {
                    case SIZE: {
                        var result = queue.getSize();
                        System.out.println(result);
                        break;
                    }
                    case POP: {
                        var result = queue.pop();
                        System.out.println(result == null ? "None" : result);
                        break;
                    }
                    case PUSH: {
                        boolean result = queue.push(map.get(key));
                        if (!result) System.out.println("error");
                        break;
                    }
                    case PEEK: {
                        var result = queue.peek();
                        System.out.println(result == null ? "None" : result);
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
            } else if (str.contains(PEEK)) {
                list.add(Map.of(PEEK, 0));
            }  else if (str.contains(POP)) {
                list.add(Map.of(POP, 0));
            } else if (str.contains(PUSH)) {
                var arr = str.split(" ");
                list.add(Map.of(arr[0], Integer.parseInt(arr[1])));
            }
        }
        return list;
    }

}

class MyQueueSized {

    private final List<Integer> queue;
    private final int maxN;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public MyQueueSized(int size) {
        this.queue = new ArrayList<>(size);
        for (int i = 0; i < size; i++) queue.add(null);
        this.maxN = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(int val) {
        if (size != maxN) {
            queue.set(tail, val);
            tail = (tail + 1) % maxN;
            size += 1;
            return true;
        } else {
            return false;
        }
    }

    public Integer peek() {
        return size == 0 ? null : queue.get(head);
    }

    public Integer pop() {
        if (isEmpty()) return null;
        var val = queue.get(head);
        queue.set(head, null);
        head = (head + 1) % maxN;
        size -= 1;
        return val;
    }

    public int getSize() {
        return size;
    }
}