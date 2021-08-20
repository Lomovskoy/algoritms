package sprint2.Final;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Main2 {

    public static void main(String[] args) throws IOException {
        var reader = getReader();
        var numberOfCommand = Integer.parseInt(reader.readLine());
        var dequeSize = Integer.parseInt(reader.readLine());

//        var deque = new Deque<>(Integer.class, 4);
//        System.out.println(deque.pushFront(861));
//        System.out.println(deque.pushFront(-819));
//        System.out.println(deque.popBack());
//        System.out.println(deque.popBack());
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
class Deque2<V> {
    private final V[] data;     // Массив данных
    private int size;           // Размер очереди
    private int head;           // Голова
    private int tail;           // Хвост

    public Deque2(Class<V> type, int size) {
        this.data = (V[]) Array.newInstance(type, size);
        this.size = 0;
        head = 0;
        tail = size - 1;
    }

    /**
     * Добавить элемент в начало дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param value - значение для добавления
     * @return - успешно или нет
     */
    public boolean pushFront(V value) {
        if (head < tail) {
            data[head] = value;
            head++;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Добавить элемент в конец дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param value - значение для добавления
     * @return - успешно или нет
     */
    public boolean pushBack(V value) {
        if (tail > head) {
            data[tail] = value;
            tail--;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Вывести последний элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - последний элемент дека
     */
    public V popFront() {
        if (size == 0) {
            return null;
        } else if (head == 0) {
            size--;
            return data[++tail];
        } else {
            size--;
            return data[--head];
        }
    }

    /**
     * Вывести первый элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - первый элемент дека
     */
    public V popBack() {
        if (size == 0) {
            return null;
        } else if (tail == data.length - 1) {
            size--;
            return data[--head];
        } else {
            size--;
            return data[++tail];
        }
    }
}

/**
 * Дек
 * Интерфейс, который позволяет и добавлять, и извлекать элементы с обоих концов.
 * Такой интерфейс называют «дек» (англ. deque — double ended queue, «очередь с двумя концами»).
 * Дек можно воспринимать как двустороннюю очередь.
 */
class Deque1 {

    int size;
    int head; //голова
    int tail; //хвост
    int[] data;

    public Deque1(int size) {
        data = new int[this.size = size];
    }

    /**
     * Добавить элемент в конец дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param value - значение для добавления
     * @return - успешно или нет
     */
    public void pushBack(int value)
    {
        if (++tail == size)
            tail = 0;
        data[tail] = value;
    }

    /**
     * Вывести первый элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - первый элемент дека
     */
    public int popBack()
    {
        int ret = data[tail];
        if (--tail < 0)
            tail = size - 1;
        return ret;
    }

    /**
     * Добавить элемент в начало дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param value - значение для добавления
     * @return - успешно или нет
     */
    public void pushFront(int value)
    {
        data[head] = value;
        if (--head < 0)
            head = size - 1;
    }

    /**
     * Вывести последний элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - последний элемент дека
     */
    public int popFront()
    {
        if (++head == size)
            head = 0;
        return data[head];
    }
}