package sprint2.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main4 {

    private static final String PUSH_FRONT = "push_front";
    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";

    public static void main(String[] args) throws IOException {
        var reader = getReader();

        var numberOfCommand = Integer.parseInt(reader.readLine());
        var size = Integer.parseInt(reader.readLine());

        var list = getMap(reader, numberOfCommand);
        var deque = new Deque5<Integer>(size);

        for (var map : list) {
            for (var key : map.keySet()) {
                switch (key) {
                    case PUSH_FRONT: {
                        deque.pushFront(map.get(key));
                        break;
                    }
                    case PUSH_BACK: {
                        deque.pushBack(map.get(key));
                        break;
                    }
                    case POP_FRONT: {
                        var result = deque.popFront();
                        System.out.println(result == null ? "error" : result);
                        break;
                    }
                    case POP_BACK: {
                        var result = deque.popBack();
                        System.out.println(result == null ? "error" : result);
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
            if (str.contains(POP_FRONT) || str.contains(POP_BACK)) {
                list.add(Map.of(str, 0));
            } else if (str.contains(PUSH_FRONT) || str.contains(PUSH_BACK)) {
                var arr = str.split(" ");
                list.add(Map.of(arr[0], Integer.parseInt(arr[1])));
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
class Deque5<V> {
    static int MAX_ARRAY_SIZE;
    private Object[] elements;
    private int head;
    private int tail;

    public Deque5(int size) {
        MAX_ARRAY_SIZE = size;
        elements = new Object[size];
    }

    /**
     * Добавить элемент в начало дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public void pushFront(V val) {
        if (val == null)
            throw new NullPointerException();
        final Object[] es = elements;
        es[head = dec(head, es.length)] = val;
        if (head == tail)
            grow(1);
    }

    /**
     * Добавить элемент в конец дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public void pushBack(V val) {
        if (val == null)
            throw new NullPointerException();
        final Object[] es = elements;
        es[tail] = val;
        if (head == (tail = inc(tail, es.length)))
            grow(1);
    }

    /**
     * Вывести последний элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - последний элемент дека
     */
    public V popFront() {
        final Object[] es;
        final int h;
        V e = elementAt(es = elements, h = head);
        if (e != null) {
            es[h] = null;
            head = inc(h, es.length);
        }
        return e;
    }

    /**
     * Вывести первый элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - первый элемент дека
     */
    public V popBack() {
        final Object[] es;
        final int t;
        V e = elementAt(es = elements, t = dec(tail, es.length));
        if (e != null)
            es[tail = t] = null;
        return e;
    }

    static int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    private void grow(int needed) {
        // код с учетом переполнения
        final int oldCapacity = elements.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        final Object[] es = elements = Arrays.copyOf(elements, newCapacity);
        // Exceptionally, here tail == head needs to be disambiguated
        if (tail < head || (tail == head && es[head] != null)) {
            // wrap around; slide first leg forward to end of array
            int newSpace = newCapacity - oldCapacity;
            System.arraycopy(es, head,
                    es, head + newSpace,
                    oldCapacity - head);
            for (int i = head, to = (head += newSpace); i < to; i++)
                es[i] = null;
        }
    }

    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elements.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0) ? oldCapacity + jump : MAX_ARRAY_SIZE;
    }

    static int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    static <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }
}