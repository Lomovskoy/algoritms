package sprint2.D;

class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}

public class Solution3 {
    public static int solution(Node<String> head, String elem) {
        int index = 0;
        while (head != null) {
            if (head.value.equals(elem)) {
                return index;
            } else {
                index++;
                head = head.next;
            }
        }
        return -1;
    }

    public static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        int idx = solution(node0, "node2");
        // result is : idx == 2
        System.out.println(idx);
    }
}