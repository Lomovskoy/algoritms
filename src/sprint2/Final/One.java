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

Deque -- это порядок LIFO - если класть и доставать элементы с одного конца,
то есть, или в голову класть и доставать из головы, или в хвост класть и доставать от туда же.
Или Порядок FIFO - если доставать элементы с противоположной стороны, то есть положил в голову,
извлёк из хвоста и наоборот.
То есть можно использовать и как стек и как очередь.

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

-- Комментарий --
Мне кажется общая сложность программы O(n*2)
*/
public class One {

    private static final String PUSH_FRONT = "push_front";
    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";
    private static final String ERROR = "error";
    private static final String LINE_BREAK = "\n";


    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        int numberOfCommand = Integer.parseInt(reader.readLine());
        int size = Integer.parseInt(reader.readLine());

        Deque<Integer> deque = new Deque<>(size);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numberOfCommand; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String token = tokenizer.nextToken();
            switch (token) {
                case POP_FRONT: {
                    Integer result = deque.popFront();
                    if (result == null) {
                        stringBuilder.append(ERROR).append(LINE_BREAK);
                    } else {
                        stringBuilder.append(result).append(LINE_BREAK);
                    }
                    break;
                }
                case POP_BACK: {
                    Integer result = deque.popBack();
                    if (result == null) {
                        stringBuilder.append(ERROR).append(LINE_BREAK);
                    } else {
                        stringBuilder.append(result).append(LINE_BREAK);
                    }
                    break;
                }
                case PUSH_FRONT: {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    boolean result = deque.pushFront(value);
                    if (!result) stringBuilder.append(ERROR).append(LINE_BREAK);
                    break;
                }
                case PUSH_BACK: {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    boolean result = deque.pushBack(value);
                    if (!result) stringBuilder.append(ERROR).append(LINE_BREAK);
                    break;
                }
            }
        }
        System.out.println(stringBuilder);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
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
        return (i - 1 + modulus) % modulus;
    }

    static int inc(int i, int modulus) {
        return (i + 1 + modulus) % modulus;
    }
}