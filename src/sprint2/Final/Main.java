package sprint2.Final;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        var deque = new Deque<Integer>();

//        System.out.println("pushFront: 1 2 3 4 5");
//
//        deque.pushFront(1);
//        deque.pushFront(2);
//        deque.pushFront(3);
//        deque.pushFront(4);
//        deque.pushFront(5);
//
//        System.out.print("popFront: ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + "\n\n");
//
//        System.out.println("pushBack: 1 2 3 4 5");
//
//        deque.pushBack(1);
//        deque.pushBack(2);
//        deque.pushBack(3);
//        deque.pushBack(4);
//        deque.pushBack(5);
//
//        System.out.print("popBack: ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + "\n\n");
//
//        System.out.println("pushFront: 1 2 3 4 5");
//
//        deque.pushFront(1);
//        deque.pushFront(2);
//        deque.pushFront(3);
//        deque.pushFront(4);
//        deque.pushFront(5);
//
//        System.out.print("popBack: ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + " ");
//        System.out.print(deque.popBack() + "\n");

//        System.out.println("pushBack: 1 2 3 4 5");
//
//        deque.pushBack(1);
//        deque.pushBack(2);
//        deque.pushBack(3);
//        deque.pushBack(4);
//        deque.pushBack(5);
//
//        System.out.print("popFront: ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + " ");
//        System.out.print(deque.popFront() + "\n\n");
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

    private V value;        // Значение
    private Deque<V> next;  // Следующий элемент
    private Deque<V> head;  // Текущий элемент
    private Deque<V> tail;  // Хвост очереди
    private Deque<V> early; // Предыдущий элемент
    private int size = 0;   // Размер очереди

    public Deque() {}

    public Deque(V value, Deque<V> next, Deque<V> early) {
        this.value = value;
        this.next = next;
        this.early = early;
    }

    /**
     * Добавить элемент в начало дека.
     * Если в деке уже находится максимальное число элементов, вывести «error».
     * @param val - значение для добавления
     * @return - успешно или нет
     */
    public void pushFront(V val) {
        if (size == 0) {
            value = val;
            head = this;
            tail = this;
            size++;
        } else {
            early = head;
            next = tail;
            head = new Deque<>(val, next, early);
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
            next = tail;
            tail = new Deque<>(val, next, tail.early);
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
        head = head.early;
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
        tail = tail.next;
        size--;
        return val;
    }
}