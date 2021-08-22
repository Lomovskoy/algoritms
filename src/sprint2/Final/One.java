package sprint2.Final;

import java.util.*;
import java.lang.*;
import java.io.*;

/*
-- ПРИНЦИП РАБОТЫ --
Я реализовал двусторонний deque на массиве.
Все элементы добавленные в голову можно извлечь из хвоста в прямом порядке,
или из головы в обратном порядке и наоборот.

Если на момент извлечения из deque выходной стек пуст,
то будет "error". Если deque переполнен, то будет "error".

Я вдохновился идеей решения из стандартной библиотеки Java Collections.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из описания алгоритма следует, что чем раньше элемент добавился в deque,
тем позже он будет из неё извлечён.

Deque -- это порядок LIFO.
Стек инвертирует порядок элементов: первые становятся последними.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление в очередь стоит O(1), это константная сложность,
так как помимо добавления в массив нужно ещё передвинуть голову или хвост,
проверить размер и прибавить или отнять размер.
Но все рано количество действий не зависит от размера стека по этому O(1),
так как согласно заданию размер стека увеличивать не нужно при переполнении,
иначе O(1) - была бы амортизированной сложностью

Извлечение из стека стоит O(1).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Если стек содержит n элементов, то пространственная сложность будет O(n + 3) - так как есть
размер, голова и хвост, в среднем получается O(n)
*/
public class One {

    private static final String PUSH_FRONT = "push_front";
    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int numberOfCommand = Integer.parseInt(reader.readLine());
        int size = Integer.parseInt(reader.readLine());

        ArrayList<Map<String, Integer>> list = getListMap(reader, numberOfCommand);
        Deque<Integer> deque = new Deque<>(size);

        StringBuilder stringBuilder = new StringBuilder();

        for (Map<String, Integer> map : list) {
            for (String key : map.keySet()) {
                switch (key) {
                    case PUSH_FRONT: {
                        boolean result = deque.pushFront(map.get(key));
                        if (!result) stringBuilder.append("error\n");
                        break;
                    }
                    case PUSH_BACK: {
                        boolean result = deque.pushBack(map.get(key));
                        if (!result) stringBuilder.append("error\n");
                        break;
                    }
                    case POP_FRONT: {
                        Integer result = deque.popFront();
                        if (result == null) {
                            stringBuilder.append("error\n");
                        } else {
                            stringBuilder.append(result).append("\n");
                        }
                        break;
                    }
                    case POP_BACK: {
                        Integer result = deque.popBack();
                        if (result == null) {
                            stringBuilder.append("error\n");
                        } else {
                            stringBuilder.append(result).append("\n");
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(stringBuilder);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static ArrayList<Map<String, Integer>> getListMap(BufferedReader reader, int numberOfRepetitions) throws IOException {
        ArrayList<Map<String, Integer>> list = new ArrayList<>();
        for (int i = 0; i < numberOfRepetitions; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String token = tokenizer.nextToken();
            if (token.equals(POP_FRONT) || token.equals(POP_BACK)) {
                Map<String, Integer> map = new HashMap<>();
                map.put(token, 0);
                list.add(map);
            } else if (token.equals(PUSH_FRONT) || token.equals(PUSH_BACK)) {
                int value = Integer.parseInt(tokenizer.nextToken());
                Map<String, Integer> map = new HashMap<>();
                map.put(token, value);
                list.add(map);
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
class Deque<V> {
    private final V[] elements;
    private int head;
    private int tail;
    private int size;

    public Deque(int size) {
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
        V element = elements[head];
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
        V element = elements[tail];
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