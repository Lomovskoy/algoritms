package sprint2.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

public class Main6 {

    private static final String PUSH_FRONT = "push_front";
    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";

    public static void main(String[] args) throws IOException {
        var reader = getReader();

        var numberOfCommand = Integer.parseInt(reader.readLine());
        var size = Integer.parseInt(reader.readLine());

        var list = getListMap(reader, numberOfCommand);
        var deque = new Deque6<Integer>(size);

        var stringBuilder = new StringBuilder();

        for (var map : list) {
                switch (map.getKey()) {
                    case PUSH_FRONT: {
                        var result = deque.pushFront(map.getValue());
                        if (!result) stringBuilder.append("error\n");
                        break;
                    }
                    case PUSH_BACK: {
                        var result = deque.pushBack(map.getValue());
                        if (!result) stringBuilder.append("error\n");
                        break;
                    }
                    case POP_FRONT: {
                        var result = deque.popFront();
                        if (result == null) {
                            stringBuilder.append("error\n");
                        } else {
                            stringBuilder.append(result).append("\n");
                        }
                        break;
                    }
                    case POP_BACK: {
                        var result = deque.popBack();
                        if (result == null) {
                            stringBuilder.append("error\n");
                        } else {
                            stringBuilder.append(result).append("\n");
                        }
                        break;
                }
            }
        }
        System.out.println(stringBuilder);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static ArrayList<Map.Entry<String, Integer>> getListMap(BufferedReader reader, int numberOfRepetitions) throws IOException {
        var list = new ArrayList<Map.Entry<String, Integer>>();
        for (int i = 0; i < numberOfRepetitions; i++) {
            var tokenizer = new StringTokenizer(reader.readLine());
            var token = tokenizer.nextToken();
            if (token.equals(POP_FRONT) || token.equals(POP_BACK)) {
                list.add(Map.entry(token, 0));
            } else if (token.equals(PUSH_FRONT) || token.equals(PUSH_BACK)) {
                int value = Integer.parseInt(tokenizer.nextToken());
                list.add(Map.entry(token, value));
            }
        }
        return list;
    }
}

/**
 * Дек
 * Интерфейс, который позволяет и добавлять, и извлекать элементы с обоих концов.
 * Такой интерфейс называют «дек» (англ. deque — double ended queue, «очередь с двумя концами»).
 * Дек можно воспринимать как двустороннюю очередь.
 *
 * @param <V> - тип данных для Дек
 */
class Deque6<V> {
    private final V[] elements;
    private int head;
    private int tail;
    private int size;

    public Deque6(int size) {
        this.size = 0;
        elements = (V[]) new Object[size];
    }

    /**
     * Добавить элемент в начало дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public boolean pushFront(V val) {
        if (size == elements.length) return false;
        head = dec(head, elements.length);
        elements[head] = val;
        size++;
        return true;
    }

    /**
     * Добавить элемент в конец дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public boolean pushBack(V val) {
        if (size == elements.length) return false;
        elements[tail] = val;
        tail = inc(tail, elements.length);
        size++;
        return true;
    }

    /**
     * Вывести последний элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - последний элемент дека
     */
    public V popFront() {
        if (size == 0) return null;
        var element = elements[head];
        elements[head] = null;
        head = inc(head, elements.length);
        size--;
        return element;
    }

    /**
     * Вывести первый элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - первый элемент дека
     */
    public V popBack() {
        if (size == 0) return null;
        tail = dec(tail, elements.length);
        var element = elements[tail];
        elements[tail] = null;
        size--;
        return element;
    }

    static int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    static int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }
}