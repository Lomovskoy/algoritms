package sprint5.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
            if (idx == 1) {
                return;
            }
            int parentIndex = idx / 2;
            if (heap[parentIndex].compareTo(heap[idx])) {
                User swap = heap[idx];
                heap[idx] = heap[parentIndex];
                heap[parentIndex] = swap;
            }
            siftUp(parentIndex);
        }

        public User popMax() {
            User result = heap[1];
            heap[1] = heap[size];
            siftDown(1);
            size--;
            return result;
        }

        public void siftDown(int idx) {
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
                siftDown(index_largest);
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
            return String.valueOf(this.getName());// + " " + this.getFine() + " " + this.getName();
        }
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}