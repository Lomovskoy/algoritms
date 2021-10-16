package sprint5;

public class L {

    public static int siftDown(int[] heap, int idx) {
        int left = 2 * idx;
        int right = 2 * idx + 1;

        // нет дочерних узлов
        if (heap.length < left || heap.length < right) {
            return idx;
        }

        int index_largest;
        // right <= heap.size проверяет, что есть оба дочерних узла
        if ((right < heap.length) && (heap[left] < heap[right])) {
            index_largest = right;
        } else {
            index_largest = left;
        }


        if (heap[idx] < heap[index_largest]) {
            int buffer = heap[idx];
            heap[idx] = heap[index_largest];
            heap[index_largest] = buffer;
            return siftDown(heap, index_largest);
        }
        return idx;
    }

    public static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }
}