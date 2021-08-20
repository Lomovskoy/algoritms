package sprint2.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {

    public static void main(String[] args) throws IOException {
//        var reader = getReader();
//        var numberOfCommand = Integer.parseInt(reader.readLine());
//        var dequeSize = Integer.parseInt(reader.readLine());

        var deque = new Deque<Integer>();
        deque.pushFront(861);
        deque.pushFront(-819);
        System.out.println(deque.popBack());
        System.out.println(deque.popBack());
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
class Deque4<V> {

    private V value;
    private Deque4<V> next;
    private Deque4<V> head;
    private Deque4<V> tail;
    private int size = 0;

    public Deque4() {}

    public Deque4(V value, Deque4<V> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Добавить элемент в начало дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public void pushFront(V val) {
        if (size == 0) {
            value = val;
            tail = this;
            head = this;
            size++;
        } else {
            tail.next = new Deque4<>(val, null);
            tail = tail.next;
            size++;
        }
    }

    /**
     * Добавить элемент в конец дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     *
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public void pushBack(V val) {
        if (size == 0) {
            value = val;
            tail = this;
            head = this;
            size++;
        } else {
            tail.next = new Deque4<>(val, null);
            tail = tail.next;
            size++;
        }
    }

    /**
     * Вывести последний элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - последний элемент дека
     */
    public V popFront() {
        if (size == 0) return null;
        var val = head.value;
        head = head.next;
        size--;
        return val;
    }

    /**
     * Вывести первый элемент дека и удалить его.
     * Если дек был пуст, то вывести «error».
     *
     * @return - первый элемент дека
     */
    public V popBack() {
        if (size == 0) return null;
        var val = tail.value;
        tail = tail.tail;
        size--;
        return val;
    }
}