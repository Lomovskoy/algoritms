package sprint5.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
ID 55244332

-- ПРИНЦИП РАБОТЫ --
    1. Создаём пустую бинарную кучу.
    2. Вставим в неё по одному все элементы массива, сохраняя свойства кучи.
        Так как нам нужна сортировка от большего к меньшему, на вершине был бы самый большой элемент.
    3. Будем извлекать из неё наиболее приоритетные элементы (с самым большим значением), удаляя их из кучи.

    Для сортировки внутри дерева используется компаратор, который сортируется элементы следующим образом:
    1. Если поле задачи разное, то по нему сортируется по убыванию.
        Если нет, то.
    2. Если поле штраф разное, то сортируется по возрастанию.
        Если нет, то.
    3. По полю Имя сортируется лексикографический.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
    Класс представляет собой невозрастающую кучу.
    Элементы в куче сортируются по убыванию.
    При добавлении элементов и извлечении их из кучи, структура Кучи не нарушается.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Сложность этой сортировки приблизительно.
    O(1) + O(n log n) + O(n log n)
    Это дает приблизительную сложность O(n log n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Сложность этой сортировки приблизительно.
    O(n)
 */
public class One {

    public static void main(String[] args) throws Exception {
        var reader = getReader();
        int size = Integer.parseInt(reader.readLine());
        var users = getUserList(reader, size);

        Heap heap = new Heap(users.length);
        for (User user : users) {
            heap.add(user);
        }

        for (int i = 0; i < users.length; i++) {
            User user = heap.popMax();
            System.out.println(user);
        }

    }

    private static class Heap {
        private int size;
        private final User[] heap;

        public Heap(int size) {
            this.size = 0;
            this.heap = new User[size + 1];
            this.heap[0] = new User("", 0, 0);
        }

        public void add(User user) {
            int index = size + 1;
            heap[index] = user;
            siftUp(index);
            size++;
        }

        private void siftUp(int idx) {
            while (true) {
                if (idx == 1) return;

                int parentIndex = idx / 2;
                if (heap[parentIndex].compareTo(heap[idx])) {
                    User swap = heap[idx];
                    heap[idx] = heap[parentIndex];
                    heap[parentIndex] = swap;
                }
                idx = parentIndex;
            }
        }

        public User popMax() {
            User result = heap[1];
            heap[1] = heap[size];
            siftDown(1);
            size--;
            return result;
        }

        public void siftDown(int idx) {
            while (true) {
                int left = 2 * idx;
                int right = 2 * idx + 1;

                // нет дочерних узлов
                if (heap.length < left || heap.length < right || idx >= size) {
                    return;
                }

                int index_largest;
                // right <= heap.size проверяет, что есть оба дочерних узла
                if ((right <= size) && (heap[left].compareTo(heap[right]))) {
                    index_largest = right;
                } else {
                    index_largest = Math.min(left, size);
                }

                if (heap[idx].compareTo(heap[index_largest])) {
                    User buffer = heap[idx];
                    heap[idx] = heap[index_largest];
                    heap[index_largest] = buffer;
                    idx = index_largest;
                    continue;
                }
                return;
            }
        }
    }

    public static User[] getUserList(BufferedReader reader, int size) throws IOException {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            users[i] = new User(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }
        return users;
    }

    public static class User {

        private final String name;
        private final int tasks;
        private final int fine;

        public User(String name, int tasks, int fine) {
            this.name = name;
            this.tasks = tasks;
            this.fine = fine;
        }

        public String getName() {
            return name;
        }

        public int getTasks() {
            return tasks;
        }

        public int getFine() {
            return fine;
        }

        public boolean compareTo(User user) {
            if (this.getTasks() != user.getTasks()) {
                return this.getTasks() < user.getTasks();
            } else if (this.getFine() != user.getFine()) {
                return this.getFine() > user.getFine();
            } else {
                return this.getName().compareTo(user.getName()) >= 0;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(this.getName());
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}