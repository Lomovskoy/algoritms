package sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class F {

    private static final String GET_MAX = "get_max";
    private static final String PUSH = "push";
    private static final String POP = "pop";

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        int numberOfRepetitions  = Integer.parseInt(reader.readLine());

        var list = getMap(reader, numberOfRepetitions);
        var stackMax = new StackMax();

        for (var map : list) {
            for (var key : map.keySet()) {
                switch (key) {
                    case GET_MAX: {
                        var result = stackMax.getMax();
                        System.out.println(result == null ? "None" : result);
                        break;
                    } case POP: {
                        var result = stackMax.pop();
                        if (result == null) System.out.println("error");
                        break;
                    } case PUSH: {
                        stackMax.push(map.get(key));
                        break;
                    }
                }
            }
        }
    }

    private static ArrayList<Map<String, Integer>> getMap(BufferedReader reader, int numberOfRepetitions) throws IOException {
        var list = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < numberOfRepetitions; i++) {
            var str  = reader.readLine();
            if (str.contains(GET_MAX)) {
                list.add(Map.of(GET_MAX, 0));
            } else if (str.contains(POP)) {
                list.add(Map.of(POP, 0));
            } else if (str.contains(PUSH)) {
                var arr = str.split(" ");
                list.add(Map.of(arr[0], Integer.parseInt(arr[1])));
            }
        }
        return list;
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}

class StackMax {

    private final List<Integer> storage = new ArrayList<>();
    private int index = 0;
    private Integer max = null;

    public void push(int val) {
        if (max == null || val > max) max = val;
        storage.add(index, val);
        index++;
    }

    public Integer pop() {
        if (index == 0) {
            return null;
        } else {
            index--;
            var val = storage.get(index);
            storage.remove(index);
            if (val.equals(max)) {
                if (storage.isEmpty()) max = null;
                else max = max();
            }
            return val;
        }
    }

    public Integer getMax() {
        return max;
    }

    private Integer max() {
        if (storage.isEmpty()) {
            return null;
        } else {
            int max = Integer.MIN_VALUE;
            for (var val : storage) {
                if (val > max) max = val;
            }
            return max;
        }
    }
}