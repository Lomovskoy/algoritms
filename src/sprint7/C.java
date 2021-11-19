package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C {

    private static final List<Heap> heaps = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();
        int capacity = Integer.parseInt(reader.readLine());
        int size = Integer.parseInt(reader.readLine());

        getMeeting(reader, size);

        heaps.sort(Heap::compareTo);
        Collections.reverse(heaps);

        long sum = 0;

        for (Heap heap : heaps) {
            if (heap.getWeight() <= capacity) {
                capacity -= heap.getWeight();
                sum += (long) heap.getPrice() * heap.getWeight();
            } else {
                if (capacity == 0) {
                    break;
                } else {
                    for (int i = 0; i < heap.getWeight(); i++) {
                        if (capacity == 0) {
                            break;
                        }
                        sum += heap.getPrice();
                        capacity--;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void getMeeting(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer;
        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            heaps.add(new Heap(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }
    }

    public static class Heap implements Comparable<Heap> {
        private final int price;
        private final int weight;

        public Heap(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        public int getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Heap)) return false;

            Heap heap = (Heap) o;

            if (getPrice() != heap.getPrice()) return false;
            return getWeight() == heap.getWeight();
        }

        @Override
        public int hashCode() {
            int result = getPrice();
            result = 31 * result + getWeight();
            return result;
        }

        @Override
        public int compareTo(Heap o) {
            return Integer.compare(this.getPrice(), o.price);
        }
    }
}