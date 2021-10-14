package sprint5;

public class M {

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            return idx;
        }
        int parentIndex = idx / 2;
        if (heap[parentIndex] < heap[idx]) {
            int second = heap[idx];
            heap[idx] = heap[parentIndex];
            heap[parentIndex] = second;
        } else {
            return idx;
        }
        return siftUp(heap, parentIndex);
    }

    public static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }
}